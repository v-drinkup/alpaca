/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package net.vdrinkup.alpaca.container;

import net.vdrinkup.alpaca.component.GenericActivator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * <p></p>
 * @author liubing
 * Date Oct 30, 2013
 */
public class Activator extends GenericActivator {
	
	static Logger LOG = LoggerFactory.getLogger( Activator.class );

	/* (non-Javadoc)
	 * @see com.jd.wms.component.GenericActivator#start()
	 */
	@Override
	protected void start() throws Exception {
		LOG.debug( "============Start Container=============" );
	}

	/* (non-Javadoc)
	 * @see com.jd.wms.component.GenericActivator#stop()
	 */
	@Override
	protected void stop() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
