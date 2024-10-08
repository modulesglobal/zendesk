package com.prestobolivia.prestobo.messaging

import com.pusher.client.channel.PusherEvent
internal interface ZendeskMessaging {
    fun initializeObjects();
    fun initializeConnection();
    fun subscribeToChannel();
    fun subscribeToEvent(onEvent : ( event : PusherEvent) ->  Unit);
}