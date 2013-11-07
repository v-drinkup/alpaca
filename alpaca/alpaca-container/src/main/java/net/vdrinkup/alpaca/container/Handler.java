package net.vdrinkup.alpaca.container;

public interface Handler {
	
	public < T > void handle( T t ) throws Exception;

}
