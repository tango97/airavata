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

package org.apache.aiaravata.application.catalog.data.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.openjpa.persistence.DataCache;

@DataCache
@Entity
@Table(name = "COMPUTE_RESOURCE")
public class ComputeResource implements Serializable {
	
	@Column(name = "RESOURCE_DESCRIPTION")
	private String resourceDescription;
	
	@Id
	@Column(name = "RESOURCE_ID")
	private String resourceId;
	
	@Column(name = "HOST_NAME")
	private String hostName;

    @Column(name = "CREATION_TIME")
    private Timestamp creationTime;

    @Column(name = "UPDATE_TIME")
    private Timestamp updateTime;

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getResourceDescription() {
		return resourceDescription;
	}
	
	public String getResourceId() {
		return resourceId;
	}
	
	public String getHostName() {
		return hostName;
	}
	
	public void setResourceDescription(String resourceDescription) {
		this.resourceDescription=resourceDescription;
	}
	
	public void setResourceId(String resourceId) {
		this.resourceId=resourceId;
	}
	
	public void setHostName(String hostName) {
		this.hostName=hostName;
	}
}