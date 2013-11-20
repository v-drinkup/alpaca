/*******************************************************************************
 * Copyright (c) 2013 Corporation and others.
 * 
 * Contributors:
 *     XXX Corporation 
 *******************************************************************************/
package com.vdrinkup.alpaca.support.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.vdrinkup.alpaca.service.KeyType;
import net.vdrinkup.alpaca.service.ServiceEntry;
import net.vdrinkup.alpaca.service.ServiceManager;

/**
 * 默认服务管理器
 * <p>
 * 服务管理器接口{@link ServiceManager}的一般实现。
 * </p>
 * 
 * @author liubing Date 2013-11-19
 */
public class DefaultServiceManager implements ServiceManager {
	/**
	 * 服务注册表
	 */
	private Map< String, ServiceEntry > registry = new ConcurrentHashMap< String, ServiceEntry >();

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.vdrinkup.alpaca.service.ServiceManager#entries()
	 */
	@Override
	public Collection< ServiceEntry > entries() {
		return registry.values();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.vdrinkup.alpaca.service.ServiceManager#unregister(java.lang.String,
	 * net.vdrinkup.alpaca.service.KeyType)
	 */
	@Override
	public ServiceEntry unregister( String key, KeyType type ) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.vdrinkup.alpaca.service.ServiceManager#lookup(java.lang.String)
	 */
	@Override
	public ServiceEntry lookup( String id ) {
		return registry.get( id );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.vdrinkup.alpaca.service.ServiceManager#bind(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void bind( String id, String callerId ) {
		lookup( id ).addCaller( callerId );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.vdrinkup.alpaca.service.ServiceManager#unbind(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void unbind( String id, String callerId ) {
		lookup( id ).removeCaller( callerId );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.vdrinkup.alpaca.service.ServiceManager#showBind(java.lang.String)
	 */
	@Override
	public List< String > showBind( String id ) {
		return lookup( id ).getCallers();
	}

	@Override
	public void register( ServiceEntry entry ) {
		registry.put( entry.getId(), entry );
	}
}
