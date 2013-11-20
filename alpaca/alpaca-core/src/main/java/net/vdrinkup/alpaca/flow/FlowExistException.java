/*******************************************************************************
 * Copyright (c) 2013 Corporation and others.
 * 
 * Contributors:
 *     XXX Corporation 
 *******************************************************************************/
package net.vdrinkup.alpaca.flow;

/**
 * 流程已存在异常
 * <p>
 * 当流程注册时，如果此时存在相同名称（ID）的流程，就会抛出此异常。
 * </p>
 * @author liubing
 * Date 2013-11-17
 */
public class FlowExistException extends RuntimeException {
	
	private static final long serialVersionUID = -3537034606539862013L;
	
	public FlowExistException( String message ) {
		super( message );
	}

}
