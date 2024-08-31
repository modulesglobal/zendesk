package com.prestobolivia.prestobo.listener
import com.pusher.client.connection.ConnectionEventListener
import com.pusher.client.connection.ConnectionStateChange

internal  class ConnectionEventListenerImpl() : ConnectionEventListener {
    override fun onConnectionStateChange(change: ConnectionStateChange?) {
    }
    override fun onError(message: String?, code: String?, e: java.lang.Exception?) {
    }
}