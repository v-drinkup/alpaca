/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package net.vdrinkup.alpaca.framework;

import java.util.concurrent.ExecutorService;

/**
 * 流程工作器接口
 * <p>
 * 该接口的实现类是流程处理的实际执行者
 * </p>
 * @author liubing
 * Date Nov 9, 2013
 */
public interface FlowWorker {
	
	/**
	 * 获取当前Worker的执行器
	 * @return
	 */
	public ExecutorService getExecutor();
	
	/**
	 * Worker启动方法
	 * <p>
	 * 1.同步方式执行时，该方法直接执行流程；
	 * 2.异步方式时，该方法调用<CODE>setExecutor(Executor)</CODE>
	 *   设置的Executor执行execute();
	 * 3.伪异步时，该方法调用<CODE>setExecutor(Executor)</CODE>
	 * 	 设置的ExecutorService执行submit()方法。
	 * </p>
	 * @throws Exception 若启动失败，则抛出异常
	 */
	public void start() throws Exception;
	
	/**
	 * Worker停止方法
	 * @throws Exception
	 */
	public void stop() throws Exception;
	
	/**
	 * 获得Worker所所执行流程配置
	 * @return
	 */
	public FlowDefinition getDefinition();
	
}
