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

package org.apache.airavata.client.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.airavata.client.airavata.AiravataClient;
import org.apache.airavata.client.api.AiravataAPIInvocationException;
import org.apache.airavata.client.api.AiravataManager;
import org.apache.airavata.common.registry.api.exception.RegistryException;

public class AiravataManagerImpl implements AiravataManager {
	private AiravataClient client;
	
	public AiravataManagerImpl(AiravataClient client) {
		setClient(client);
	}
	
	@Override
	public List<URI> getGFaCURLs()  throws AiravataAPIInvocationException{
		List<URI> list=new ArrayList<URI>();
		try {
			List<String> gFacDescriptorList = getClient().getRegistry().getGFacDescriptorList();
			for (String urlString : gFacDescriptorList) {
				try {
					list.add(new URI(urlString));
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}
			}
		} catch (RegistryException e) {
			throw new AiravataAPIInvocationException(e);
		}
		return list;
	}

	@Override
	public URI getGFaCURL()  throws AiravataAPIInvocationException{
		try {
			return getClient().getClientConfiguration().getGfacURL().toURI();
		} catch (URISyntaxException e) {
			throw new AiravataAPIInvocationException(e);
		}
	}

	@Override
	public URI getGFaCURL(URI defaultURL)  throws AiravataAPIInvocationException{
		if (getGFaCURL()==null){
			return defaultURL;	
		}
		return getGFaCURL();
	}

	@Override
	public List<URI> getWorkflowInterpreterServiceURLs()  throws AiravataAPIInvocationException{
		try {
			return getClient().getRegistry().getInterpreterServiceURLList();
		} catch (RegistryException e) {
			throw new AiravataAPIInvocationException(e);
		}
	}



	public AiravataClient getClient() {
		return client;
	}

	public void setClient(AiravataClient client) {
		this.client = client;
	}

	@Override
	public URI getWorkflowInterpreterServiceURL()
			throws AiravataAPIInvocationException {
		try {
			return getClient().getClientConfiguration().getXbayaServiceURL().toURI();
		} catch (URISyntaxException e) {
			throw new AiravataAPIInvocationException(e);
		}
	}

	@Override
	public URI getWorkflowInterpreterServiceURL(URI defaultURL)
			throws AiravataAPIInvocationException {
		if (getWorkflowInterpreterServiceURL()==null){
			return defaultURL;	
		}
		return getWorkflowInterpreterServiceURL();
		
	}

	@Override
	public List<URI> getMessageBoxServiceURLs()
			throws AiravataAPIInvocationException {
		try {
			return getClient().getRegistry().getMessageBoxServiceURLList();
		} catch (RegistryException e) {
			throw new AiravataAPIInvocationException(e);
		}
	}

	@Override
	public URI getMessageBoxServiceURL() throws AiravataAPIInvocationException {
		try {
			return getClient().getClientConfiguration().getMessageboxURL().toURI();
		} catch (URISyntaxException e) {
			throw new AiravataAPIInvocationException(e);
		}
	}

	@Override
	public URI getMessageBoxServiceURL(URI defaultURL)
			throws AiravataAPIInvocationException {
		if (getMessageBoxServiceURL()==null){
			return defaultURL;	
		}
		return getMessageBoxServiceURL();
	}

	@Override
	public List<URI> getEventingServiceURLs()
			throws AiravataAPIInvocationException {
		try {
			return getClient().getRegistry().getEventingServiceURLList();
		} catch (RegistryException e) {
			throw new AiravataAPIInvocationException(e);
		}
	}

	@Override
	public URI getEventingServiceURL() throws AiravataAPIInvocationException {
		try {
			return getClient().getClientConfiguration().getMessagebrokerURL().toURI();
		} catch (URISyntaxException e) {
			throw new AiravataAPIInvocationException(e);
		}
	}

	@Override
	public URI getEventingServiceURL(URI defaultURL)
			throws AiravataAPIInvocationException {
		if (getEventingServiceURL()==null){
			return defaultURL;	
		}
		return getEventingServiceURL();
	}

	@Override
	public URI getRegistryURL() throws AiravataAPIInvocationException {
		try {
			return getClient().getClientConfiguration().getJcrURL().toURI();
		} catch (URISyntaxException e) {
			throw new AiravataAPIInvocationException(e);
		}
	}

	@Override
	public URI getRegistryURL(URI defaultURL)
			throws AiravataAPIInvocationException {
		if (getRegistryURL()==null){
			return defaultURL;	
		}
		return getRegistryURL();
	}

}