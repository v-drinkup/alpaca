/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package net.vdrinkup.alpaca.context;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


/**
 *	通用数据上下文实现类
 * <p></p>
 * @author liubing
 * Date Oct 24, 2013
 */
public class GenericDataContext implements DataContext {

	private static final long serialVersionUID = 3648951552916798657L;
	
	protected Map< String, Object > attributes = new HashMap< String, Object >( 16 );
	
	protected Object payload;

	protected Exception exception;
	
	/**
	 * default:{@link ContextStatus.VALID}
	 */
	protected ContextStatus status = ContextStatus.VALID;
	
	/* (non-Javadoc)
	 * @see com.jd.wms.container.DataContext#getAttributes()
	 */
	@Override
	public Map< String, Object > getAttributes() {
		return attributes;
	}

	/* (non-Javadoc)
	 * @see com.jd.wms.container.DataContext#getPayload()
	 */
	@SuppressWarnings( "unchecked" )
	@Override
	public < T > T getPayload() {
		return ( T ) payload;
	}

	/* (non-Javadoc)
	 * @see com.jd.wms.container.DataContext#setPayload(java.lang.Object)
	 */
	@Override
	public < T > void setPayload( T t ) {
		this.payload = t;
	}

	/* (non-Javadoc)
	 * @see com.jd.wms.container.DataContext#addAttribute(java.lang.String, java.lang.Object)
	 */
	@Override
	public void addAttribute( String key, Object value ) {
		if ( key == null ) {
			throw new IllegalArgumentException( "The key can not be null." );
		}
		attributes.put( key, value );
	}

	/* (non-Javadoc)
	 * @see com.jd.wms.container.DataContext#getAttribute(java.lang.String)
	 */
	@Override
	public Object getAttribute( String key ) {
		if ( key == null ) {
			throw new IllegalArgumentException( "The key can not be null." );
		}
		return attributes.get( key );
	}

	/* (non-Javadoc)
	 * @see com.jd.wms.container.DataContext#getId()
	 */
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.jd.wms.container.DataContext#setException(java.lang.Exception)
	 */
	@Override
	public void setException( Exception e ) {
		this.exception = e;
	}

	/* (non-Javadoc)
	 * @see com.jd.wms.container.DataContext#getException()
	 */
	@Override
	public Exception getException() {
		return exception;
	}

	/*
	 * (non-Javadoc)
	 * @see net.vdrinkup.alpaca.context.DataContext#getStatus()
	 */
	@Override
	public ContextStatus getStatus() {
		return this.status;
	}

	/*
	 * (non-Javadoc)
	 * @see net.vdrinkup.alpaca.context.DataContext#setStatus(net.vdrinkup.alpaca.context.ContextStatus)
	 */
	@Override
	public void setStatus( ContextStatus status ) {
		this.status = status;
	}

	/* (non-Javadoc)
	 * @see net.vdrinkup.alpaca.context.DataContext#getAttributeAsString(java.lang.String)
	 */
	@Override
	public String getAttributeAsString( String key ) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see net.vdrinkup.alpaca.context.DataContext#getAttributeAsInt(java.lang.String)
	 */
	@Override
	public Integer getAttributeAsInt( String key ) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see net.vdrinkup.alpaca.context.DataContext#getAttributeAsLong(java.lang.String)
	 */
	@Override
	public Long getAttributeAsLong( String key ) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see net.vdrinkup.alpaca.context.DataContext#getAttributeAsDouble(java.lang.String)
	 */
	@Override
	public Double getAttributeAsDouble( String key ) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see net.vdrinkup.alpaca.context.DataContext#getAttributeAsBigDecimal(java.lang.String)
	 */
	@Override
	public BigDecimal getAttributeAsBigDecimal( String key ) {
		// TODO Auto-generated method stub
		return null;
	}

}
