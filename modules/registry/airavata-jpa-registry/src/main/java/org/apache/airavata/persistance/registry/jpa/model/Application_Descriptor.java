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

@Entity
public class Application_Descriptor {
    @Id
    private String application_descriptor_ID;
    private String application_descriptor_xml;

    @ManyToOne
    @JoinColumn(name = "gateway_ID")
    private Gateway gateway;

    @OneToOne
    @JoinColumn(name = "service_descriptor_ID")
    private Service_Descriptor service_descriptor;

    @ManyToOne
    @JoinColumn(name = "host_descriptor_ID")
    private Host_Descriptor host_descriptor;

    @ManyToOne
    @JoinColumn(name = "user_ID")
    private Users user;

    public String getApplication_descriptor_ID() {
        return application_descriptor_ID;
    }

    public String getApplication_descriptor_xml() {
        return application_descriptor_xml;
    }

    public Gateway getGateway() {
        return gateway;
    }

    public Service_Descriptor getService_descriptor() {
        return service_descriptor;
    }

    public Host_Descriptor getHost_descriptor() {
        return host_descriptor;
    }

    public void setApplication_descriptor_ID(String application_descriptor_ID) {
        this.application_descriptor_ID = application_descriptor_ID;
    }

    public void setApplication_descriptor_xml(String application_descriptor_xml) {
        this.application_descriptor_xml = application_descriptor_xml;
    }

    public void setGateway(Gateway gateway) {
        this.gateway = gateway;
    }

    public void setService_descriptor(Service_Descriptor service_descriptor) {
        this.service_descriptor = service_descriptor;
    }

    public void setHost_descriptor(Host_Descriptor host_descriptor) {
        this.host_descriptor = host_descriptor;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}