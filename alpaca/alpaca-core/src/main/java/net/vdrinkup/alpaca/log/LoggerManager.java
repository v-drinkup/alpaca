/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package net.vdrinkup.alpaca.log;


/**
 *
 * <p></p>
 * @author liubing
 * Date Oct 28, 2013
 */
public class LoggerManager {
	
	private static final LoggerManager INSTANCE = new LoggerManager();
			
	private LogMBean mBean;
	
	private LoggerManager() {
	}
	
	public static LoggerManager getInstance() {
		return INSTANCE;
	}

	/**
	 * @param logMBean
	 */
	public void register( LogMBean logMBean ) {
		synchronized ( INSTANCE ) {
			if ( logMBean != null ) {
				mBean = logMBean;
			}
		}
	}
	
	public LogMBean getMBean() {
		return mBean;
	}
	
}
