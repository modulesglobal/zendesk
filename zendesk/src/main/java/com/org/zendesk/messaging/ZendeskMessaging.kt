package com.org.zendesk.messaging

import com.pusher.client.channel.PusherEvent
interface ZendeskMessaging {
    fun initializeObjects();
    fun initializeConnection();
    fun initializeChannel();
    fun subscribeToChannel(onEvent : ( event : PusherEvent) ->  Unit);
}