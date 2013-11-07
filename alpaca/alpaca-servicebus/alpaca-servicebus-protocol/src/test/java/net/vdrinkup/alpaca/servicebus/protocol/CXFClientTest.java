/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package net.vdrinkup.alpaca.servicebus.protocol;

import java.util.Map;

import net.vdrinkup.alpaca.Env;
import net.vdrinkup.alpaca.context.DataContext;
import net.vdrinkup.alpaca.context.GenericDataContext;
import net.vdrinkup.alpaca.servicebus.Constants;
import net.vdrinkup.alpaca.servicebus.protocol.Connection;
import net.vdrinkup.alpaca.servicebus.protocol.ProtocolConfigManager;
import net.vdrinkup.alpaca.servicebus.protocol.ProtocolInstanceManager;
import net.vdrinkup.alpaca.servicebus.protocol.ProtocolPluginManager;

import org.junit.Before;
import org.junit.Test;


/**
 * 
 * <p>
 * </p>
 * 
 * @author liubing Date Nov 5, 2013
 */
public class CXFClientTest {
	@Before
	public void setup() {
		Env.getInstance().init( "/home/bjyfliubing/Test/client_conf" );
		try {
			ProtocolPluginManager.getInstance().start();
			ProtocolConfigManager.getInstance().readAll();
			System.out.println( ProtocolConfigManager.getInstance().getAll().toString() );
		} catch ( Exception e ) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testClient() {
		try {
			ProtocolInstanceManager.getInstance().loadAll( ProtocolConfigManager.getInstance().getAll().values() );
			Map< String, Connection > instances = ProtocolInstanceManager.getInstance().getAllInstance();
			System.out.println( instances.toString() );
			final Connection connection = ProtocolInstanceManager.getInstance().lookup( "testwsclient" );
			DataContext context = new GenericDataContext();
			context.addAttribute( Constants.Protocol.WEB_SERVICE_OPERATION_NAME, "sayHello" );
			context.setPayload( new Object[]{} );
			Object obj = connection.send( context );
			System.out.println( obj );
		} catch ( Exception e ) {
			e.printStackTrace();
		}
//		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
//
//		factory.setServiceClass( HelloWorld1.class );
//		factory.setAddress( "http://localhost:8080/testcxf/hello?wsdl" );
//		
//		
//		Object obj = factory.create();
//		try {
//			Method[] methodArray = obj.getClass().getDeclaredMethods();
//			Map< String, Method > methods = new HashMap< String, Method >( 16 );
//			for ( Method method : methodArray ) {
//				methods.put( method.getName(), method );
//			}
//			System.out.println( methods.get( "say" ).invoke( obj, new Object[]{} ) );
//		} catch ( SecurityException e ) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch ( IllegalArgumentException e ) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch ( IllegalAccessException e ) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch ( InvocationTargetException e ) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}
