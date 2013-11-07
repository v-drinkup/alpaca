package net.vdrinkup.alpaca.servicebus.channel.service;

import net.vdrinkup.alpaca.servicebus.channel.model.Channel;

/**
 * 渠道service
 * @author wangcanpei
 *
 */
public interface ChannelService {

	/**
	 * 根据渠道id识别渠道
	 * @param id
	 * @return
	 */
	public Channel getChannelById(String id);	
	
}
