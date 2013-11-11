/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package net.vdrinkup.alpaca.framework;

import net.vdrinkup.alpaca.Env;
import net.vdrinkup.alpaca.NodeEnum;

/**
 * 流程引擎工厂
 * <p>
 * </p>
 * @author liubing
 * Date Nov 9, 2013
 */
public class FlowEngineFactory {
	
	private static volatile FlowEngineFactory _instance;
	
	private volatile FlowEngine currentEngine;

	private FlowEngineFactory() {
	}
	
	public static FlowEngineFactory getInstance() {
		if ( _instance == null ) {
			synchronized ( FlowEngineFactory.class ) {
				if ( _instance == null ) {
					_instance = new FlowEngineFactory();
				}
			}
		}
		return _instance;
	}
	
	public FlowEngine getEngine() {
		if ( currentEngine == null ) {
			synchronized ( this ) {
				if ( currentEngine == null ) {
					final String nodeType = Env.getInstance().getPropertyValue( Env.SpecificKeys.NODE );
					final int node = Integer.parseInt( nodeType , 10 );
					currentEngine = new DefaultFlowEngine( NodeEnum.values()[ node ].name() );
				}
			}
		}
		return currentEngine;
	}
	
}
