/*******************************************************************************
 * Copyright (c) 2013 Corporation and others.
 * 
 * Contributors:
 *     XXX Corporation 
 *******************************************************************************/
package net.vdrinkup.alpaca.service;

import net.vdrinkup.alpaca.flow.FlowDefinition;

/**
 * 代理服务接口
 * <p>
 * 该接口继承{@link Service}。
 * 代理服务与原子服务的区别在于，代理服务本身可以包含由许多原子服务组成的业务流程。
 * 执行代理服务，其实就是执行该代理服务包含流程所定义的各个原子服务。
 * </p>
 * @author liubing
 * Date 2013-11-17
 */
public interface ProxyService extends Service {

	public FlowDefinition getFlow();
	
}
