/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package net.vdrinkup.alpaca.configuration;

/**
 *
 * <p></p>
 * @author liubing
 * Date Nov 10, 2013
 */
public abstract class ProcessorDefinition extends AbstractDefinition {
	
	public abstract Processor createProcessor();

}
