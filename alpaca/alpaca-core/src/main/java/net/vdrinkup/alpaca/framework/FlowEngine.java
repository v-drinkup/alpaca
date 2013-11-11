/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package net.vdrinkup.alpaca.framework;

import net.vdrinkup.alpaca.context.DataContext;

/**
 * 流程引擎接口
 * <p>
 * 该接口的实现在每个独立的系统中有且只有一个实例。
 * </p>
 * @author liubing
 * Date Nov 9, 2013
 */
public interface FlowEngine {
	
	/**
	 * 创建一个新的数据上下文
	 * @return new instance of DataContext
	 */
	public DataContext createnNewContext();
	
	/**
	 * 获得Engine名称，Engine的名称必须全局唯一
	 * @return
	 */
	public String getName();
	
	/**
	 * 引擎的异步执行方法
	 * @param context
	 */
	public void incoming( DataContext context );
	
	/**
	 * 判断是否启动
	 * @return 启动true；未启动false
	 */
	public boolean isStartup();
	
	/**
	 * 判断是否停止
	 * @return 停止true;未停止false
	 */
	public boolean isShutdown();
	
	/**
	 * Engine启动
	 */
	public void start();

	/**
	 * Engine停止
	 */
	public void stop();
	
	/**
	 * 获得当前最大线程数
	 * @return
	 */
	public int getThreadSize();
	
	/**
	 * 设置当前最大线程数
	 * @param size
	 */
	public void setThreadSize( int size );
	
}
