/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package net.vdrinkup.alpaca.servicebus.protocol.spi.ws.config;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

/**
 *
 * <p></p>
 * @author liubing
 * Date Nov 3, 2013
 */
@XmlEnum( String.class )
public enum WsStrategy {

	@XmlEnumValue( value = "pojo" )
	POJO,
	@XmlEnumValue( value = "wsdl" )
	WSDL
	
}
