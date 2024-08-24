package com.org.zendesk.listener
import android.util.Log
import com.pusher.client.channel.PusherEvent
import com.pusher.client.channel.SubscriptionEventListener

class SuscriptionEventListenerImpl( onEvent : ( event : PusherEvent ) ->  Unit) : SubscriptionEventListener {
    override fun onEvent(event: PusherEvent?) {
        Log.i("PUSHER","Received event with data: $event")
        onEvent( event );
    }

}