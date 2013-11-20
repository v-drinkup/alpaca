/*******************************************************************************
 * Copyright (c) 2013 Corporation and others.
 * 
 * Contributors:
 *     XXX Corporation 
 *******************************************************************************/
package net.vdrinkup.alpaca.service;

import java.util.Collection;
import java.util.List;

import net.vdrinkup.alpaca.HelperProvider;

/**
 * 服务管理器
 * <p>
 * 服务管理器管理运行时服务的实例。
 * 管理器使用{@link ServiceEntry}管理服务实例。
 * </p>
 * @author liubing
 * Date 2013-11-17
 */
public interface ServiceManager {
	
	ServiceManager INSTANCE = HelperProvider.INSTANCE.serviceManager();
	
	/**
	 * 获得当前所有服务注册项
	 * @return
	 */
	public Collection< ServiceEntry > entries();
	/**
	 * 撤销注册项
	 * <p>
	 * 撤销注册通过关键字类型{@link KeyType}，选择匹配方式
	 * 如果是SINGLE，则严格按照服务ID进行匹配
	 * 如果是BATCH，则按照组件名称进行匹配
	 * </p>
	 * @param key 服务关键字
	 * @param 关键字类型
	 * @return 对应服务项
	 */
	public ServiceEntry unregister( String key, KeyType type );
	/**
	 * 按ID查询服务注册项
	 * @param id 服务ID
	 * @return 对应服务项
	 */
	public ServiceEntry lookup( String id );
	/**
	 * 绑定服务与流程
	 * @param id 服务ID
	 * @param flowId 流程ID
	 */
	public void bind( String id, String flowId );
	/**
	 * 解绑服务与流程
	 * @param id 服务ID
	 * @param flowId 流程ID
	 */
	public void unbind( String id, String flowId );
	/**
	 * 显示ID对应服务所关联的流程
	 * @param id 服务ID
	 * @return
	 */
	public List< String > showBind( String id );
	/**
	 * 按组件注册服务信息
	 * @param component
	 */
	public void register( ServiceEntry entry );
	
}
