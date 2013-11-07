/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package net.vdrinkup.alpaca.servicebus.protocol.spi.ws;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * <p></p>
 * @author liubing
 * Date Nov 6, 2013
 */
public class WsConstants {
	
	public static final Set< String > ACTIVATION_NAMESPACES = new HashSet< String >();
	
	public static final List< String > SERVER_HTTP_TRANSPORT_IDS = new ArrayList< String >( 3 );
	
	public static final List< String > CLIENT_HTTP_TRANSPORT_IDS = new ArrayList< String >( 3 );
	
	static {
		ACTIVATION_NAMESPACES.add( "http://schemas.xmlsoap.org/soap/" );
		ACTIVATION_NAMESPACES.add( "http://schemas.xmlsoap.org/wsdl/soap/" );
		ACTIVATION_NAMESPACES.add( "http://schemas.xmlsoap.org/wsdl/soap12/" );
		ACTIVATION_NAMESPACES.add( "http://www.w3.org/2003/05/soap/bindings/HTTP/" );
		ACTIVATION_NAMESPACES.add( "http://schemas.xmlsoap.org/wsdl/soap/http" );
		
		SERVER_HTTP_TRANSPORT_IDS.add( "http://cxf.apache.org/bindings/xformat" );
		SERVER_HTTP_TRANSPORT_IDS.add( "http://schemas.xmlsoap.org/soap/http" );
		SERVER_HTTP_TRANSPORT_IDS.add( "http://schemas.xmlsoap.org/wsdl/http/" );
		SERVER_HTTP_TRANSPORT_IDS.add( "http://schemas.xmlsoap.org/wsdl/soap/http" );
		SERVER_HTTP_TRANSPORT_IDS.add( "http://www.w3.org/2003/05/soap/bindings/HTTP/" );
		SERVER_HTTP_TRANSPORT_IDS.add( "http://cxf.apache.org/transports/http/configuration" );
		
		CLIENT_HTTP_TRANSPORT_IDS.add( "http://schemas.xmlsoap.org/soap/http" );
		CLIENT_HTTP_TRANSPORT_IDS.add( "http://schemas.xmlsoap.org/wsdl/http/" );
		CLIENT_HTTP_TRANSPORT_IDS.add( "http://schemas.xmlsoap.org/wsdl/soap/http" );
		CLIENT_HTTP_TRANSPORT_IDS.add( "http://www.w3.org/2003/05/soap/bindings/HTTP/" );
		CLIENT_HTTP_TRANSPORT_IDS.add( "http://cxf.apache.org/transports/http/configuration" );
		CLIENT_HTTP_TRANSPORT_IDS.add( "http://cxf.apache.org/bindings/xformat</value>" );
	}

}
