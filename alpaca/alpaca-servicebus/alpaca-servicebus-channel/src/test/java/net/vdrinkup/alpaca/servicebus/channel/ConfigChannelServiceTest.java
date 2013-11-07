package net.vdrinkup.alpaca.servicebus.channel;

import net.vdrinkup.alpaca.Env;
import net.vdrinkup.alpaca.servicebus.channel.service.ChannelService;
import net.vdrinkup.alpaca.servicebus.channel.service.config.ConfigChannelService;

import org.junit.Before;
import org.junit.Test;


public class ConfigChannelServiceTest {
	
	@Before
	public void init(){
		Env.getInstance().init( "D:\\work文档\\平台\\wms_platform\\wms\\wms-servicebus\\wms-servicebus-channel\\src\\test\\resources" );
	}

	@Test
	public void testGetChannelById(){
		ChannelService cs=new ConfigChannelService();
		cs.getChannelById("ws");
	}
}
