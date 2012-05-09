package org.apache.airavata.test.suite.gfac;

import org.apache.airavata.commons.gfac.type.ActualParameter;
import org.apache.airavata.commons.gfac.type.ApplicationDeploymentDescription;
import org.apache.airavata.commons.gfac.type.HostDescription;
import org.apache.airavata.commons.gfac.type.ServiceDescription;
import org.apache.airavata.core.gfac.context.invocation.impl.DefaultExecutionContext;
import org.apache.airavata.core.gfac.context.invocation.impl.DefaultInvocationContext;
import org.apache.airavata.core.gfac.context.message.impl.ParameterContextImpl;
import org.apache.airavata.core.gfac.context.security.impl.GSISecurityContext;
import org.apache.airavata.core.gfac.notification.impl.LoggingNotification;
import org.apache.airavata.core.gfac.services.impl.PropertiesBasedServiceImpl;
import org.apache.airavata.migrator.registry.MigrationUtil;
import org.apache.airavata.registry.api.impl.AiravataJCRRegistry;
import org.apache.airavata.schemas.gfac.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.URL;
import java.util.*;

import static org.junit.Assert.fail;

public class GramProviderPwscfRangerTest {
    public static final String MYPROXY = "myproxy";
    public static final String GRAM_PROPERTIES = "gram-ranger.properties";
    private AiravataJCRRegistry jcrRegistry = null;

    @Before
    public void setUp() throws Exception {
        Map<String,String> config = new HashMap<String,String>();
            config.put("org.apache.jackrabbit.repository.home","target");

        jcrRegistry = new AiravataJCRRegistry(null,
                "org.apache.jackrabbit.core.RepositoryFactoryImpl", "admin",
                "admin", config);

        // Host
        URL url = this.getClass().getClassLoader().getResource(GRAM_PROPERTIES);
        Properties properties = new Properties();
        properties.load(url.openStream());
        HostDescription host = new HostDescription();
        host.getType().changeType(GlobusHostType.type);
        host.getType().setHostName(properties.getProperty("host.commom.name"));
        host.getType().setHostAddress(properties.getProperty("host.fqdn.name"));
        ((GlobusHostType) host.getType()).setGridFTPEndPointArray(new String[]{properties.getProperty("gridftp.endpoint")});
        ((GlobusHostType) host.getType()).setGlobusGateKeeperEndPointArray(new String[]{properties.getProperty("gram.endpoints")});

        /* Application */
        ApplicationDeploymentDescription appDesc = new ApplicationDeploymentDescription(GramApplicationDeploymentType.type);
        GramApplicationDeploymentType app = (GramApplicationDeploymentType) appDesc.getType();
        app.setNodeCount(1);
        ApplicationDeploymentDescriptionType.ApplicationName name = appDesc.getType().addNewApplicationName();
        name.setStringValue("MockPwscfMPI");
        app.setExecutableLocation("/share/home/01437/ogce/airavata-test/applications/MockPwscf/pwscf.w");
        app.setScratchWorkingDirectory(properties.getProperty("scratch.working.directory"));
        app.setStaticWorkingDirectory("/share/home/01437/ogce/airavata-test/applications/MockPwscf");
        app.setCpuCount(4);
        app.setJobType(MigrationUtil.getJobTypeEnum("MPI"));
        app.setMaxWallTime(9);
        ProjectAccountType projectAccountType = ((GramApplicationDeploymentType) appDesc.getType()).addNewProjectAccount();
        projectAccountType.setProjectAccountNumber(properties.getProperty("allocation.charge.number"));

        /* Service */
        ServiceDescription serv = new ServiceDescription();
        serv.getType().setName("MockPwscfMPIService");

        InputParameterType input = InputParameterType.Factory.newInstance();
        ParameterType parameterType = input.addNewParameterType();
        parameterType.setName("echo_mpi_input");
        List<InputParameterType> inputList = new ArrayList<InputParameterType>();
        inputList.add(input);
        InputParameterType[] inputParamList = inputList.toArray(new InputParameterType[inputList
                .size()]);

        OutputParameterType output = OutputParameterType.Factory.newInstance();
        ParameterType parameterType1 = output.addNewParameterType();
        parameterType1.setName("echo_mpi_output");
        List<OutputParameterType> outputList = new ArrayList<OutputParameterType>();
        outputList.add(output);
        OutputParameterType[] outputParamList = outputList
                .toArray(new OutputParameterType[outputList.size()]);
        serv.getType().setInputParametersArray(inputParamList);
        serv.getType().setOutputParametersArray(outputParamList);

        /* Save to Registry */
        jcrRegistry.saveHostDescription(host);
        jcrRegistry.saveDeploymentDescription(serv.getType().getName(), host.getType().getHostName(), appDesc);
        jcrRegistry.saveServiceDescription(serv);
        jcrRegistry.deployServiceOnHost(serv.getType().getName(), host.getType().getHostName());
    }

    @Test
    public void testExecute() {
        try {
            URL url = this.getClass().getClassLoader().getResource(GRAM_PROPERTIES);
            Properties properties = new Properties();
            properties.load(url.openStream());

            DefaultInvocationContext ct = new DefaultInvocationContext();
            DefaultExecutionContext ec = new DefaultExecutionContext();
            ec.addNotifiable(new LoggingNotification());
            ec.setRegistryService(jcrRegistry);
            ct.setExecutionContext(ec);


            GSISecurityContext gsiSecurityContext = new GSISecurityContext();
            gsiSecurityContext.setMyproxyServer(properties.getProperty("myproxy.server"));
            gsiSecurityContext.setMyproxyUserName(properties.getProperty("myproxy.username"));
            gsiSecurityContext.setMyproxyPasswd(properties.getProperty("myproxy.password"));
            gsiSecurityContext.setMyproxyLifetime(14400);
            gsiSecurityContext.setTrustedCertLoc(properties.getProperty("ca.certificates.directory"));

            ct.addSecurityContext(MYPROXY, gsiSecurityContext);

            ct.setServiceName("MockPwscfMPIService");

            /* Input */
            ParameterContextImpl input = new ParameterContextImpl();
            ActualParameter echo_input = new ActualParameter();
            ((StringParameterType) echo_input.getType()).setValue("echo_mpi_output=hi");
            input.add("echo_mpi_input", echo_input);

            /* Output */
            ParameterContextImpl output = new ParameterContextImpl();
            ActualParameter echo_output = new ActualParameter();
            output.add("echo_mpi_output", echo_output);

            /* parameter */
            ct.setInput(input);
            ct.setOutput(output);

            PropertiesBasedServiceImpl service = new PropertiesBasedServiceImpl();
            service.init();
            service.execute(ct);

            System.out.println("output              : " + ct.getOutput().toString());
            System.out.println("output from service : " + ct.getOutput().getValue("echo_mpi_output"));

            Assert.assertNotNull(ct.getOutput());

        } catch (Exception e) {
            e.printStackTrace();
            fail("ERROR");
        }
    }
}