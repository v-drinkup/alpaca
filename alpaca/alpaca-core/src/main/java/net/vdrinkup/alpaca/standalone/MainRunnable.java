/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package net.vdrinkup.alpaca.standalone;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collection;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.vdrinkup.alpaca.component.Component;
import net.vdrinkup.alpaca.component.ComponentManager;


/**
 *
 * <p></p>
 * @author liubing
 * Date Oct 28, 2013
 */
public class MainRunnable implements Runnable {
	
	private Logger LOG = LoggerFactory.getLogger( MainRunnable.class );
		
	private ClassLoader parent;
	
	/**
	 * @param urls
	 */
	public MainRunnable( ClassLoader parent ) {
		this.parent = parent;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		Thread.currentThread().setContextClassLoader( parent );
		if ( LOG.isDebugEnabled() ) {
			LOG.debug( "The platform is starting..." );
		}
		final long beginTime = System.currentTimeMillis();
		final Collection< Component > components = ComponentManager.getInstance().getAll();
		if ( LOG.isDebugEnabled() ) {
			LOG.debug( ">Current component count is " + components.size() );
		}
		Iterator< Component > iter = components.iterator();
		final URL[] cUrls = new URL[ components.size() ];
		int i = 0;
		URL url = null;
		while ( iter.hasNext() ) {
			try {
				url = new File( iter.next().getFilePath() ).toURI().toURL();
				cUrls[ i++ ] = url;
			} catch ( MalformedURLException e ) {
				LOG.error( "Current url [" + url + "] is malformed. Error message is \r\n" + e.getMessage() );
				System.exit( 0 );
			}
		}
		final ClassLoader componentCl = new URLClassLoader( cUrls, this.parent );
		iter = components.iterator();
		Component component = null;
		while ( iter.hasNext() ) {
			component = iter.next();
			component.setClassLoader( componentCl );
		}
		try {
			ComponentManager.getInstance().startAll();
		} catch ( Exception e ) {
			LOG.error( "Components started error. Error message is : \r\n", e.getMessage() );
		}
		final long endTime= System.currentTimeMillis();
		if ( LOG.isDebugEnabled() ) {
			LOG.debug( "The platform has been started. use time [" + ( endTime - beginTime ) + "ms]" );
		}
	}
}
