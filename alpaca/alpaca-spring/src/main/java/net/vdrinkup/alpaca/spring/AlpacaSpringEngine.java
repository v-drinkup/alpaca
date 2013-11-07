/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package net.vdrinkup.alpaca.spring;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.vdrinkup.alpaca.Env;
import net.vdrinkup.alpaca.resource.impl.AbstractFileFilter;
import net.vdrinkup.alpaca.resource.impl.FileResourceScanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 *
 * <p></p>
 * @author liubing
 * Date Nov 7, 2013
 */
public class AlpacaSpringEngine {
	
private static volatile AlpacaSpringEngine _instance;
	
	private ApplicationContext context;
	
	private AlpacaSpringEngine() {
	}
	
	public void start() {
		final FileResourceScanner scanner = new FileResourceScanner();
		final AbstractFileFilter filter = new AbstractFileFilter() {
			
			private List< String > files = new ArrayList< String >( 10 );
			
			protected < R > R filter( File file ) {
				if ( file != null && file.getName().endsWith( "Context.xml" ) ) {
					files.add( file.toURI().toString() );
				}
				return null;
			}

			@SuppressWarnings( "unchecked" )
			public String[] getResult() {
				files.add( "classpath*:spring/*Context.xml" );
				final String[] fileArray = new String[ files.size() ];
				files.toArray( fileArray );
				files.clear();
				return fileArray;
			} 
			
		};
		final String springRoot = new StringBuffer( 
				Env.getInstance().getConfPath() ).append( "/spring" ).toString();
		scanner.scan( new File( springRoot ), filter );
		this.context = new FileSystemXmlApplicationContext( ( String[] ) filter.getResult() );
	}
	
	public static AlpacaSpringEngine getInstance() {
		if ( _instance == null ) {
			synchronized ( AlpacaSpringEngine.class ) {
				if ( _instance == null ) {
					_instance = new AlpacaSpringEngine();
				}
			}
		}
		return _instance;
	}
	
	public Object lookup( String beanName ) {
		Object bean = null;
		if ( this.context.containsBean( beanName ) ) {
			bean = this.context.getBean( beanName );
		} else {
			throw new BeanNotFoundException( "The bean named by [" + beanName + "] can not be found." );
		}
		return bean;
	}
	
	public Object lookup( Class< ? > clazz ) {
		return this.context.getBean( clazz );
	}

}
