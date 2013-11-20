/*******************************************************************************
 * Copyright (c) 2013 Corporation and others.
 * 
 * Contributors:
 *     XXX Corporation 
 *******************************************************************************/
package net.vdrinkup.alpaca.transformation.config;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import net.vdrinkup.alpaca.configuration.Processor;
import net.vdrinkup.alpaca.configuration.ProcessorDefinition;

/**
 *
 * <p></p>
 * @author liubing
 * Date 2013-11-16
 */
@XmlRootElement( name = "element" )
public class ElementConfig extends ProcessorDefinition {
	@XmlAttribute
	protected String srcRef;
	@XmlAttribute
	protected String targetRef;

	public String getSrcRef() {
		return srcRef;
	}

	public void setSrcRef( String srcRef ) {
		this.srcRef = srcRef;
	}

	public String getTargetRef() {
		return targetRef;
	}

	public void setTargetRef( String targetRef ) {
		this.targetRef = targetRef;
	}

	/* (non-Javadoc)
	 * @see net.vdrinkup.alpaca.configuration.ProcessorDefinition#createProcessor()
	 */
	@Override
	public Processor createProcessor() {
		return null;
	}

}
