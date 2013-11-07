/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package net.vdrinkup.alpaca.servicebus.framework;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import net.vdrinkup.alpaca.Env;


/**
 *
 * <p></p>
 * @author liubing
 * Date Nov 5, 2013
 */
public class DefaultRuntimeFramework implements Framework {
	
	private String belongTo;
	
	private ThreadPoolExecutor pool;
	
	public DefaultRuntimeFramework( String belongTo ) {
	}

}
