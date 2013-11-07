package net.vdrinkup.alpaca.servicebus.channel;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import net.vdrinkup.alpaca.Env;
import net.vdrinkup.alpaca.servicebus.channel.model.Channel;
import net.vdrinkup.alpaca.servicebus.channel.model.Channels;
import net.vdrinkup.alpaca.servicebus.protocol.ProtocolConfigManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 渠道管理类
 * <p>
 * 注册当前所有渠道实例配置
 * </p>
 * @author wangcanpei
 *
 */
public class ChannelConfigManager {

	private static Logger LOG = LoggerFactory.getLogger( ChannelConfigManager.class );
	
	private static volatile ChannelConfigManager _instance;
	
	/**
	 * 禁止该类被实例化
	 */
	private ChannelConfigManager(){
		
	};
	
	/**
	 * 获取该类的实例
	 * @return
	 */
	public static ChannelConfigManager getInstance() {
		if ( _instance == null ) {
			synchronized (  ProtocolConfigManager.class ) {
				if ( _instance == null ) {
					_instance = new ChannelConfigManager();
				}
			}
		}
		return _instance;
	}
	
	//渠道与服务的对应关系map
	private Map< String, Channel > registry = new ConcurrentHashMap< String, Channel >( 16 );
	
	/**
	 * 注册所有的渠道
	 */
	public void readAll() {
		Channels channels=readChannelsFromxml();
		if(null!=channels){
			List<Channel> list=channels.getChannelList();
			for(Channel chl:list){
				registry.put(chl.getId(), chl);
			}
		}
	}
	
	private Channels readChannelsFromxml(){
		StringBuilder channel_conf_path = new StringBuilder( Env.getInstance().getConfPath() );
		channel_conf_path.append( "channel" ).append( File.separator ).append( "channels.xml" );
		final File channel_conf_file = new File( channel_conf_path.toString() );
		Channels channels=null;
		try {
			final JAXBContext context = JAXBContext.newInstance( Channels.class);
			final Unmarshaller unmarshaller = context.createUnmarshaller();
			channels = ( Channels ) unmarshaller.unmarshal( channel_conf_file );
		} catch (JAXBException e) {
			LOG.error("解析channels.xml错误",e);
		}
		return channels;
	}
	
	/**
	 * 根据id获取到该渠道
	 * @param id
	 * @return
	 */
	public Channel getChannelById(String id){
		return this.registry.get(id);
	}
	
	/**
	 * 得到注册map里的所有数据
	 */
	public Map< String, Channel > getAll() {
		return this.registry;
	}
	
}
