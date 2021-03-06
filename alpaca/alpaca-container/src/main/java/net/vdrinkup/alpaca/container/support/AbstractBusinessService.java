package net.vdrinkup.alpaca.container.support;

import net.vdrinkup.alpaca.container.ErrorHandler;
import net.vdrinkup.alpaca.container.Handler;
import net.vdrinkup.alpaca.context.ContextStatus;
import net.vdrinkup.alpaca.context.DataContext;
import net.vdrinkup.alpaca.service.AtomicService;
import net.vdrinkup.alpaca.service.BusinessException;
import net.vdrinkup.alpaca.service.BusinessService;
import net.vdrinkup.alpaca.service.InvokeException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 抽象业务模板类
 * <p>
 * 继承{@link BusinessService}接口
 * </p>
 * @author liubing
 * Date Oct 24, 2013
 */
public abstract class AbstractBusinessService implements AtomicService {
	
	protected static Logger LOG = LoggerFactory.getLogger( AbstractBusinessService.class );
		
	protected Handler handler;
	
	protected ErrorHandler errorHandler;
		
	public AbstractBusinessService() {
		super();
	}
	
	public AbstractBusinessService( Handler handler ) {
		this();
		this.handler = handler;
	}
	
	public AbstractBusinessService( Handler handler, ErrorHandler errorHandler ) {
		this.handler = handler;
		this.errorHandler = errorHandler;
	}
	
	/*
	 * (non-Javadoc)
	 * @see net.vdrinkup.alpaca.service.Service#invoke(net.vdrinkup.alpaca.context.DataContext)
	 */
	public void invoke( DataContext context ) throws InvokeException {
		if ( ContextStatus.INVALID.equals( context.getStatus() ) ) {
			getLogger().warn( "****************Current context is invalid.******************" );
			return ;
		}
		try {
			invoke( context );
		} catch ( Exception e ) {
			getLogger().error( e.getMessage(), e ) ;
			context.setStatus( ContextStatus.EXCEPTION );
			context.setException( e );
		}
	}
	
	public abstract void doService( DataContext context ) throws Exception;
	
	/************************setter/getter***************************/

	/**
	 * @return the handler
	 */
	public Handler getHandler() {
		return handler;
	}

	/**
	 * @param handler the handler to set
	 */
	public void setHandler( Handler handler ) {
		this.handler = handler;
	}

	/**
	 * @return the errorHandler
	 */
	public ErrorHandler getErrorHandler() {
		return errorHandler;
	}

	/**
	 * @param errorHandler the errorHandler to set
	 */
	public void setErrorHandler( ErrorHandler errorHandler ) {
		this.errorHandler = errorHandler;
	}
	
	public Logger getLogger() {
		return LOG;
	}
	
}
