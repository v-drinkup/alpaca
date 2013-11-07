/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package net.vdrinkup.alpaca.servicebus.protocol.spi.ws;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.vdrinkup.alpaca.servicebus.protocol.Connection;
import net.vdrinkup.alpaca.servicebus.protocol.config.SideEnum;
import net.vdrinkup.alpaca.servicebus.protocol.spi.ws.agent.CXfConnectorAgent;
import net.vdrinkup.alpaca.servicebus.protocol.spi.ws.config.Realization;
import net.vdrinkup.alpaca.servicebus.protocol.spi.ws.proxy.CXFConnectorProxy;


/**
 *
 * <p></p>
 * @author liubing
 * Date Nov 2, 2013
 */
public class WsConnectorFactory {
	
	private static volatile WsConnectorFactory _instance;
	
	private WsConnectorFactory() {
	}
	
	public static WsConnectorFactory getInstance() {
		if ( _instance == null ) {
			synchronized ( WsConnectorFactory.class ) {
				if ( _instance == null ) {
					_instance = new WsConnectorFactory();
					_instance.init();
				}
			}
		}
		return _instance;
	}
	
	private Map< Realization, Connection > agents = new ConcurrentHashMap< Realization, Connection >( 16 );
	
	private Map< Realization, Connection > proxies = new ConcurrentHashMap< Realization, Connection >( 16 );
	
	private void init() {
		agents.put( Realization.CXF, new CXfConnectorAgent() );
		proxies.put( Realization.CXF, new CXFConnectorProxy() );
	}

	private Connection createAgent( Realization r ) {
		return agents.get( r );
	}
	
	private Connection createProxy( Realization r ) {
		return proxies.get( r );
	}

	/**
	 * @param r
	 * @param side
	 */
	public Connection create( Realization r, SideEnum side ) {
		Connection connection = null;
		if ( SideEnum.CLIENT.equals( side ) ) {
			connection = createProxy( r );
		} else if ( SideEnum.SERVER.equals( side ) ) {
			connection = createAgent( r );
		} else {
			throw new IllegalArgumentException( "Invalid SideEnum value." );
		}
		return connection;
	}
	
}
