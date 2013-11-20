/*******************************************************************************
 * Copyright (c) 2013 Corporation and others.
 * 
 * Contributors:
 *     XXX Corporation 
 *******************************************************************************/
package net.vdrinkup.alpaca;


/**
 *
 * <p></p>
 * @author liubing
 * Date 2013-11-19
 */
public class ProviderNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4704159246539262138L;
	
	public ProviderNotFoundException( String message ) {
		super( message );
	}

	/**
	 * @param e
	 */
	public ProviderNotFoundException( Exception e ) {
		super( e );
	}

	/**
	 * @param implName
	 * @param e
	 */
	public ProviderNotFoundException( String message, Exception e ) {
		super( message, e );
	}

}
