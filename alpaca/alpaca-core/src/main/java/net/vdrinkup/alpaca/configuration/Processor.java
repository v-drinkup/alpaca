/*******************************************************************************
 * Copyright (c) 2013 Corporation and others.
 * 
 * Contributors:
 *     XXX Corporation 
 *******************************************************************************/
package net.vdrinkup.alpaca.configuration;


/**
 *
 * <p></p>
 * @author liubing
 * Date 2013-11-13
 */
public interface Processor {
	
	public < T > void process( T t ) throws Exception;

}
