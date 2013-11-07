/*******************************************************************************
 * Copyright (c) 2013 JD Corporation and others.
 * 
 * Contributors:
 *     JD Corporation 
 *******************************************************************************/
package net.vdrinkup.alpaca.servicebus.protocol.spi.ws;

import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import net.vdrinkup.alpaca.servicebus.protocol.ProtocolConfigProcessor;
import net.vdrinkup.alpaca.servicebus.protocol.config.ProtocolConfig;
import net.vdrinkup.alpaca.servicebus.protocol.spi.ws.config.WsProtocolConfig;


/**
 *
 * <p></p>
 * @author liubing
 * Date Nov 1, 2013
 */
public class WsConfigProcessor implements ProtocolConfigProcessor {
	
	private static final String JAXB_CONTEXT;
	
	static {
		final StringBuilder sb = new StringBuilder( 
				ProtocolConfig.class.getPackage().getName() );
		sb.append( ":" ).append( WsProtocolConfig.class.getPackage().getName() );
		JAXB_CONTEXT = sb.toString();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.jd.wms.servicebus.protocol.ProtocolConfigProcessor#read(java.io.InputStream)
	 */
	@Override
	public ProtocolConfig read( InputStream is ) throws Exception {
		if ( is == null ) {
			throw new IllegalArgumentException( "The input stream can not be null." );
		}
		final JAXBContext context = JAXBContext.newInstance( JAXB_CONTEXT, 
				this.getClass().getClassLoader() );
		final Unmarshaller unmarshaller = context.createUnmarshaller();
		final ProtocolConfig config = ( WsProtocolConfig ) unmarshaller.unmarshal( is );
		return config;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.jd.wms.servicebus.protocol.ProtocolConfigProcessor#write(com.jd.wms.servicebus.protocol.config.ProtocolConfig, java.io.OutputStream)
	 */
	@Override
	public void write( ProtocolConfig config, OutputStream os ) throws Exception {
		if ( config == null ) {
			throw new IllegalArgumentException( "The config can not be null." );
		}
		final JAXBContext context = JAXBContext.newInstance( JAXB_CONTEXT, 
				this.getClass().getClassLoader() );
		final Marshaller marshaller = context.createMarshaller();
		marshaller.marshal( config, os );
	}

}
