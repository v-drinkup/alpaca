/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package net.vdrinkup.alpaca.servicebus.protocol.plugin;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import net.vdrinkup.alpaca.servicebus.protocol.ProtocolConfigProcessor;


/**
 * 通讯协议插件类
 * <p>
 * 用于封装协议插件的相关操作类
 * </p>
 * @author liubing
 * Date Nov 1, 2013
 */
@XmlRootElement( name = "plugin" )
@XmlAccessorType( XmlAccessType.FIELD )
public class ProtocolPlugin {
	@XmlAttribute( required = true )
	private String protocol;
	@XmlElement( name = "config-processor", required = true )
	private String processorClazz;
	@XmlElement( name = "connection-server", required = true )
	private String serverConnection;
	@XmlElement( name = "connection-client", required = true )
	private String clientConnection;
	
	@XmlTransient
	private volatile ProtocolConfigProcessor configProcessor;
	
	/**
	 * @return the protocol
	 */
	public String getProtocol() {
		return protocol;
	}

	/**
	 * @param protocol the protocol to set
	 */
	public void setProtocol( String protocol ) {
		this.protocol = protocol;
	}
	
	/**
	 * @return the processorClazz
	 */
	public String getProcessorClazz() {
		return processorClazz;
	}

	/**
	 * @param processorClazz the processorClazz to set
	 */
	public void setProcessorClazz( String processorClazz ) {
		this.processorClazz = processorClazz;
	}

	/**
	 * @return the serverConnection
	 */
	public String getServerConnection() {
		return serverConnection;
	}

	/**
	 * @param serverConnection the serverConnection to set
	 */
	public void setServerConnection( String serverConnection ) {
		this.serverConnection = serverConnection;
	}

	/**
	 * @return the clientConnection
	 */
	public String getClientConnection() {
		return clientConnection;
	}

	/**
	 * @param clientConnection the clientConnection to set
	 */
	public void setClientConnection( String clientConnection ) {
		this.clientConnection = clientConnection;
	}

	public ProtocolConfigProcessor getConfigProcessor() throws Exception {
		if ( configProcessor == null ) {
			synchronized ( this ) {
				if ( configProcessor == null ) {
					Class< ? > clazz =  Class.forName( this.processorClazz );
					configProcessor = ( ProtocolConfigProcessor ) clazz.newInstance();
				}
			}
		}
		return configProcessor;
	}

}
