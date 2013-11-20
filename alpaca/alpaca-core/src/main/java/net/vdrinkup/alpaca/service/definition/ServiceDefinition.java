/*******************************************************************************
 * Copyright (c) 2013 Corporation and others.
 * 
 * Contributors:
 *     XXX Corporation 
 *******************************************************************************/
package net.vdrinkup.alpaca.service.definition;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import net.vdrinkup.alpaca.configuration.AbstractDefinition;

/**
 * 
 * <p>
 * </p>
 * @author liubing Date 2013-11-18
 */
@XmlRootElement( name = "service" )
public abstract class ServiceDefinition extends AbstractDefinition {
	@XmlAttribute
	protected String name;
	@XmlAttribute
	protected String version;
	@XmlAttribute
	protected String clazz;

	public String getName() {
		return name;
	}

	public void setName( String name ) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion( String version ) {
		this.version = version;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz( String clazz ) {
		this.clazz = clazz;
	}

}
