/*******************************************************************************
 * Copyright (c) 2013 Corporation and others.
 * 
 * Contributors:
 *     XXX Corporation 
 *******************************************************************************/
package net.vdrinkup.alpaca.service;

import java.util.LinkedList;
import java.util.List;

import net.vdrinkup.alpaca.service.definition.ServiceDefinition;

/**
 * 服务管理项
 * <p>
 * 该类用于维护服务ID与其实例(instance)以及调用服务的流程列表（flow）列表。
 * 服务的流程调用列表，是一个链表，用于保存调用当前服务ID对应服务实例的流程ID，
 * 使用该链表在发送变更通知时，作为消息接收者参考列表。
 * 流程接到服务变更通知后的操作，参见{@link FlowEngine}相关说明。
 * </p>
 * <p>
 * ID的组成格式："组件名$服务名称-版本信息"。
 * "$"与"-"是分隔符，服务名称由{@link ServiceDefinition.id}指定。
 * 版本信息通过{@link ServiceDefinition.version}指定。
 * example："test$testService-1.0"，表示在test.jar中名称为testService的1.0版本实例。
 * 按照以上的ID构成，可实现服务定义的多态性。
 * example:
 * 
 * 限制在于：服务配置时，不允许定义同一软件包下相同版本的相同服务。
 * </p>
 * @author liubing
 * Date 2013-11-18
 */
public final class ServiceEntry {
		
	private String id;
	
	private Service instance;
	
	private ServiceDefinition definition;
	
	private List< String > callers = new LinkedList< String >();
	/**
	 * 添加调用方
	 * @param called
	 */
	public void addCaller( String callerId ) {
		callers.add( callerId );
	}
	/**
	 * 移除调用方
	 * @param called
	 */
	public void removeCaller( String caller ) {
		callers.remove( caller );
	}
	
	public String getId() {
		return id;
	}

	public void setId( String id ) {
		this.id = id;
	}

	public Service getInstance() {
		return instance;
	}

	public void setInstance( Service instance ) {
		this.instance = instance;
	}

	public List< String > getCallers() {
		return callers;
	}

	public void setCallers( List< String > callers ) {
		this.callers = callers;
	}

	public ServiceDefinition getDefinition() {
		return definition;
	}
	
	public void setDefinition( ServiceDefinition definition ) {
		this.definition = definition;
	}

	/**
	 * 分隔符
	 * @author liubing
	 * Date 2013-11-18
	 */
	public class Separator {
		
		private Separator() {
		}
		
		public static final String PACKAGE_SEPARATOR = "$";
		
		public static final String VERSION_SEPARATOR = "-";
		
	}

}
