/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package net.vdrinkup.alpaca.standalone;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import net.vdrinkup.alpaca.Env;
import net.vdrinkup.alpaca.component.ComponentResourceFilter;


/**
 *
 * <p></p>
 * @author liubing
 * Date Oct 28, 2013
 */
public class StandAloneMain {
		
	private static ExecutorService single = Executors.newSingleThreadExecutor( new ThreadFactory() {

		@Override
		public Thread newThread( Runnable r ) {
			return new Thread( r, "StandAlone-Thread" );
		}
		
	} );
	
	private ClassLoader parent;
			
	public void main( ClassLoader classLoader, URL[] urls, String installPath, String... args ) throws Exception {
		this.parent = classLoader;
		Env.getInstance().init( installPath );
		doStart( urls, args );
	}

	/**
	 * @param args
	 */
	private void doStart( URL[] urls, String[] args ) throws Exception {
		final ComponentResourceFilter crf = new ComponentResourceFilter();
		final List< URL > commonUrls = new LinkedList< URL >();
		File file = null;
		URL url = null;
		for ( int i = 0; i < urls.length; i++ ) {
			url = urls[ i ];
			file = new File( url.toURI() );
			if ( ! ( ( Boolean ) crf.doFilter( file ) ) ) {
				System.out.println( ">Add [" + url.getFile() + "] to commons libs." );
				commonUrls.add( url );
			}
		}
		final URL[] commonUrlArray = new URL[ commonUrls.size() ];
		commonUrls.toArray( commonUrlArray );
		commonUrls.clear();
		final ClassLoader commonsCL = new URLClassLoader( commonUrlArray, this.parent );
		
		Thread.currentThread().setContextClassLoader( commonsCL );
		
		single.execute( new MainRunnable( commonsCL ) );
	}

}
