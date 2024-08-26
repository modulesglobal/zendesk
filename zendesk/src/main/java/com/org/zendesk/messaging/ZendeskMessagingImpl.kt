package com.org.zendesk.messaging
import android.util.Log
import com.org.zendesk.listener.ConnectionEventListenerImpl
import com.org.zendesk.listener.SuscriptionEventListenerImpl
import com.pusher.client.Pusher
import com.pusher.client.PusherOptions
import com.pusher.client.channel.Channel
import com.pusher.client.channel.PusherEvent


internal  class ZendeskMessagingImpl() : ZendeskMessaging {

    var pusherOptions : PusherOptions? = null;
    var pusher : Pusher? = null;
    var channel : Channel? = null;

    override  fun initializeObjects(){
        pusherOptions = PusherOptions();
        pusherOptions?.setCluster("sa1");
        pusher = Pusher("d9342c3624e53e98e4a1", pusherOptions );
    }
    override fun initializeConnection() {
        if ( pusher == null ){
            throw Exception("No initialized pusher");
        }

        pusher!!.connect(  ConnectionEventListenerImpl()  )
    }

    override fun subscribeToChannel() {
        if ( pusher == null ){
            throw Exception("No initialized pusher");
        }
        if( channel == null ){
            channel = pusher!!.subscribe("my-channel");
        }else{
            if(!channel!!.isSubscribed){
                channel = pusher!!.subscribe("my-channel");
            }
        }
    }

    override fun subscribeToEvent(onEvent : ( event : PusherEvent) ->  Unit) {
        if( channel == null ){
            throw Exception("No initialized channel");
        }
        channel!!.bind("my-event" ,  SuscriptionEventListenerImpl( onEvent )  );

    }
}