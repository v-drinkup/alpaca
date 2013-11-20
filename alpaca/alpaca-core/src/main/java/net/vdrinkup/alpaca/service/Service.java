/*******************************************************************************
 * Copyright (c) 2013 Corporation and others.
 * 
 * Contributors:
 *     XXX Corporation 
 *******************************************************************************/
package net.vdrinkup.alpaca.service;

import net.vdrinkup.alpaca.context.DataContext;

/**
 * 服务接口
 * <p>
 * 该接口定义服务运行所需的方法。
 * {@link start()}方法用于启动服务，该方法可被用于执行服务的一些初始化操作。
 * {@link stop()}方法用于停止服务，服务一旦被停止则该服务所在流程执行时，将不会执行服务。即该服务步骤会被跳过。
 * {@link invoke()}方法用于执行该服务。
 * </p>
 * @author liubing
 * Date 2013-11-11
 */
interface Service {
	/**
	 * 执行服务
	 * @param context
	 * @throws InvokeException
	 */
	public void invoke( DataContext context ) throws InvokeException;
	/**
	 * 启动服务
	 * @return
	 */
	public void start() throws ServiceException;
	/**
	 * 停止服务
	 * @return
	 */
	public void stop() throws ServiceException;
	/**
	 * 服务是否已经启动
	 * @return
	 */
	public boolean isStartup();
	/**
	 * 服务是否已经停止
	 * @return
	 */
	public boolean isShutdown();
}
