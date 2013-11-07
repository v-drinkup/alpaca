/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package net.vdrinkup.alpaca.servicebus.protocol.spi.ws.config;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import net.vdrinkup.alpaca.servicebus.protocol.config.RequestConfig;


/**
 * Web Service请求配置类
 * <p></p>
 * @author liubing
 * Date Nov 1, 2013
 */
@XmlRootElement( name = "request" )
public class WsRequestConfig extends RequestConfig {
	
	@XmlAttribute( name = "realization" )
	private Realization realization;
	
	@XmlAttribute( name = "resource" )
	private String resource;
	/**
	 * @return the realization
	 */
	public Realization getRealization() {
		return realization;
	}

	/**
	 * @param realization the realization to set
	 */
	public void setRealization( Realization realization ) {
		this.realization = realization;
	}

	/**
	 * @return the resource
	 */
	public String getResource() {
		return resource;
	}

	/**
	 * @param resource the resource to set
	 */
	public void setResource( String resource ) {
		this.resource = resource;
	}

}
