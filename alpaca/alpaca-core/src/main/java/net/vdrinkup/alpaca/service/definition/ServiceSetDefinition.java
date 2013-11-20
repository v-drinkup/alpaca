/*******************************************************************************
 * Copyright (c) 2013 Corporation and others.
 * 
 * Contributors:
 *     XXX Corporation 
 *******************************************************************************/
package net.vdrinkup.alpaca.service.definition;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import net.vdrinkup.alpaca.configuration.AbstractDefinition;

/**
 *
 * <p></p>
 * @author liubing
 * Date 2013-11-18
 */
@XmlRootElement( name = "service.set" )
public class ServiceSetDefinition extends AbstractDefinition {
	@XmlElementRef
	private List< ServiceDefinition > services = new LinkedList< ServiceDefinition >();

	public List< ServiceDefinition > getServices() {
		return services;
	}

	public void setServices( List< ServiceDefinition > services ) {
		this.services = services;
	}
	
}
