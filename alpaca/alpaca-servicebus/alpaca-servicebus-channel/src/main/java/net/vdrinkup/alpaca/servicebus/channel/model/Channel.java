package net.vdrinkup.alpaca.servicebus.channel.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * 通道model
 * @author wangcanpei
 *
 */
@XmlRootElement( name = "channel" )
@XmlAccessorType( XmlAccessType.FIELD )
public class Channel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7634394487509298170L;
	
	/**
	 * 渠道id
	 */
	@XmlElement(name="id")
	private String id;
	
	/**
	 * 渠道名称
	 */
	@XmlElement(name="serviceName")
	private String serviceName;
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	
}
