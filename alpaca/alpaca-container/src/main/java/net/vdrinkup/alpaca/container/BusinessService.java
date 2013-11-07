package net.vdrinkup.alpaca.container;

import net.vdrinkup.alpaca.container.BusinessException;
import net.vdrinkup.alpaca.context.DataContext;


public interface BusinessService {
	
	public void doService( DataContext context ) throws BusinessException;

}
