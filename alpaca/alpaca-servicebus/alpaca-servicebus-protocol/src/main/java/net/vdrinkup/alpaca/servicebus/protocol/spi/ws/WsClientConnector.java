/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package net.vdrinkup.alpaca.servicebus.protocol.spi.ws;

import net.vdrinkup.alpaca.servicebus.protocol.Connection;
import net.vdrinkup.alpaca.servicebus.protocol.ConnectionException;
import net.vdrinkup.alpaca.servicebus.protocol.config.ProtocolConfig;
import net.vdrinkup.alpaca.servicebus.protocol.config.SideEnum;
import net.vdrinkup.alpaca.servicebus.protocol.spi.ws.config.Realization;
import net.vdrinkup.alpaca.servicebus.protocol.spi.ws.config.WsProtocolConfig;
import net.vdrinkup.alpaca.servicebus.protocol.spi.ws.config.WsRequestConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * <p></p>
 * @author liubing
 * Date Nov 5, 2013
 */
public class WsClientConnector implements Connection {
	
	private static Logger LOG = LoggerFactory.getLogger( WsClientConnector.class );
	
	private Connection proxy;
	
	private WsProtocolConfig config;
	
	public WsClientConnector() {
	}

	/* (non-Javadoc)
	 * @see com.jd.wms.servicebus.protocol.Connection#isShutdown()
	 */
	@Override
	public boolean isShutdown() {
		return proxy == null ? true : proxy.isShutdown();
	}

	/* (non-Javadoc)
	 * @see com.jd.wms.servicebus.protocol.Connection#isStartup()
	 */
	@Override
	public boolean isStartup() {
		return proxy == null ? false : proxy.isStartup();
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
	@Override
	public < T, R > R send( T t ) throws Exception {
		if ( proxy == null ) {
			throw new ConnectionException( "No instance can be invoked." );
		}
		return proxy.send( t );
	}

	/* (non-Javadoc)
	 * @see com.jd.wms.servicebus.protocol.Connection#start()
	 */
	@Override
	public synchronized void start() throws Exception {
		if ( isStartup() ) {
			LOG.warn( "The instance named [" + getConfig().getName() + "] has been started." );
			return ;
		}
		if ( proxy == null ) {
			initProxy();
		}
		proxy.start();
		LOG.info( new StringBuilder( "The instance of WsClientConnector named [" ).append( 
				getConfig().getName() ).append( "] started successfully." ).toString() );
	}

	/* (non-Javadoc)
	 * @see com.jd.wms.servicebus.protocol.Connection#stop()
	 */
	@Override
	public synchronized void stop() throws Exception {
		if ( isShutdown() ) {
			LOG.warn( "The instance named [" + getConfig().getName() + "] has been started." );
			return ;
		}
		if ( proxy != null ) {
			proxy.stop();
		}
		LOG.info( new StringBuilder( "The instance of WsClientConnector named [" ).append( 
				getConfig().getName() ).append( "] stopped successfully." ).toString() );
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

	/**
	 * 
	 */
	private void initProxy() {
		final Realization r = ( ( WsRequestConfig ) this.config.getRequest() ).getRealization();
		final SideEnum side = this.config.getSide();
		this.proxy = WsConnectorFactory.getInstance().create( r, side );
		this.proxy.setConfig( getConfig() );
	}

}
