/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package net.vdrinkup.alpaca.component;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.jar.JarFile;

import edu.emory.mathcs.backport.java.util.Collections;

import net.vdrinkup.alpaca.constant.ManifestKeys;
import net.vdrinkup.alpaca.resource.ResourceFilter;


/**
 *
 * <p></p>
 * @author liubing
 * Date Oct 28, 2013
 */
public class ComponentResourceFilter implements ResourceFilter {
	
	@SuppressWarnings( "unchecked" )
	private static List< ResourceFilter > extraFilters = Collections.synchronizedList( new LinkedList< ResourceFilter >() );
	
	/*
	 * (non-Javadoc)
	 * @see com.jd.wms.resource.ResourceFilter#doFilter(java.lang.Object)
	 */
	@SuppressWarnings( "unchecked" )
	@Override
	public < T, R > R doFilter( T t ) throws Exception {
		if ( ! ( t instanceof File ) ) {
			throw new IllegalArgumentException( "The argument must be an instance of java.io.File." );
		}
		final JarFile jar = new JarFile( ( File ) t );
		final String componentName = jar.getManifest().getMainAttributes().getValue( ManifestKeys.COMPONENT_NAME );
		if ( componentName == null || "".equals( componentName ) ) {
			return ( R ) Boolean.FALSE;
		}
		final Component component = new Component( componentName, 
				jar.getManifest().getMainAttributes().getValue( ManifestKeys.COMPONENT_VERSION ) );
		component.setFilePath( ( ( File ) t ).getAbsolutePath() );
		final String activatorClass = jar.getManifest().getMainAttributes().getValue( ManifestKeys.COMPONENT_ACTIVATOR );
		if ( activatorClass != null && ! "".equals( activatorClass ) ) {
			component.setActivatorClass( activatorClass );
		}
		final String componentPriority = jar.getManifest().getMainAttributes().getValue( ManifestKeys.COMPONENT_PRIORITY );
		int priority = 3;
		if ( componentPriority != null && ! "".equals( componentPriority ) ) {
			priority = Integer.parseInt( componentPriority, 10 );
		}
		component.setPriority( priority );
		ComponentManager.getInstance().register( component.getId(), component );
		for ( ResourceFilter filter : extraFilters ) {
			filter.doFilter( component );
		}
		return ( R ) Boolean.TRUE;
	}
	
	public static void addExtraFilter( ResourceFilter filter ) {
		extraFilters.add( filter );
	}

}
