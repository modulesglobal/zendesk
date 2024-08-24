package com.org.zendesk.listener
import android.util.Log
import com.pusher.client.connection.ConnectionEventListener
import com.pusher.client.connection.ConnectionStateChange

class ConnectionEventListenerImpl() : ConnectionEventListener {
    override fun onConnectionStateChange(change: ConnectionStateChange?) {
        Log.i("PUSHER", "connection done" );
    }
    override fun onError(message: String?, code: String?, e: java.lang.Exception?) {
        Log.i("PUSHER", "connection error $message" );
    }
}