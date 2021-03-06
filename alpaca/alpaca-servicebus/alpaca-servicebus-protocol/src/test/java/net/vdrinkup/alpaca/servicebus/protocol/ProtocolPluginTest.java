/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package net.vdrinkup.alpaca.servicebus.protocol;

import junit.framework.Assert;

import net.vdrinkup.alpaca.Env;
import net.vdrinkup.alpaca.servicebus.protocol.ProtocolPluginManager;
import net.vdrinkup.alpaca.servicebus.protocol.plugin.ProtocolPlugin;

import org.junit.Before;
import org.junit.Test;


/**
 *
 * <p></p>
 * @author liubing
 * Date Nov 1, 2013
 */
public class ProtocolPluginTest {
	@Before
	public void setup() {
		String uri = "/home/bjyfliubing/Test";
		System.out.println( uri );
		Env.getInstance().init( uri );
	}
	
	@Test
	public void testPluginInit() {
		try {
			ProtocolPluginManager.getInstance().start();
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		ProtocolPlugin plugin = ProtocolPluginManager.getInstance().lookup( "ws" );
		Assert.assertNotNull( plugin );
		Assert.assertEquals( plugin.getProtocol(), "ws" );
	}

}
