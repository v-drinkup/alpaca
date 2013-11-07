/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package com.jd.wms.servicebus.protocol;

import java.util.Map;

import junit.framework.Assert;

import net.vdrinkup.alpaca.Env;
import net.vdrinkup.alpaca.servicebus.protocol.ProtocolConfigManager;
import net.vdrinkup.alpaca.servicebus.protocol.ProtocolPluginManager;
import net.vdrinkup.alpaca.servicebus.protocol.config.ProtocolConfig;

import org.junit.Before;
import org.junit.Test;


/**
 *
 * <p></p>
 * @author liubing
 * Date Nov 1, 2013
 */
public class ProtocolConfigTest {
	@Before
	public void setup() {
		Env.getInstance().init( "/home/bjyfliubing/Test" );
		try {
			ProtocolPluginManager.getInstance().start();
		} catch ( Exception e ) {
			e.printStackTrace();
		}
	}
	@Test
	public void tsetConfig() {
		ProtocolConfigManager.getInstance().readAll();
		final Map< String, ProtocolConfig > configs = ProtocolConfigManager.getInstance().getAll();
		Assert.assertNotNull( configs );
		Assert.assertTrue( configs.size() == 1 );
		Assert.assertNotNull( configs.get( "testws" ) );
	}

}
