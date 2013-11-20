/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package net.vdrinkup.alpaca.flow;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import net.vdrinkup.alpaca.context.DataContext;

/**
 *
 * <p></p>
 * @author liubing
 * Date Nov 10, 2013
 */
public class SyncFlowWorker implements FlowWorker, Runnable  {
	
	private final ExecutorService executor;
	
	private final FlowDefinition definition;
	
	private Future< ? > future;
	
	private DataContext context;
	
	public SyncFlowWorker( ExecutorService executor, FlowDefinition definition, DataContext context ) {
		this.definition = definition;
		this.executor = executor;
		this.context = context;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			definition.createProcessor().process( context );
		} catch ( Exception e ) {
			
		}
	}

	/* (non-Javadoc)
	 * @see net.vdrinkup.alpaca.framework.FlowWorker#start()
	 */
	@Override
	public void start() throws Exception {
		future = this.executor.submit( this );
	}

	/* (non-Javadoc)
	 * @see net.vdrinkup.alpaca.framework.FlowWorker#stop()
	 */
	@Override
	public void stop() throws Exception {
		if ( future == null ) {
			return ;
		}
		if ( future.isDone() ) {
			return ;
		} else {
			future.cancel( false );
		}
	}

	/* (non-Javadoc)
	 * @see net.vdrinkup.alpaca.framework.FlowWorker#getExecutor()
	 */
	@Override
	public ExecutorService getExecutor() {
		return executor;
	}

	/* (non-Javadoc)
	 * @see net.vdrinkup.alpaca.framework.FlowWorker#getDefinition()
	 */
	@Override
	public FlowDefinition getDefinition() {
		return this.definition;
	}

}
