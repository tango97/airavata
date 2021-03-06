/**
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
 */

package org.apache.aiaravata.application.catalog.data.resources;

import org.airavata.appcatalog.cpi.AppCatalogException;
import org.apache.aiaravata.application.catalog.data.model.Workflow;
import org.apache.aiaravata.application.catalog.data.model.WorkflowInput;
import org.apache.aiaravata.application.catalog.data.model.WorkflowInput_PK;
import org.apache.aiaravata.application.catalog.data.util.AppCatalogJPAUtils;
import org.apache.aiaravata.application.catalog.data.util.AppCatalogQueryGenerator;
import org.apache.aiaravata.application.catalog.data.util.AppCatalogResourceType;
import org.apache.airavata.common.exception.ApplicationSettingsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkflowInputResource extends AbstractResource {

    private final static Logger logger = LoggerFactory.getLogger(WorkflowInputResource.class);

    private String wfTemplateId;
    private String inputKey;
    private String dataType;
    private String inputVal;
    private String metadata;
    private String appArgument;
    private String userFriendlyDesc;
    private boolean standareInput;

    private WorkflowResource workflowResource;

    public void remove(Object identifier) throws AppCatalogException {
        HashMap<String, String> ids;
        if (identifier instanceof Map) {
            ids = (HashMap) identifier;
        } else {
            logger.error("Identifier should be a map with the field name and it's value");
            throw new AppCatalogException("Identifier should be a map with the field name and it's value");
        }

        EntityManager em = null;
        try {
            em = AppCatalogJPAUtils.getEntityManager();
            em.getTransaction().begin();
            AppCatalogQueryGenerator generator = new AppCatalogQueryGenerator(WORKFLOW_INPUT);
            generator.setParameter(WFInputConstants.WF_TEMPLATE_ID, ids.get(WFInputConstants.WF_TEMPLATE_ID));
            generator.setParameter(WFInputConstants.INPUT_KEY, ids.get(WFInputConstants.INPUT_KEY));
            Query q = generator.deleteQuery(em);
            q.executeUpdate();
            em.getTransaction().commit();
            em.close();
        } catch (ApplicationSettingsException e) {
            logger.error(e.getMessage(), e);
            throw new AppCatalogException(e);
        } finally {
            if (em != null && em.isOpen()) {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                em.close();
            }
        }
    }

    public Resource get(Object identifier) throws AppCatalogException {
        HashMap<String, String> ids;
        if (identifier instanceof Map) {
            ids = (HashMap<String, String>) identifier;
        } else {
            logger.error("Identifier should be a map with the field name and it's value");
            throw new AppCatalogException("Identifier should be a map with the field name and it's value");
        }

        EntityManager em = null;
        try {
            em = AppCatalogJPAUtils.getEntityManager();
            em.getTransaction().begin();
            AppCatalogQueryGenerator generator = new AppCatalogQueryGenerator(WORKFLOW_INPUT);
            generator.setParameter(WFInputConstants.WF_TEMPLATE_ID, ids.get(WFInputConstants.WF_TEMPLATE_ID));
            generator.setParameter(WFInputConstants.INPUT_KEY, ids.get(WFInputConstants.INPUT_KEY));
            Query q = generator.selectQuery(em);
            WorkflowInput workflowInput = (WorkflowInput) q.getSingleResult();
            WorkflowInputResource workflowInputResource =
                    (WorkflowInputResource) AppCatalogJPAUtils.getResource(AppCatalogResourceType.WORKFLOW_INPUT
                            , workflowInput);
            em.getTransaction().commit();
            em.close();
            return workflowInputResource;
        } catch (ApplicationSettingsException e) {
            logger.error(e.getMessage(), e);
            throw new AppCatalogException(e);
        } finally {
            if (em != null && em.isOpen()) {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                em.close();
            }
        }
    }

    public List<Resource> get(String fieldName, Object value) throws AppCatalogException {
        List<Resource> wfInputResources = new ArrayList<Resource>();
        EntityManager em = null;
        try {
            em = AppCatalogJPAUtils.getEntityManager();
            em.getTransaction().begin();
            Query q;
            AppCatalogQueryGenerator generator = new AppCatalogQueryGenerator(WORKFLOW_INPUT);
            List results;
            if (fieldName.equals(WFInputConstants.WF_TEMPLATE_ID)) {
                generator.setParameter(WFInputConstants.WF_TEMPLATE_ID, value);
                q = generator.selectQuery(em);
                results = q.getResultList();
                if (results.size() != 0) {
                    for (Object result : results) {
                        WorkflowInput workflowInput = (WorkflowInput) result;
                        WorkflowInputResource workflowInputResource =
                                (WorkflowInputResource) AppCatalogJPAUtils.getResource(
                                        AppCatalogResourceType.WORKFLOW_INPUT, workflowInput);
                        wfInputResources.add(workflowInputResource);
                    }
                }
            } else if (fieldName.equals(WFInputConstants.INPUT_KEY)) {
                generator.setParameter(WFInputConstants.INPUT_KEY, value);
                q = generator.selectQuery(em);
                results = q.getResultList();
                if (results.size() != 0) {
                    for (Object result : results) {
                        WorkflowInput workflowInput = (WorkflowInput) result;
                        WorkflowInputResource workflowInputResource =
                                (WorkflowInputResource) AppCatalogJPAUtils.getResource(
                                        AppCatalogResourceType.WORKFLOW_INPUT, workflowInput);
                        wfInputResources.add(workflowInputResource);
                    }
                }
            } else if (fieldName.equals(WFInputConstants.DATA_TYPE)) {
                generator.setParameter(WFInputConstants.DATA_TYPE, value);
                q = generator.selectQuery(em);
                results = q.getResultList();
                if (results.size() != 0) {
                    for (Object result : results) {
                        WorkflowInput workflowInput = (WorkflowInput) result;
                        WorkflowInputResource workflowInputResource =
                                (WorkflowInputResource) AppCatalogJPAUtils.getResource(
                                        AppCatalogResourceType.WORKFLOW_INPUT, workflowInput);
                        wfInputResources.add(workflowInputResource);
                    }
                }
            } else {
                em.getTransaction().commit();
                em.close();
                logger.error("Unsupported field name for WFInput Resource.", new IllegalArgumentException());
                throw new IllegalArgumentException("Unsupported field name for WFInput Resource.");
            }
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new AppCatalogException(e);
        } finally {
            if (em != null && em.isOpen()) {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                em.close();
            }
        }
        return wfInputResources;
    }

    public List<Resource> getAll() throws AppCatalogException {
        return null;
    }

    public List<String> getAllIds() throws AppCatalogException {
        return null;
    }

    public List<String> getIds(String fieldName, Object value) throws AppCatalogException {
        List<String> wfInputResourceIDs = new ArrayList<String>();
        EntityManager em = null;
        try {
            em = AppCatalogJPAUtils.getEntityManager();
            em.getTransaction().begin();
            Query q;
            AppCatalogQueryGenerator generator = new AppCatalogQueryGenerator(WORKFLOW_INPUT);
            List results;
            if (fieldName.equals(WFInputConstants.WF_TEMPLATE_ID)) {
                generator.setParameter(WFInputConstants.WF_TEMPLATE_ID, value);
                q = generator.selectQuery(em);
                results = q.getResultList();
                if (results.size() != 0) {
                    for (Object result : results) {
                        WorkflowInput workflowInput = (WorkflowInput) result;
                        wfInputResourceIDs.add(workflowInput.getWfTemplateId());
                    }
                }
            } else if (fieldName.equals(WFInputConstants.INPUT_KEY)) {
                generator.setParameter(WFInputConstants.INPUT_KEY, value);
                q = generator.selectQuery(em);
                results = q.getResultList();
                if (results.size() != 0) {
                    for (Object result : results) {
                        WorkflowInput workflowInput = (WorkflowInput) result;
                        wfInputResourceIDs.add(workflowInput.getWfTemplateId());
                    }
                }
            } else if (fieldName.equals(WFInputConstants.DATA_TYPE)) {
                generator.setParameter(WFInputConstants.DATA_TYPE, value);
                q = generator.selectQuery(em);
                results = q.getResultList();
                if (results.size() != 0) {
                    for (Object result : results) {
                        WorkflowInput workflowInput = (WorkflowInput) result;
                        wfInputResourceIDs.add(workflowInput.getWfTemplateId());
                    }
                }
            } else {
                em.getTransaction().commit();
                em.close();
                logger.error("Unsupported field name for WFInput resource.", new IllegalArgumentException());
                throw new IllegalArgumentException("Unsupported field name for WFInput Resource.");
            }
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new AppCatalogException(e);
        } finally {
            if (em != null && em.isOpen()) {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                em.close();
            }
        }
        return wfInputResourceIDs;
    }

    public void save() throws AppCatalogException {
        EntityManager em = null;
        try {
            em = AppCatalogJPAUtils.getEntityManager();
            WorkflowInput existingWFInput = em.find(WorkflowInput.class, new WorkflowInput_PK(wfTemplateId, inputKey));
            em.close();
            WorkflowInput workflowInput;
            em = AppCatalogJPAUtils.getEntityManager();
            em.getTransaction().begin();
            if (existingWFInput == null) {
                workflowInput = new WorkflowInput();
            } else {
            	workflowInput=existingWFInput;
            }
            workflowInput.setWfTemplateId(wfTemplateId);
            Workflow workflow = em.find(Workflow.class, wfTemplateId);
            workflowInput.setWorkflow(workflow);
            workflowInput.setDataType(dataType);
            workflowInput.setInputKey(inputKey);
            if (inputVal != null){
                workflowInput.setInputVal(inputVal.toCharArray());
            }
            workflowInput.setMetadata(metadata);
            workflowInput.setAppArgument(appArgument);
            workflowInput.setUserFriendlyDesc(userFriendlyDesc);
            workflowInput.setStandardInput(standareInput);
            if (existingWFInput == null) {
                em.persist(workflowInput);
            } else {
                em.merge(workflowInput);
            }
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new AppCatalogException(e);
        } finally {
            if (em != null && em.isOpen()) {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                em.close();
            }
        }
    }

    public boolean isExists(Object identifier) throws AppCatalogException {
        HashMap<String, String> ids;
        if (identifier instanceof Map) {
            ids = (HashMap<String, String>) identifier;
        } else {
            logger.error("Identifier should be a map with the field name and it's value");
            throw new AppCatalogException("Identifier should be a map with the field name and it's value");
        }

        EntityManager em = null;
        try {
            em = AppCatalogJPAUtils.getEntityManager();
            WorkflowInput workflowInput = em.find(WorkflowInput.class, new WorkflowInput_PK(
                    ids.get(WFInputConstants.WF_TEMPLATE_ID),
                    ids.get(WFInputConstants.INPUT_KEY)));

            em.close();
            return workflowInput != null;
        } catch (ApplicationSettingsException e) {
            logger.error(e.getMessage(), e);
            throw new AppCatalogException(e);
        } finally {
            if (em != null && em.isOpen()) {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                em.close();
            }
        }
    }

    public String getWfTemplateId() {
        return wfTemplateId;
    }

    public void setWfTemplateId(String wfTemplateId) {
        this.wfTemplateId = wfTemplateId;
    }

    public String getInputKey() {
        return inputKey;
    }

    public void setInputKey(String inputKey) {
        this.inputKey = inputKey;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getInputVal() {
        return inputVal;
    }

    public void setInputVal(String inputVal) {
        this.inputVal = inputVal;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public String getAppArgument() {
        return appArgument;
    }

    public void setAppArgument(String appArgument) {
        this.appArgument = appArgument;
    }

    public String getUserFriendlyDesc() {
        return userFriendlyDesc;
    }

    public void setUserFriendlyDesc(String userFriendlyDesc) {
        this.userFriendlyDesc = userFriendlyDesc;
    }

    public WorkflowResource getWorkflowResource() {
        return workflowResource;
    }

    public void setWorkflowResource(WorkflowResource workflowResource) {
        this.workflowResource = workflowResource;
    }

    public boolean isStandareInput() {
        return standareInput;
    }

    public void setStandareInput(boolean standareInput) {
        this.standareInput = standareInput;
    }
}
