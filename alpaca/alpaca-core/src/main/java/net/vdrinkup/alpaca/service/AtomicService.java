/*******************************************************************************
 * Copyright (c) 2013 Corporation and others.
 * 
 * Contributors:
 *     XXX Corporation 
 *******************************************************************************/
package net.vdrinkup.alpaca.service;

/**
 * 原子服务接口
 * <p>
 * 原子服务是最小力度的服务，该接口继承{@link Service}
 * 从业务的角度看，原子服务是不可再分的业务功能。
 * 为保证服务力度，原子服务的实现应遵循“单一职责原则”。
 * </p>
 * @author liubing
 * Date 2013-11-17
 */
public interface AtomicService extends Service {
}
