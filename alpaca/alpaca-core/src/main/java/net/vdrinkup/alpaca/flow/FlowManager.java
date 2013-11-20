/*******************************************************************************
 * Copyright (c) 2013 Corporation and others.
 * 
 * Contributors:
 *     XXX Corporation 
 *******************************************************************************/
package net.vdrinkup.alpaca.flow;

/**
 * 流程管理器
 * <p>
 * 定义CRUD方法，用于内存中的流程对象的管理。包括：
 * 1.添加新流程（C）。
 * 2.提供按名称（ID）查找流程定义（R）。
 * 3.更新当前已有流程（U），更新操作是两个操作的结合，
 *   先反注册原有定义再重新注册ID相同的新流程。
 * 4.删除当前已有流程（D）。
 * </p>
 * @author liubing
 * Date 2013-11-17
 */
public interface FlowManager {
	
	/**
	 * 注册流程
	 * <p>如果当前已经存在相同名称的流程定义，则抛出{@link FlowExistException}</p>
	 * @param id
	 * @param definition
	 * @return
	 */
	public boolean register( FlowDefinition definition );
	
	/**
	 * 反注册流程（删除流程）
	 * @param id
	 * @return
	 */
	public FlowDefinition unregister( String id );
	
	/**
	 * 按ID查找流程定义
	 * @param id
	 * @return
	 */
	public FlowDefinition lookup( String id );
	
}
