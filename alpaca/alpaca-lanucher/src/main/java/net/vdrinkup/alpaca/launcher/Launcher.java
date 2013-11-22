/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package net.vdrinkup.alpaca.launcher;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 *
 * <p></p>
 * @author liubing
 * Date Oct 29, 2013
 */
public class Launcher {
	
	private static Launcher _instance;
	
	private String clazzName;
	
	public static void main( String... args ) {
		final Launcher launcher = new Launcher();
		_instance = launcher;
		_instance.run( args );
	}
	
	private void run( String... args ) {
		System.out.println( "Ready to start the runtime..." );
		System.out.println( "\t ---->Get install path..." );
		final SecurityManager sm = System.getSecurityManager();
		if ( sm != null ) {
			sm.checkPermission( new RuntimePermission( "getProtectionDomain" ) );
		}
		final URL url = Launcher.class.getProtectionDomain().getCodeSource().getLocation();
		File codeSource = null;
		try {
			codeSource = new File( url.toURI() );
		} catch ( URISyntaxException e ) {
			error( e );
		}
		final String _install = codeSource.getParentFile().getAbsolutePath();
		System.out.println( "\t ---->Current install path is [".concat( _install ).concat( "]" ) );
		final String _conf = _install.concat( File.separator ).concat( "conf" );
		final String _lib = _install.concat( File.separator ).concat( "lib" );
		final String _logs = _install.concat( File.separator ).concat( "logs" );
		System.out.println( "\t ---->Current conf path is [".concat( _conf ).concat( "]" ) );
		System.out.println( "\t ---->Current lib path is [".concat( _lib ).concat( "lib" ).concat( "]" ) );
		System.out.println( "\t ---->Current logs path is [".concat( _logs ).concat( "logs" ).concat( "]" ) );
		System.out.println( "\t ---->Read start configuration..." );
		final File startIni = new File( _install.concat( File.separator ).concat( "start.ini" ) );
		Properties prop = null;
		try {
			prop = read( startIni );
			clazzName = prop.getProperty( "main.class" );
		} catch ( Exception e ) {
			error( e );
		}
		List< URL > urls = null;
		try {
			urls = frt( new File( _lib ) );
		} catch ( Exception e ) {
			error( e );
		}
		final String commandPort = prop.getProperty( "command.port" );
		final String mainClass = prop.getProperty( "main.class" );
		
	}
	
	/**
	 * @param e
	 */
	private void error( Exception e ) {
		System.err.println( e.getMessage() );
		System.err.println( "Runtime start error, will be Interrupted." );
		System.exit( 0 );
	}
	
	private Properties read( File file ) throws Exception {
		if ( file == null ) {
			throw new IllegalArgumentException( "Read config error.The file is null." );
		}
		if ( ! file.exists() ) {
			throw new IllegalArgumentException( "Read config error.The file not exist." );
		}
		if ( ! file.isFile() ) {
			throw new IllegalArgumentException( "Read config error.The file is not a file" );
		}
		Properties properties = new Properties();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream( file );
			properties.load( fis );
		} finally {
			if ( fis != null ) {
				fis.close();
				fis = null;
			}
		}
		return properties;
	}

	/**
	 * @param main
	 */
	private void invokeMain( Object obj, Method main, URL[] urls, String[] args ) {
		try {
			main.invoke( obj, this.classLoader, urls, this._install, args );
		} catch ( Exception e ) {
			e.printStackTrace();
			System.exit( 0 );
		}
	}
	
	private List< URL > frt( File root ) throws Exception {
		final List< URL > urls = new LinkedList< URL >();
		final List< StackItem > stack = new LinkedList< StackItem >();
		if ( root.isFile() ) {
			fileFilter( root, urls );
			return urls;
		} else {
			stack.add( 0, new StackItem( root.listFiles(), -1 ) );
			StackItem item = null;
			int i = 0;
			File[] files = null;
			while ( ! stack.isEmpty() ) {
				item = stack.remove( 0 );
				files = item.getFiles();
				i = item.getIndex();
				i++;
				
				for ( ; files != null && i < files.length; i++ ) {
					if ( files[ i ].isDirectory() ) {
						stack.add( 0, new StackItem( files, i ) );
						files = files[ i ].listFiles();
						i = -1;
						continue ;
					}
					fileFilter( files[ i ], urls );
				}
			}
		}
		return urls;
	}
	
	private void fileFilter( File file, List< URL > urls ) throws Exception {
		if ( file.getName().endsWith( ".jar" ) ) {
			if ( this.mainClazz == null ) {
				jarFilter( file );
			}
			urls.add( file.toURI().toURL() );
		}
	}
	
	private void jarFilter( File file ) throws Exception {
		final JarFile jar = new JarFile( file );
		final Enumeration< JarEntry > entries = jar.entries();
		JarEntry entry = null;
		String name = null;
		while ( entries.hasMoreElements() ) {
			entry = entries.nextElement();
			if ( entry.isDirectory() ) {
				continue ;
			}
			name = entry.getName();
			if ( name.endsWith( ".class" )  ) {
				name = entry.getName().replaceAll( "/", "." ).substring( 
						0, entry.getName().lastIndexOf( "." ) );
				if ( name.equals(  ) ) {
					this.classLoader = new URLClassLoader( new URL[] { file.toURI().toURL() }, 
							ClassLoader.getSystemClassLoader() );
					this.mainClazz = this.classLoader.loadClass( name );
					break;
				}
			}
		}
	}
	
	class StackItem {
		private File[] files;
		
		private int index;
		
		StackItem( File[] files, int index ) {
			this.files = files;
			this.index = index;
		}

		public File[] getFiles() {
			return files;
		}

		public int getIndex() {
			return index;
		}
		
	}

}
