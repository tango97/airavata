/*
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
*/
package org.apache.airavata.persistance.registry.jpa.resources;

import org.apache.airavata.persistance.registry.jpa.Resource;
import org.apache.airavata.persistance.registry.jpa.ResourceType;
import org.apache.airavata.persistance.registry.jpa.ResourceUtils;
import org.apache.airavata.persistance.registry.jpa.model.Gateway;
import org.apache.airavata.persistance.registry.jpa.model.Published_Workflow;
import org.apache.airavata.persistance.registry.jpa.model.Published_Workflow_PK;
import org.apache.airavata.persistance.registry.jpa.model.Users;
import org.apache.airavata.registry.cpi.RegistryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.util.List;

public class PublishWorkflowResource extends AbstractResource {
    private final static Logger logger = LoggerFactory.getLogger(PublishWorkflowResource.class);
    private String name;
    private String version;
    private Timestamp publishedDate;
    private String content;
    private GatewayResource gateway;
    private String createdUser;
    private String path;

    /**
     *
     */
    public PublishWorkflowResource() {
    }

    /**
     *
     * @param gateway gateway resource
     */
    public PublishWorkflowResource(GatewayResource gateway) {
        this.gateway = gateway;
    }

    /**
     *
     * @return created user
     */
    public String getCreatedUser() {
        return createdUser;
    }

    /**
     *
     * @return path of the workflow
     */
    public String getPath() {
        return path;
    }

    /**
     *
     * @param createdUser  created user
     */
    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    /**
     *
     * @param path path of the workflow
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     *
     * @return name of the publish workflow
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return version
     */
    public String getVersion() {
        return version;
    }

    /**
     *
     * @return published date
     */
    public Timestamp getPublishedDate() {
        return publishedDate;
    }

    /**
     *
     * @return content of the workflow
     */
    public String getContent() {
        return content;
    }

    /**
     *
     * @param version version of the workflow
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     *
     * @param publishedDate published date of the workflow
     */
    public void setPublishedDate(Timestamp publishedDate) {
        this.publishedDate = publishedDate;
    }

    /**
     *
     * @param content content of the workflow
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Since published workflows are at the leaf level of the
     * data structure, this method is not valid
     * @param type type of the child resource
     * @return UnsupportedOperationException
     */
    public Resource create(ResourceType type) throws RegistryException {
        logger.error("Unsupported resource type for published workflow resource.", new UnsupportedOperationException());
        throw new UnsupportedOperationException();
    }

    /**
     * Since published workflows are at the leaf level of the
     * data structure, this method is not valid
     * @param type type of the child resource
     * @param name name of the child resource
     */
    public void remove(ResourceType type, Object name) throws RegistryException{
        logger.error("Unsupported resource type for published workflow resource.", new UnsupportedOperationException());
        throw new UnsupportedOperationException();
    }

    /**
     * Since published workflows are at the leaf level of the
     * data structure, this method is not valid
     * @param type type of the child resource
     * @param name name of the child resource
     * @return UnsupportedOperationException
     */
    public Resource get(ResourceType type, Object name) throws RegistryException{
        logger.error("Unsupported resource type for published workflow resource.", new UnsupportedOperationException());
        throw new UnsupportedOperationException();
    }

    /**
     * since published workflows are at the leaf level of the
     * data structure, this method is not valid
     * @param type type of the child resource
     * @return UnsupportedOperationException
     */
    public List<Resource> get(ResourceType type) throws RegistryException{
        logger.error("Unsupported resource type for published workflow resource.", new UnsupportedOperationException());
        throw new UnsupportedOperationException();
    }

    /**
     * save published workflow to the database
     */
    public void save() throws RegistryException{
        EntityManager em = null;
        try {
            em = ResourceUtils.getEntityManager();
            Published_Workflow existingWF = em.find(Published_Workflow.class, new Published_Workflow_PK(gateway.getGatewayName(), name));
            em.close();

            em = ResourceUtils.getEntityManager();
            em.getTransaction().begin();
            Published_Workflow publishedWorkflow = new Published_Workflow();
            publishedWorkflow.setPublish_workflow_name(getName());
            publishedWorkflow.setPublished_date(publishedDate);
            publishedWorkflow.setVersion(version);
            byte[] bytes = content.getBytes();
            publishedWorkflow.setWorkflow_content(bytes);
            Gateway existingGateway = em.find(Gateway.class, gateway.getGatewayName());
            publishedWorkflow.setGateway(existingGateway);
            publishedWorkflow.setGateway_name(existingGateway.getGateway_name());
            Users user = em.find(Users.class, createdUser);
            publishedWorkflow.setUser(user);
            if (existingWF != null) {
                existingWF.setUser(user);
                existingWF.setGateway(existingGateway);
                existingWF.setGateway_name(existingGateway.getGateway_name());
                existingWF.setPublished_date(publishedDate);
                existingWF.setWorkflow_content(bytes);
                existingWF.setVersion(version);
                existingWF.setPath(path);
                publishedWorkflow = em.merge(existingWF);
            } else {
                em.merge(publishedWorkflow);
            }

            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RegistryException(e);
        } finally {
            if (em != null && em.isOpen()) {
                if (em.getTransaction().isActive()){
                    em.getTransaction().rollback();
                }
                em.close();
            }
        }
    }


    /**
     * Since published workflows are at the leaf level of the
     * data structure, this method is not valid
     * @param type type of the child resource
     * @param name name of the child resource
     * @return UnsupportedOperationException
     */
    public boolean isExists(ResourceType type, Object name) throws RegistryException{
        logger.error("Unsupported resource type for published workflow resource.", new UnsupportedOperationException());
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @return gateway resource
     */
    public GatewayResource getGateway() {
		return gateway;
	}

    /**
     *
     * @param gateway gateway resource
     */
    public void setGateway(GatewayResource gateway) {
		this.gateway = gateway;
	}

    /**
     *
     * @param name published workflow name
     */
    public void setName(String name) {
		this.name = name;
	}
}
