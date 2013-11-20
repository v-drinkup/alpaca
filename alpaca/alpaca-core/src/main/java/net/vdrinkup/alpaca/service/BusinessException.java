/**
 * 
 */
package net.vdrinkup.alpaca.service;

import net.vdrinkup.alpaca.context.DataContext;

/**
 * 业务异常类
 * <p>
 * 
 * </p>
 * @author bjyfliubing
 *
 */
public class BusinessException extends InvokeException {

	private static final long serialVersionUID = 4107987912564998303L;
	
	private DataContext currentContext;
	
	public BusinessException( DataContext context ) {
		super();
		this.currentContext = context;
	}
	
	public BusinessException( String message, DataContext context ) {
		super( message );
		this.currentContext = context;
	}

	/**
	 * @return the currentContext
	 */
	@SuppressWarnings( "unchecked" )
	public < T > T getCurrentContext() {
		return ( T ) currentContext;
	}

}
