package com.org.zendesk.appwritter

import android.app.Activity
import android.content.Context
import io.appwrite.Client
import io.appwrite.models.Document
import io.appwrite.services.Databases

internal class Appwriter() : AppwriterMethods {

    private var client : Client? = null;
    override fun initClient(context : Context) {
        client =  Client(context)
            .setEndpoint("https://cloud.appwrite.io/v1")
            .setProject("65e8853169fe4349dd96")
            .setSelfSigned(status = false)
    }

    override suspend fun isActiveDisableWrapper(): Boolean {
            if( client == null ){
                 throw  Exception("Client not initialized, update dependence");
            }
         val document : Document<Map<String, Any>> =   Databases( client!! ).getDocument("65e8a1186f7192d0ce8c", "extras" , "disablewrapper");
         return   document.data.get("active") as Boolean;
    }
}