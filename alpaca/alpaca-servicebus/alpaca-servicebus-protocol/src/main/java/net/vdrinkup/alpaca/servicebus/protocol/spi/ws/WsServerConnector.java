/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package net.vdrinkup.alpaca.servicebus.protocol.spi.ws;

import net.vdrinkup.alpaca.servicebus.protocol.Connection;
import net.vdrinkup.alpaca.servicebus.protocol.config.ProtocolConfig;
import net.vdrinkup.alpaca.servicebus.protocol.config.SideEnum;
import net.vdrinkup.alpaca.servicebus.protocol.spi.ws.config.Realization;
import net.vdrinkup.alpaca.servicebus.protocol.spi.ws.config.WsProtocolConfig;
import net.vdrinkup.alpaca.servicebus.protocol.spi.ws.config.WsRequestConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * WebService服务端连接器
 * <p>
 * 实现<CODE>Connection</CODE>接口
 * </p>
 * @author liubing
 * Date Nov 2, 2013
 */
public class WsServerConnector implements Connection {
	
	private static Logger LOG = LoggerFactory.getLogger( WsServerConnector.class );
	
	private WsProtocolConfig config;
	
	private Connection agent;
			
	/* (non-Javadoc)
	 * @see com.jd.wms.servicebus.protocol.Connection#isShutdown()
	 */
	@Override
	public boolean isShutdown() {
		return agent == null ? true : agent.isShutdown();
	}

	/* (non-Javadoc)
	 * @see com.jd.wms.servicebus.protocol.Connection#isStartup()
	 */
	@Override
	public boolean isStartup() {
		return agent == null ? false : agent.isStartup();
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
		return null;
	}

	/* (non-Javadoc)
	 * @see com.jd.wms.servicebus.protocol.Connection#start()
	 */
	@Override
	public synchronized void start() throws Exception {
		if ( isStartup() ) {
			LOG.warn( "The instance named " + getConfig().getName() +  " has been started." );
			return ;
		}
		if ( agent == null ) {
			initAgent();
		}
		this.agent.start();
		LOG.info( new StringBuilder( "The instance of WsServerConnector named [" ).append( 
				getConfig().getName() ).append( "] started successfully." ).toString() );
	}

	/* (non-Javadoc)
	 * @see com.jd.wms.servicebus.protocol.Connection#stop()
	 */
	@Override
	public synchronized void stop() throws Exception {
		if ( isShutdown() ) {
			LOG.warn( "The instance of WsServerConnector has been stopped." );
		}
		if ( this.agent != null ) {
			this.agent.stop();
		}
		LOG.info( new StringBuilder( "The instance of WsServerConnector named [" ).append( 
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
	 * Initialize Agent
	 */
	private void initAgent() {
		final Realization r = ( ( WsRequestConfig ) getConfig().getRequest() ).getRealization();
		final SideEnum side = getConfig().getSide();
		this.agent = WsConnectorFactory.getInstance().create( r, side );
		this.agent.setConfig( getConfig() );
	}

}
