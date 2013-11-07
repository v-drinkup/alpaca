/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package net.vdrinkup.alpaca.servicebus.protocol.spi.ws.proxy;

import net.vdrinkup.alpaca.context.DataContext;
import net.vdrinkup.alpaca.servicebus.Constants;
import net.vdrinkup.alpaca.servicebus.protocol.Connection;
import net.vdrinkup.alpaca.servicebus.protocol.config.ProtocolConfig;
import net.vdrinkup.alpaca.servicebus.protocol.spi.ws.WsConstants;
import net.vdrinkup.alpaca.servicebus.protocol.spi.ws.config.WsProtocolConfig;
import net.vdrinkup.alpaca.servicebus.protocol.spi.ws.config.WsRequestConfig;

import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.binding.soap.SoapBindingFactory;
import org.apache.cxf.binding.soap.SoapTransportFactory;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsClientFactoryBean;


/**
 *
 * <p></p>
 * @author liubing
 * Date Nov 5, 2013
 */
public class CXFConnectorProxy implements Connection {
	
	private boolean running = false;
	
	private WsProtocolConfig config;
	
	private JaxWsClientFactoryBean client;
		
	private Client proxy;
	
	public CXFConnectorProxy() {
		this.client = new JaxWsClientFactoryBean();
	}

	/* (non-Javadoc)
	 * @see com.jd.wms.servicebus.protocol.Connection#isShutdown()
	 */
	@Override
	public boolean isShutdown() {
		return ! running;
	}

	/* (non-Javadoc)
	 * @see com.jd.wms.servicebus.protocol.Connection#isStartup()
	 */
	@Override
	public boolean isStartup() {
		return running;
	}

	/* (non-Javadoc)
	 * @see com.jd.wms.servicebus.protocol.Connection#receive()
	 */
	@Override
	public < R > R receive() throws Exception {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.jd.wms.servicebus.protocol.Connection#send(java.lang.Object)
	 */
	@SuppressWarnings( "unchecked" )
	@Override
	public < T, R > R send( T t ) throws Exception {
		final DataContext context = ( DataContext ) t;
		final String operationName = ( String ) context.getAttribute( Constants.Protocol.WEB_SERVICE_OPERATION_NAME );
		return ( R ) proxy.invoke( operationName, context.getPayload() );
	}

	/* (non-Javadoc)
	 * @see com.jd.wms.servicebus.protocol.Connection#start()
	 */
	@Override
	public void start() throws Exception {
		if ( isStartup() ) {
			return ;
		}
		ClassLoader oldCL = Thread.currentThread().getContextClassLoader();
		Bus bus;
		try {
			Thread.currentThread().setContextClassLoader( BusFactory.class.getClassLoader() );
			bus = BusFactory.newInstance().createBus();
			client.setBus( bus );
		} finally {
			Thread.currentThread().setContextClassLoader( oldCL );
		}
		final SoapBindingFactory sfb = new SoapBindingFactory();
		sfb.setActivationNamespaces( WsConstants.ACTIVATION_NAMESPACES );
		sfb.setBus( bus );
		client.setBindingFactory( sfb );
		final SoapTransportFactory stf =  new SoapTransportFactory();
		stf.setTransportIds( WsConstants.SERVER_HTTP_TRANSPORT_IDS );
		stf.setBus( bus );
		client.setDestinationFactory( stf );
		final WsRequestConfig request = ( ( WsRequestConfig ) getConfig().getRequest() );
		client.setAddress( request.getUri() );
		if ( request.getResource() == null || "".equals( request.getResource() ) ) {
			throw new IllegalArgumentException(  );
		}
		final String resource = request.getResource();
		final Class< ? > clazz = Class.forName( resource, false, this.getClass().getClassLoader() );
		client.setServiceClass( clazz );
		client.getInInterceptors().add( new LoggingInInterceptor() );
		client.getOutInterceptors().add( new LoggingOutInterceptor() );
		proxy = client.create();
		running = true;
	}

	/* (non-Javadoc)
	 * @see com.jd.wms.servicebus.protocol.Connection#stop()
	 */
	@Override
	public void stop() throws Exception {
		if ( isShutdown() ) {
			return ;
		}
		client.getBus().shutdown( true );
		proxy = null;
		running = false;
	}

	/* (non-Javadoc)
	 * @see com.jd.wms.servicebus.protocol.Connection#getConfig()
	 */
	@SuppressWarnings( "unchecked" )
	@Override
	public < T extends ProtocolConfig > T getConfig() {
		return ( T ) this.config;
	}

	/* (non-Javadoc)
	 * @see com.jd.wms.servicebus.protocol.Connection#setConfig(com.jd.wms.servicebus.protocol.config.ProtocolConfig)
	 */
	@Override
	public < T extends ProtocolConfig > void setConfig( T t ) {
		this.config = ( WsProtocolConfig ) t;
	}

}
