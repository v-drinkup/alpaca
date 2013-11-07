package net.vdrinkup.alpaca.resource;

public interface ResourceScanner {
	
	public < R > void scan( R resource, ResourceFilter filter );

}
