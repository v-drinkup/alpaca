package net.vdrinkup.alpaca.servicebus.protocol.config;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum( String.class )
public enum SideEnum {
	
	@XmlEnumValue( value = "server" )SERVER,
	
	@XmlEnumValue( value = "client" )CLIENT

}
