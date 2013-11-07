package net.vdrinkup.alpaca.servicebus.channel.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement( name = "channels" )
@XmlAccessorType( XmlAccessType.FIELD )
public class Channels implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7359834321057235198L;
	/**
	 * 渠道配置
	 */
	@XmlElement( name = "channel" )
	private List<Channel> channelList= new LinkedList< Channel >();
	public List<Channel> getChannelList() {
		return channelList;
	}
	public void setChannelList(List<Channel> channelList) {
		this.channelList = channelList;
	}

	
	
}
