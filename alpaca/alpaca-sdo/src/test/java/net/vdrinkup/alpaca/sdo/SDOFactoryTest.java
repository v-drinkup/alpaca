/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package net.vdrinkup.alpaca.sdo;

import junit.framework.Assert;
import net.vdrinkup.alpaca.Env;

import org.junit.Before;
import org.junit.Test;

import commonj.sdo.DataObject;
import commonj.sdo.helper.DataFactory;

/**
 *
 * <p></p>
 * @author liubing
 * Date Nov 8, 2013
 */
public class SDOFactoryTest {
	
	@Before
	public void setup() {
		Env.getInstance().init( "/home/bjyfliubing/Test/server_conf" );
	}
	
	@Test
	public void testInit() {
		SDOFactory.getInstance();
		DataObject instance = DataFactory.INSTANCE.create( "http://wave.wms.jd.com/domainModelDefinion", "obShipmentMain" );
		Assert.assertNotNull( instance );
	}

}
