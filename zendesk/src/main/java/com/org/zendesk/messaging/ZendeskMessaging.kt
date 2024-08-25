package com.org.zendesk.messaging

import com.pusher.client.channel.PusherEvent
interface ZendeskMessaging {
    fun initializeObjects();
    fun initializeConnection();
    fun subscribeToChannel();
    fun subscribeToEvent(onEvent : ( event : PusherEvent) ->  Unit);
}