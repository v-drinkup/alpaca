package net.vdrinkup.alpaca.servicebus.channel.service.config;


import net.vdrinkup.alpaca.servicebus.channel.ChannelConfigManager;
import net.vdrinkup.alpaca.servicebus.channel.model.Channel;
import net.vdrinkup.alpaca.servicebus.channel.service.ChannelService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * 读取配置文件来获取渠道
 * @author wangcanpei
 *
 */
public class ConfigChannelService implements ChannelService{

	private static Logger LOG = LoggerFactory.getLogger( ConfigChannelService.class );
	private static ChannelConfigManager chM=ChannelConfigManager.getInstance();
	static{
		chM.readAll();
	}
	
	@Override
	public Channel getChannelById(String id) {
		
		Channel chl= chM.getChannelById(id);
		if(LOG.isDebugEnabled()){
			LOG.debug("Channel为"+chl.getServiceName());
		}
		return chl;
	}

}
