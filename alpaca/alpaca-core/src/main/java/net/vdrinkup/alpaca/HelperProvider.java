/*******************************************************************************
 * Copyright (c) 2013 Corporation and others.
 * 
 * Contributors:
 *     XXX Corporation 
 *******************************************************************************/
package net.vdrinkup.alpaca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.AccessController;
import java.security.PrivilegedAction;

import net.vdrinkup.alpaca.log.LogMBean;
import net.vdrinkup.alpaca.service.ServiceManager;

/**
 * 
 * <p>
 * </p>
 * 
 * @author liubing Date 2013-11-18
 */
public abstract class HelperProvider {

	protected static final String PROPERTY_NAME = "net.vdrinkup.alpaca.HelperProvider";
	
	protected static final String SERVICE_RESOURCE_NAME = "META-INF/services/net.vdrinkup.alpaca.HelperProvider";
	
	public static HelperProvider INSTANCE;

	static {
		HelperProvider provider;
		try {
			provider = getInstance( HelperProvider.class.getClassLoader() );
		} catch ( ProviderNotFoundException e ) {
			provider = null;
		}
		INSTANCE = provider;
	}

	/**
	 * @param classLoader
	 * @return
	 */
	protected static HelperProvider getInstance( ClassLoader classLoader ) {
		String implName = getImplementationName();

		ClassLoader cl = getContextClassLoader();
		if ( cl != null ) {
			HelperProvider provider = loadImplementation( cl, implName );
			if ( provider != null ) {
				return provider;
			}
		}

		cl = HelperProvider.class.getClassLoader();
		HelperProvider provider = loadImplementation( cl, implName );
		if ( provider != null ) {
			return provider;
		}

		throw new ProviderNotFoundException( implName );
	}

	/**
	 * 
	 * @return
	 */
	private static String getImplementationName() {
		String implementationName = AccessController.doPrivileged( new PrivilegedAction< String >() {
            public String run() {
            	return System.getProperty( PROPERTY_NAME );
            }
        } );
		if ( implementationName == null || "".equals( implementationName ) ) {
			implementationName = Env.getInstance().getPropertyValue( PROPERTY_NAME );
		}
		return implementationName;
    }

	private static ClassLoader getContextClassLoader() {
		try {
			return ( ClassLoader ) AccessController
					.doPrivileged( new PrivilegedAction< ClassLoader >() {
						public ClassLoader run() {
							return Thread.currentThread().getContextClassLoader();
						}
					} );
		} catch ( SecurityException e ) {
			return null;
		}
	}
	
	/**
	 * @param cl
	 * @return
	 */
	private static String getImplementationName( ClassLoader cl ) {
		InputStream is = cl.getResourceAsStream( SERVICE_RESOURCE_NAME );
        if ( is == null ) {
            return null;
        }

        InputStreamReader in;
        try {
            in = new InputStreamReader( is, "UTF-8" );
        } catch ( UnsupportedEncodingException e ) {
            throw new AssertionError( "UTF-8 encoding not available" );
        }

        try {
            BufferedReader reader = new BufferedReader( in, 128 );
            try {
                String line;
                while ( ( line = reader.readLine() ) != null ) {
                    int i = line.indexOf( '#' );
                    if (i != -1) {
                        line = line.substring( 0, i );
                    }
                    line = line.trim();
                    if ( line.length() > 0 ) {
                        return line;
                    }
                }
                return null;
            } finally {
                reader.close();
            }
        } catch ( IOException e ) {
            throw new ProviderNotFoundException( e );
        }
	}
	
	/**
	 * 
	 * @param cl
	 * @param implName
	 * @return
	 * @throws NoHelperProviderException
	 */
	private static HelperProvider loadImplementation( ClassLoader cl,
			String implName ) throws ProviderNotFoundException {
		if ( implName == null ) {
			implName = getImplementationName( cl );
		}
		if ( implName == null ) {
			return null;
		}

		try {
			return ( HelperProvider ) cl.loadClass( implName ).newInstance();
		} catch ( InstantiationException e ) {
			throw new ProviderNotFoundException( implName, e );
		} catch ( IllegalAccessException e ) {
			throw new ProviderNotFoundException( implName, e );
		} catch ( ClassNotFoundException e ) {
			throw new ProviderNotFoundException( implName, e );
		}
	}
	
	public abstract ServiceManager serviceManager();
	
	public abstract LogMBean logMBean();

}
