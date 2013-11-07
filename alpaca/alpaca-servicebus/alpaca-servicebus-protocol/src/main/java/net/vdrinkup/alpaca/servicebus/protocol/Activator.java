/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package net.vdrinkup.alpaca.servicebus.protocol;

import net.vdrinkup.alpaca.component.GenericActivator;
import net.vdrinkup.alpaca.servicebus.protocol.ProtocolConfigManager;
import net.vdrinkup.alpaca.servicebus.protocol.ProtocolInstanceManager;
import net.vdrinkup.alpaca.servicebus.protocol.ProtocolPluginManager;

/**
 *
 * <p></p>
 * @author liubing
 * Date Nov 6, 2013
 */
public class Activator extends GenericActivator {

	/* (non-Javadoc)
	 * @see com.jd.wms.component.GenericActivator#start()
	 */
	@Override
	protected void start() throws Exception {
		ProtocolPluginManager.getInstance().start();
		ProtocolConfigManager.getInstance().readAll();
		ProtocolInstanceManager.getInstance().loadAll( ProtocolConfigManager.getInstance().getAll().values() );
	}

	/* (non-Javadoc)
	 * @see com.jd.wms.component.GenericActivator#stop()
	 */
	@Override
	protected void stop() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
