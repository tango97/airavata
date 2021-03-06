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

package org.apache.airavata.commons.gfac.type;

import org.apache.airavata.schemas.gfac.ApplicationDeploymentDescriptionDocument;
import org.apache.airavata.schemas.gfac.ApplicationDeploymentDescriptionType;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlException;

public class ApplicationDescription implements Type {

	private ApplicationDeploymentDescriptionDocument appDocument;

	public ApplicationDescription() {
		this.appDocument = ApplicationDeploymentDescriptionDocument.Factory
				.newInstance();
		this.appDocument.addNewApplicationDeploymentDescription();
	}

	public ApplicationDescription(SchemaType type) {
		this();
		this.appDocument.getApplicationDeploymentDescription().changeType(type);
	}

	public ApplicationDeploymentDescriptionType getType() {
		return this.appDocument.getApplicationDeploymentDescription();
	}

	public String toXML() {
		return appDocument.xmlText();
	}

	public static ApplicationDescription fromXML(String xml)
			throws XmlException {
		ApplicationDescription app = new ApplicationDescription();
		app.appDocument = ApplicationDeploymentDescriptionDocument.Factory
				.parse(xml);
		return app;
	}
}
