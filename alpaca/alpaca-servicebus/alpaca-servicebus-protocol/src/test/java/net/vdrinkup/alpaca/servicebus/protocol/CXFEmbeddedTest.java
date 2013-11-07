/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package net.vdrinkup.alpaca.servicebus.protocol;

import java.util.Map;

import junit.framework.Assert;

import net.vdrinkup.alpaca.Env;
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
 * @author liubing Date Nov 3, 2013
 */
public class CXFEmbeddedTest {
	@Before
	public void setup() {
		Env.getInstance().init( "/home/bjyfliubing/Test/server_conf" );
		try {
			ProtocolPluginManager.getInstance().start();
			ProtocolConfigManager.getInstance().readAll();
			System.out.println( ProtocolConfigManager.getInstance().getAll().toString() );
		} catch ( Exception e ) {
			e.printStackTrace();
		}
	}
	@Test
	public void cxfServerTest() {
		try {
			ProtocolInstanceManager.getInstance().loadAll( ProtocolConfigManager.getInstance().getAll().values() );
			Map< String, Connection > instances = ProtocolInstanceManager.getInstance().getAllInstance();
			System.out.println( instances.toString() );
			Assert.assertTrue( ProtocolInstanceManager.getInstance().size() == 1 );
		} catch ( Exception e ) {
			e.printStackTrace();
		}
	}

//	public static void main( String... args ) throws Exception {
//		Tomcat tomcat = new Tomcat();
//		tomcat.setPort( 8080 );
//
//		final Context context = tomcat.addContext( "/testcxf", "/home/bjyfliubing/Test" );
//
//		// Add AprLifecycleListener
//		StandardServer server = ( StandardServer ) tomcat.getServer();
//		AprLifecycleListener listener = new AprLifecycleListener();
//		server.addLifecycleListener( listener );
//		Tomcat.addServlet( context, "", new HelloCxfServlet() );
//		context.addServletMapping("/*", "");
//		tomcat.start();
//		tomcat.getServer().await();
//	}

}

//class HelloCxfServlet extends CXFNonSpringServlet {
//
//	private static final long serialVersionUID = -8142679270251742597L;
//	
//	 @Override
//     protected void service(HttpServletRequest req, HttpServletResponse resp)
//             throws ServletException, IOException {
//         Writer w = resp.getWriter();
//         w.write("Hello, World!");
//         w.flush();
//     }
//
//	public void loadBus( ServletConfig servletConfig ) throws ServletException {
//		super.loadBus( servletConfig );
//
//		final Bus bus = getBus();
//		BusFactory.setDefaultBus( bus );
//
//		final ServerFactoryBean sfb = new ServerFactoryBean();
//		sfb.setServiceClass( HelloWorldImpl.class );
//		sfb.setServiceBean( new HelloWorldImpl() );
//		sfb.setAddress( "/hello" );
//		sfb.create();
//		
//		final ServerFactoryBean sfb1 = new ServerFactoryBean();
//		sfb1.setServiceClass( HelloWorldImpl.class );
//		sfb1.setServiceBean( new HelloWorldImpl() );
//		sfb1.setAddress( "/hello1" );
//		sfb1.create();
//	}
//
//}
