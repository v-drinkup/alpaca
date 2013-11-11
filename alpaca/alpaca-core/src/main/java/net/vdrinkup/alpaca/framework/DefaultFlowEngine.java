/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package net.vdrinkup.alpaca.framework;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import net.vdrinkup.alpaca.context.DataContext;
import net.vdrinkup.alpaca.context.GenericDataContext;

/**
 *
 * <p></p>
 * @author liubing
 * Date Nov 10, 2013
 */
public class DefaultFlowEngine implements FlowEngine {

	static final int SHUTDOWN_TIMEOUT = 5000;
	
	private volatile ThreadPoolExecutor threadPool;
	
	private String name;
	
	private int threadSize;
	
	private volatile boolean running = false;
		
	public DefaultFlowEngine( String name ) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see net.vdrinkup.alpaca.framework.FlowEngine#createnNewContext()
	 */
	@Override
	public DataContext createnNewContext() {
		final DataContext context = new GenericDataContext();
		return context;
	}

	/* (non-Javadoc)
	 * @see net.vdrinkup.alpaca.framework.FlowEngine#incoming(net.vdrinkup.alpaca.context.DataContext)
	 */
	@Override
	public void incoming( DataContext context ) {
		
	}

	/* (non-Javadoc)
	 * @see net.vdrinkup.alpaca.framework.FlowEngine#start()
	 */
	@Override
	public synchronized void start() {
		if ( isStartup() ) {
			return ;
		}
		if ( threadPool == null ) {
			threadPool = ( ThreadPoolExecutor ) Executors.newFixedThreadPool( 10, new ThreadFactory() {
				private int count = 0;
				private ThreadGroup group = new ThreadGroup( getName() + "-ThreadPool"  );
				@Override
				public Thread newThread( Runnable r ) {
					return new Thread( group, r, new StringBuilder( 
							group.getName() ).append("-" ).append( count ++ ).toString() );
				}
			} );
		}
		running = true;
	}

	/* (non-Javadoc)
	 * @see net.vdrinkup.alpaca.framework.FlowEngine#stop()
	 */
	@Override
	public synchronized void stop() {
		if ( isShutdown() ) {
			return ;
		}
		running = false;
		if ( threadPool != null ) {
			threadPool.shutdown();
			try {
				if ( ! threadPool.awaitTermination( SHUTDOWN_TIMEOUT, TimeUnit.MILLISECONDS ) ) {
					threadPool.shutdownNow();
				}
			} catch ( InterruptedException e ) {
				e.printStackTrace();
			}
		}
	}

	/* (non-Javadoc)
	 * @see net.vdrinkup.alpaca.framework.FlowEngine#isStartup()
	 */
	@Override
	public boolean isStartup() {
		return running;
	}

	/* (non-Javadoc)
	 * @see net.vdrinkup.alpaca.framework.FlowEngine#isShutdown()
	 */
	@Override
	public boolean isShutdown() {
		return ! running;
	}

	/* (non-Javadoc)
	 * @see net.vdrinkup.alpaca.framework.FlowEngine#getThreadSize()
	 */
	@Override
	public int getThreadSize() {
		return this.threadSize;
	}

	/* (non-Javadoc)
	 * @see net.vdrinkup.alpaca.framework.FlowEngine#setThreadSize(int)
	 */
	@Override
	public void setThreadSize( int size ) {
		this.threadSize = size;
		resetThreadPool();
	}

	/**
	 * 
	 */
	private void resetThreadPool() {
		if ( threadPool == null ) {
			return ;
		}
		threadPool.setCorePoolSize( threadSize );
	}

	/* (non-Javadoc)
	 * @see net.vdrinkup.alpaca.framework.FlowEngine#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

}
