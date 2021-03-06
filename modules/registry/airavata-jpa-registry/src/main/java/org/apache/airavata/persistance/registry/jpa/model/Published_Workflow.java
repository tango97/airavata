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
package org.apache.airavata.persistance.registry.jpa.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name ="PUBLISHED_WORKFLOW")
@IdClass(Published_Workflow_PK.class)
public class Published_Workflow implements Serializable {

    @Id
    @Column(name = "PUBLISH_WORKFLOW_NAME")
    private String publish_workflow_name;

    @Id
    @Column(name = "GATEWAY_NAME")
    private String gateway_name;

    @ManyToOne(cascade= CascadeType.MERGE)
    @JoinColumn(name = "GATEWAY_NAME")
    private Gateway gateway;

    @Column(name = "VERSION")
    private String version;
    @Column(name = "PUBLISHED_DATE")
    private Timestamp published_date;

    @Lob
    @Column(name = "WORKFLOW_CONTENT")
    private byte[] workflow_content;
    @Column(name = "PATH")
    private String path;

    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name = "CREATED_USER", referencedColumnName = "USER_NAME")
    private Users user;

    public String getPublish_workflow_name() {
        return publish_workflow_name;
    }

    public String getVersion() {
        return version;
    }

    public Timestamp getPublished_date() {
        return published_date;
    }

    public byte[] getWorkflow_content() {
        return workflow_content;
    }

    public Gateway getGateway() {
        return gateway;
    }

    public void setPublish_workflow_name(String publish_workflow_name) {
        this.publish_workflow_name = publish_workflow_name;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setPublished_date(Timestamp published_date) {
        this.published_date = published_date;
    }

    public void setWorkflow_content(byte[] workflow_content) {
        this.workflow_content = workflow_content;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getGateway_name() {
        return gateway_name;
    }

    public void setGateway_name(String gateway_name) {
        this.gateway_name = gateway_name;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public void setGateway(Gateway gateway) {
        this.gateway = gateway;
    }
}


