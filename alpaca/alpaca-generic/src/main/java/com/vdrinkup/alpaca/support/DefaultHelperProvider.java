/*******************************************************************************
 * Copyright (c) 2013 Corporation and others.
 * 
 * Contributors:
 *     XXX Corporation 
 *******************************************************************************/
package com.vdrinkup.alpaca.support;

import com.vdrinkup.alpaca.support.log.Log4jMBean;
import com.vdrinkup.alpaca.support.service.DefaultServiceManager;

import net.vdrinkup.alpaca.HelperProvider;
import net.vdrinkup.alpaca.log.LogMBean;
import net.vdrinkup.alpaca.service.ServiceManager;

/**
 *
 * <p></p>
 * @author liubing
 * Date 2013-11-19
 */
public class DefaultHelperProvider extends HelperProvider {
	
	protected ServiceManager serviceManager;
	
	protected LogMBean logMBean;
	
	public DefaultHelperProvider() {
		serviceManager = new DefaultServiceManager();
		logMBean = new Log4jMBean();
	}

	/* (non-Javadoc)
	 * @see net.vdrinkup.alpaca.HelperProvider#serviceManager()
	 */
	@Override
	public ServiceManager serviceManager() {
		return serviceManager;
	}
	
	public LogMBean logMBean() {
		return logMBean;
	}

}
