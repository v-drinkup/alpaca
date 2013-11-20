/*******************************************************************************
 * Copyright (c) 2013 Corporation and others.
 * 
 * Contributors:
 *     XXX Corporation 
 *******************************************************************************/
package net.vdrinkup.alpaca.service.extra;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import net.vdrinkup.alpaca.component.Component;
import net.vdrinkup.alpaca.component.ComponentResourceFilter;
import net.vdrinkup.alpaca.resource.ResourceFilter;
import net.vdrinkup.alpaca.service.ServiceDefNotFoundException;
import net.vdrinkup.alpaca.service.ServiceEntry;
import net.vdrinkup.alpaca.service.ServiceRegisterException;
import net.vdrinkup.alpaca.service.definition.ServiceDefinition;
import net.vdrinkup.alpaca.service.definition.ServiceSetDefinition;

/**
 * 服务组件过滤器
 * <p></p>
 * @author liubing
 * Date 2013-11-18
 */
class ServiceComponentFilter implements ResourceFilter {

	private static final String RESOURCE_NAME = "META-INF/services/service-set.xml";
		
	static {
		ComponentResourceFilter.addExtraFilter( new ServiceComponentFilter() );
	}
	
	/* (non-Javadoc)
	 * @see net.vdrinkup.alpaca.resource.ResourceFilter#doFilter(java.lang.Object)
	 */
	@Override
	public < T, R > R doFilter( T t ) throws Exception {
		if ( ! ( t instanceof Component ) ) {
			throw new IllegalArgumentException( "The argument must be an instance of " + Component.class.getName() );
		}
		final Component component = ( Component ) t;
		final ClassLoader cl = component.getClassLoader() == null ? this.getClass().getClassLoader() : component.getClassLoader();
		final InputStream is = cl.getResourceAsStream( RESOURCE_NAME );
		if ( is != null ) {
			parse( component.getId(), cl, is );
		}
		return null;
	}
	
	private void parse( String componentId, ClassLoader cl, InputStream is ) {
		ServiceSetDefinition serviceSet = null;
		try {
			final JAXBContext context = JAXBContext.newInstance( ServiceSetDefinition.class.getPackage().getName() );
			final Unmarshaller unmarshaller = context.createUnmarshaller();
			serviceSet  = ( ServiceSetDefinition ) unmarshaller.unmarshal( is );
		} catch ( JAXBException e ) {
			throw new ServiceRegisterException( e.getMessage(), e );
		}
		if ( serviceSet == null ) {
			throw new ServiceDefNotFoundException( "No found any service definition." );
		}
		if ( serviceSet.getServices() == null || serviceSet.getServices().size() == 0 ) {
			throw new ServiceDefNotFoundException( "No found any service definition." );
		}
		ServiceEntry entry  = null;
		for ( ServiceDefinition service : serviceSet.getServices() ) {
			entry = new ServiceEntry();
			entry.setId( new StringBuilder( componentId ).append( 
					ServiceEntry.Separator.PACKAGE_SEPARATOR )
					.append( service.getName() ).append( ServiceEntry.Separator.VERSION_SEPARATOR )
					.append( service.getVersion() ).toString() );
			entry.setDefinition( service );
			try {
				cl.loadClass( service.getClazz() );
			} catch ( ClassNotFoundException e ) {
				throw new ServiceRegisterException( "Create instance of the service named [" + entry.getId() + "] error.", e );
			}
		}
	}

}
