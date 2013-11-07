/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package net.vdrinkup.alpaca.servicebus.protocol;

/**
 * 连接器异常
 * <p></p>
 * @author liubing
 * Date Nov 5, 2013
 */
public class ConnectionException extends Exception {

	private static final long serialVersionUID = 1170821277775712135L;
	
	public ConnectionException( String message ) {
		super( message );
	}
	
}
