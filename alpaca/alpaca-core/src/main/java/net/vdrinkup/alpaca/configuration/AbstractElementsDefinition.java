/*******************************************************************************
 * Copyright (c) 2013 Corporation and others.
 * 
 * Contributors:
 *     XXX Corporation 
 *******************************************************************************/
package net.vdrinkup.alpaca.configuration;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlElementRef;

/**
 *
 * <p></p>
 * @author liubing
 * Date 2013-11-13
 */
public abstract class AbstractElementsDefinition extends ProcessorDefinition {
	@XmlElementRef
	protected List< ProcessorDefinition > processors = new LinkedList< ProcessorDefinition >();

	public List< ProcessorDefinition > getProcessors() {
		return processors;
	}

	public void setProcessors( List< ProcessorDefinition > processors ) {
		this.processors = processors;
	} 

}
