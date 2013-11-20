/*******************************************************************************
 * Copyright (c) 2013 Corporation and others.
 * 
 * Contributors:
 *     XXX Corporation 
 *******************************************************************************/
package net.vdrinkup.alpaca.transformation.config;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;

import net.vdrinkup.alpaca.configuration.Processor;
import net.vdrinkup.alpaca.configuration.ProcessorDefinition;

/**
 * 
 * <p>
 * </p>
 * 
 * @author liubing Date 2013-11-16
 */
@XmlRootElement( name = "transformation" )
public class TransformationConfig extends ProcessorDefinition {

	private static NormalizedStringAdapter adapter = new NormalizedStringAdapter();

	@XmlAttribute
	protected String from;
	@XmlAttribute
	protected String fromType;
	@XmlAttribute
	protected String to;
	@XmlAttribute
	protected String toType;
	@XmlElementRefs( { @XmlElementRef( type = ProcessorDefinition.class ) } )
	@XmlAnyElement
	@XmlMixed
	protected List< Object > elements;

	public String getFrom() {
		return from;
	}

	public void setFrom( String from ) {
		this.from = from;
	}

	public String getFromType() {
		return fromType;
	}

	public void setFromType( String fromType ) {
		this.fromType = fromType;
	}

	public String getTo() {
		return to;
	}

	public void setTo( String to ) {
		this.to = to;
	}

	public String getToType() {
		return toType;
	}

	public void setToType( String toType ) {
		this.toType = toType;
	}

	public List< Object > getElements() {
		return elements;
	}

	public void setElements( List< Object > elements ) {
		this.elements = elements;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.vdrinkup.alpaca.configuration.ProcessorDefinition#createProcessor()
	 */
	@Override
	public Processor createProcessor() {
		// TODO Auto-generated method stub
		return null;
	}

	protected void afterUnmarshal( Unmarshaller u, Object parent ) {
		afterUnmarshal: {
			if ( this.elements == null || this.elements.size() == 0 ) {
				break afterUnmarshal;
			}
			final List< Object > result = new LinkedList< Object >();
			Object obj = null;
			for ( int i = 0; i < this.elements.size(); i++ ) {
				obj = this.elements.get( i );
				if ( obj instanceof String ) {
					obj = adapter.unmarshal( ( ( String ) obj ) ).trim();
					if ( "".equals( obj ) ) {
						continue ;
					}
				}
				result.add( obj );
			}
			this.elements.clear();
			this.elements = result;
		}
	}

}
