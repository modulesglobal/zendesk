package com.org.zendesk.listener
import android.util.Log
import com.pusher.client.channel.PusherEvent
import com.pusher.client.channel.SubscriptionEventListener

internal class SuscriptionEventListenerImpl( private val  onEventCallBack : ( event : PusherEvent ) ->  Unit) : SubscriptionEventListener {
    override fun onEvent(event: PusherEvent?) {
        event?.let {
            onEventCallBack( event );
        }
    }

}