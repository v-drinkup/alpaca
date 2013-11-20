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
import java.util.logging.Logger;

import net.vdrinkup.alpaca.component.Component;
import net.vdrinkup.alpaca.component.ComponentManager;


/**
 *
 * <p></p>
 * @author liubing
 * Date Oct 28, 2013
 */
public class MainRunnable implements Runnable {
	
	private static Logger LOG = Logger.getLogger( MainRunnable.class.getName() );
	
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
		LOG.config( ">The platform is starting..." );
		final long beginTime = System.currentTimeMillis();
		final Collection< Component > components = ComponentManager.getInstance().getAll();
		LOG.config( ">Current component count is " + components.size() );
		Iterator< Component > iter = components.iterator();
		final URL[] cUrls = new URL[ components.size() ];
		int i = 0;
		while ( iter.hasNext() ) {
			try {
				cUrls[ i++ ] = new File( iter.next().getFilePath() ).toURI().toURL();
			} catch ( MalformedURLException e ) {
				e.printStackTrace();
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
			e.printStackTrace();
		}
		final long endTime= System.currentTimeMillis();
		LOG.config( ">The platform has been started. use time [" + ( endTime - beginTime ) + "ms]" );
	}

}
