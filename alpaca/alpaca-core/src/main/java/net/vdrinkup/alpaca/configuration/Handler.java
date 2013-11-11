/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package net.vdrinkup.alpaca.configuration;

/**
 * 配置项处理器
 * <p></p>
 * @author liubing
 * Date Nov 10, 2013
 */
public interface Handler {
	
	public < T > void handle( T t ) throws Exception;
	
}
