package net.vdrinkup.alpaca.servicebus.protocol;

public interface Reconnected {

	public void reconnect( Connection connector ) throws Exception;

}
