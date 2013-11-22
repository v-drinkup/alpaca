package net.vdrinkup.alpaca.protocol.config;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum( String.class )
public enum ContentType {
	
	@XmlEnumValue( value = "text" )TEXT,
	@XmlEnumValue( value = "byte" )BYTE,

}
