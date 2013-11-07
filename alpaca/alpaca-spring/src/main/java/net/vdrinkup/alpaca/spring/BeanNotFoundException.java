/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package net.vdrinkup.alpaca.spring;

/**
 *
 * <p></p>
 * @author liubing
 * Date Nov 5, 2013
 */
public class BeanNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 7589424305880065657L;
	
	public BeanNotFoundException( String message ) {
		super( message );
	}

}
