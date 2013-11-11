/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package net.vdrinkup.alpaca.servicebus.protocol;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 *
 * <p></p>
 * @author liubing
 * Date Nov 5, 2013
 */
@WebService
public interface HelloWorld1 {
	@WebMethod( operationName="sayHello" )
	@WebResult( name = "return", targetNamespace="http://protocol.servicebus.alpaca.vdrinkup.net/" )
	public String sayHello();

}
