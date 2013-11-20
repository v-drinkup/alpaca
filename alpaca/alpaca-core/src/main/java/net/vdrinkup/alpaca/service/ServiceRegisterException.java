/*******************************************************************************
 * Copyright (c) 2013 Corporation and others.
 * 
 * Contributors:
 *     XXX Corporation 
 *******************************************************************************/
package net.vdrinkup.alpaca.service;

/**
 * 服务注册异常类
 * <p>
 * 继承{@link RuntimeException}
 * </p>
 * @author liubing
 * Date 2013-11-19
 */
public class ServiceRegisterException extends RuntimeException {

	private static final long serialVersionUID = -2524664313144505585L;

	public ServiceRegisterException( String message, Exception e ) {
		super( message, e );
	}
	
}
