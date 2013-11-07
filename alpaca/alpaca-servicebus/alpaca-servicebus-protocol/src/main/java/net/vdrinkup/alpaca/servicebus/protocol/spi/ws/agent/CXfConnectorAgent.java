/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package net.vdrinkup.alpaca.servicebus.protocol.spi.ws.agent;

import net.vdrinkup.alpaca.servicebus.protocol.Connection;
import net.vdrinkup.alpaca.servicebus.protocol.ConnectionException;
import net.vdrinkup.alpaca.servicebus.protocol.config.ProtocolConfig;
import net.vdrinkup.alpaca.servicebus.protocol.spi.ws.WsConstants;
import net.vdrinkup.alpaca.servicebus.protocol.spi.ws.config.WsProtocolConfig;
import net.vdrinkup.alpaca.servicebus.protocol.spi.ws.config.WsRequestConfig;

import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.binding.soap.SoapBindingFactory;
import org.apache.cxf.binding.soap.SoapTransportFactory;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;


/**
 * 
 * <p>
 * </p>
 * 
 * @author liubing Date Nov 3, 2013
 */
public class CXfConnectorAgent implements Connection {

	private WsProtocolConfig config;
	
	private JaxWsServerFactoryBean server;

//	private volatile Tomcat embedded;

	private boolean running = false;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jd.wms.servicebus.protocol.Connection#isShutdown()
	 */
	@Override
	public boolean isShutdown() {
		return ! running;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jd.wms.servicebus.protocol.Connection#isStartup()
	 */
	@Override
	public boolean isStartup() {
		return running;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jd.wms.servicebus.protocol.Connection#receive()
	 */
	@Override
	public < R > R receive() throws Exception {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jd.wms.servicebus.protocol.Connection#send(java.lang.Object)
	 */
	@Override
	public < T, R > R send( T t ) throws Exception {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jd.wms.servicebus.protocol.Connection#start()
	 */
	@Override
	public void start() throws Exception {
		if ( isStartup() ) {
			return ;
		}
		if ( server == null ) {
			server = new JaxWsServerFactoryBean();
		}
		
		Bus bus = null;
		final ClassLoader oldCL = Thread.currentThread().getContextClassLoader();
		try {
			Thread.currentThread().setContextClassLoader( BusFactory.class.getClassLoader() );
			bus = BusFactory.newInstance().createBus();
		} finally {
			Thread.currentThread().setContextClassLoader( oldCL );
		}
		server.setBus( bus );
		final SoapBindingFactory sfb = new SoapBindingFactory();
		sfb.setActivationNamespaces( WsConstants.ACTIVATION_NAMESPACES );
		sfb.setBus( bus );
		server.setBindingFactory( sfb );
		final SoapTransportFactory stf = new SoapTransportFactory();
		stf.setTransportIds( WsConstants.SERVER_HTTP_TRANSPORT_IDS );
		stf.setBus( bus );
		server.setDestinationFactory( stf );
		server.setAddress( getConfig().getRequest().getUri() );
		final String className = ( ( WsRequestConfig ) getConfig().getRequest() ).getResource();
		try {
			Class< ? > clazz = Class.forName( className, false, this.getClass().getClassLoader() );
			server.setServiceClass( clazz );
			server.setServiceBean( clazz.newInstance() );
		} catch ( Exception e ) {
			throw new ConnectionException( e.getMessage() );
		}
		server.setStart( true );
		server.create();
		running = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jd.wms.servicebus.protocol.Connection#stop()
	 */
	@Override
	public void stop() throws Exception {
		if ( isShutdown() ) {
			return ;
		}
		this.server.setStart( false );
		this.server.destroy();
		running = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jd.wms.servicebus.protocol.Connection#getConfig()
	 */
	@SuppressWarnings( "unchecked" )
	@Override
	public < T extends ProtocolConfig > T getConfig() {
		return ( T ) this.config;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jd.wms.servicebus.protocol.Connection#setConfig(com.jd.wms.servicebus
	 * .protocol.config.ProtocolConfig)
	 */
	@Override
	public < T extends ProtocolConfig > void setConfig( T t ) {
		this.config = ( WsProtocolConfig ) t;
	}

}
