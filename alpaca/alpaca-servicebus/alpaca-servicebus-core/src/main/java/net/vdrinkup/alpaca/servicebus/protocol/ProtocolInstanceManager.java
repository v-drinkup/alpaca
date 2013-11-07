/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package net.vdrinkup.alpaca.servicebus.protocol;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.vdrinkup.alpaca.servicebus.protocol.config.ProtocolConfig;
import net.vdrinkup.alpaca.servicebus.protocol.config.SideEnum;
import net.vdrinkup.alpaca.servicebus.protocol.plugin.ProtocolPlugin;


/**
 * 协议实例管器
 * <p>
 * 该类用于保存运行时协议实例。
 * </p>
 * @author liubing
 * Date Nov 4, 2013
 */
public class ProtocolInstanceManager {
	
	public static volatile ProtocolInstanceManager _instance;
	
	private ProtocolInstanceManager() {
	}
	
	public static ProtocolInstanceManager getInstance() {
		if ( _instance == null ) {
			synchronized ( ProtocolInstanceManager.class ) {
				if ( _instance == null ) {
					_instance = new ProtocolInstanceManager();
				}
			}
		}
		return _instance;
	}

	private Map< String, Connection > registry = new ConcurrentHashMap< String, Connection >( 16 );
	
	@SuppressWarnings( "unchecked" )
	public void load( ProtocolConfig config ) throws Exception {
		if ( registry.containsKey( config.getName() ) ) {
			throw new IllegalArgumentException( "The instance named [" + config.getName() + "] has exist." );
		}
		final ProtocolPlugin plugin = ProtocolPluginManager.getInstance().lookup( config.getProtocolName() );
		Class< Connection > clazz = null;
		if ( config.getSide().equals( SideEnum.SERVER ) ) {
			clazz = ( Class< Connection > ) Class.forName( plugin.getServerConnection() );
		} else {
			clazz = ( Class< Connection > ) Class.forName( plugin.getClientConnection() );
		}
		Connection connection = clazz.newInstance();
		connection.setConfig( config );
		if ( config.isAutoRun() ) {
			connection.start();
		}
		registry.put( config.getName(), connection );
	}
	
	public void loadAll( Collection< ProtocolConfig > collection ) throws Exception {
		if ( collection != null ) {
			final Iterator< ProtocolConfig > iter = collection.iterator();
			while ( iter.hasNext() ) {
				load( iter.next() );
			}
		}
	}
	
	public Map< String, Connection > getAllInstance() {
		return registry;
	}

	/**
	 * @return
	 */
	public int size() {
		return registry.size();
	}

	/**
	 * @param string
	 */
	public Connection lookup( String key ) {
		if ( ! registry.containsKey( key ) ) {
			throw new NoSuchElementException( "No such element named [" + key + "] exist." );
		}
		return registry.get( key );
	}
	
}
