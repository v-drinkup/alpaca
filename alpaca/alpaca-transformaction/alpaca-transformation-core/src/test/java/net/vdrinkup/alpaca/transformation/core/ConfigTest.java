/*******************************************************************************
 * Copyright (c) 2013 Corporation and others.
 * 
 * Contributors:
 *     XXX Corporation 
 *******************************************************************************/
package net.vdrinkup.alpaca.transformation.core;

import java.util.List;

import javax.xml.bind.JAXBContext;

import net.vdrinkup.alpaca.configuration.ProcessorDefinition;
import net.vdrinkup.alpaca.transformation.config.TransformationConfig;

import org.junit.Test;
import org.w3c.dom.Element;

/**
 *
 * <p></p>
 * @author liubing
 * Date 2013-11-16
 */
public class ConfigTest {
	@Test
	public void testConfig() throws Exception {
		JAXBContext jaxbContext = JAXBContext.newInstance( TransformationConfig.class.getPackage().getName() );
		TransformationConfig config = ( TransformationConfig ) jaxbContext.createUnmarshaller().unmarshal( ConfigTest.class.getResource( "test.xml" ) );
		List< ? > list = config.getElements();
		for ( Object object : list ) {
			if ( object instanceof String ) {
				System.out.println( object );
			} else if ( object instanceof ProcessorDefinition ) {
				( ( ProcessorDefinition ) object ).createProcessor();
			} else if ( object instanceof Element ) {
				System.out.println( object.getClass().getName() );
			}
		}
	}
	
}
