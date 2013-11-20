/*******************************************************************************
 * Copyright (c) 2013 Corporation and others.
 * 
 * Contributors:
 *     XXX Corporation 
 *******************************************************************************/
package net.vdrinkup.alpaca.configuration;


/**
 *
 * <p></p>
 * @author liubing
 * Date 2013-11-13
 */
public abstract class AbstractProcessor< T extends ProcessorDefinition > implements Processor {
	
	protected T definition;
	
	public AbstractProcessor( T t ) {
		this.definition = t;
	}

	public T getDefinition() {
		return definition;
	}

}
