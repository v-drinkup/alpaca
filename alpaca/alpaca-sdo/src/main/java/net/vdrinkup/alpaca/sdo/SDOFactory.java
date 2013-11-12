/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package net.vdrinkup.alpaca.sdo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import net.vdrinkup.alpaca.Env;

import commonj.sdo.helper.XSDHelper;

/**
 *
 * <p></p>
 * @author liubing
 * Date Nov 8, 2013
 */
public class SDOFactory {
	
	private static volatile SDOFactory _instance;
	
	private SDOFactory() {
	}
	
	public static SDOFactory getInstance() {
		if ( _instance == null ) {
			synchronized ( SDOFactory.class ) {
				if ( _instance == null ) {
					_instance = new SDOFactory();
					try {
						_instance.init();
					} catch ( Exception e ) {
						throw new RuntimeException( e );
					}
				}
			}
		}
		return _instance;
	}
	
	private void init() throws Exception {
		final String path = new StringBuilder( 
				Env.getInstance().getConfPath() )
				.append( "sdo" ).append( File.separator )
				.append( "domain" ).toString();
		final File dir = new File( path );
		if ( ! dir.exists() ) {
			throw new FileNotFoundException( "The file [ " + path + " ] not exists." );
		}
		final File[] children = dir.listFiles();
		FileInputStream fis = null;
		for ( File child : children ) {
			try {
				fis = new FileInputStream( child );
				XSDHelper.INSTANCE.define( fis, null );
			} catch ( Exception e ) {
				e.printStackTrace();
			} finally {
				if ( fis != null ) {
					fis.close();
					fis = null;
				}
			}
		}
	}

}
