package com.org.zendesk.listener
import android.util.Log
import com.pusher.client.connection.ConnectionEventListener
import com.pusher.client.connection.ConnectionStateChange

internal  class ConnectionEventListenerImpl() : ConnectionEventListener {
    override fun onConnectionStateChange(change: ConnectionStateChange?) {
    }
    override fun onError(message: String?, code: String?, e: java.lang.Exception?) {
    }
}