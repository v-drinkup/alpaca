/*******************************************************************************
 * Copyright (c) 2013 Corporation and others.
 * 
 * Contributors:
 *     XXX Corporation 
 *******************************************************************************/
package net.vdrinkup.alpaca.service;

/**
 *
 * <p></p>
 * @author liubing
 * Date 2013-11-11
 */
public class InvokeException extends Exception {

	private static final long serialVersionUID = -7661430517413279772L;
	
	public InvokeException() {
		super();
	}
	
	public InvokeException( String message ) {
		super( message );
	}
	
	public InvokeException( Exception e ) {
		super( e );
	}
	
	public InvokeException( Throwable t ) {
		super( t );
	}

}
