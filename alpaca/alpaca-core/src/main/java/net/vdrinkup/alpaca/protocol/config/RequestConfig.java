package net.vdrinkup.alpaca.protocol.config;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

/**
 * 请求配置抽象基类
 * <p>
 * 所有不同协议的请求配置都继承该类
 * </p>
 * @author pluto.bing.liu
 *
 */
@XmlType
public class RequestConfig extends AbstractAttributeDefinition {
	
	protected List< ParamConfig > params = new LinkedList< ParamConfig >();

	public List< ParamConfig > getParams() {
		return params;
	}

	public void setParams( List< ParamConfig > params ) {
		this.params = params;
	}

}
