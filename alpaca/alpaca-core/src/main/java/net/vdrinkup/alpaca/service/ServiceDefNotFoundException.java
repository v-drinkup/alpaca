/*******************************************************************************
 * Copyright (c) 2013 Corporation and others.
 * 
 * Contributors:
 *     XXX Corporation 
 *******************************************************************************/
package net.vdrinkup.alpaca.service;

/**
 * 服务定义未找到异常类
 * <p>
 * 继承{@link RuntimeException}
 * </p>
 * @author liubing
 * Date 2013-11-19
 */
public class ServiceDefNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5893179612203341794L;

	public ServiceDefNotFoundException( String message ) {
		super( message );
	}
		
}
