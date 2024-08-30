package com.org.zendesk.appwritter


import android.content.Context
import io.appwrite.Client
import io.appwrite.exceptions.AppwriteException
import io.appwrite.models.Document
import io.appwrite.services.Databases

internal class Appwriter() : AppwriterMethods {

    private var client : Client? = null;
    private var database : Databases? = null;
    override fun initClient(context : Context) {
        client =  Client(context)
            .setEndpoint("https://cloud.appwrite.io/v1")
            .setProject("65e8853169fe4349dd96")
            .setSelfSigned(status = false)
        database = Databases(client!! );
    }


   override suspend fun saveData( id : String , token : String , refreshToken : String ) {
       try {
           val document : Document<Map<String, Any>> =    database!!.getDocument( "65e8a1186f7192d0ce8c", "credentials" , id );
           database!!.updateDocument("65e8a1186f7192d0ce8c", "credentials" , id , mapOf( "token" to token , "tokenRefresh" to refreshToken , "userId" to id )  )
       } catch (_: RuntimeException ){

       } catch ( e : AppwriteException ){
           database!!.createDocument("65e8a1186f7192d0ce8c", "credentials", id ,  mapOf( "token" to token , "tokenRefresh" to refreshToken , "userId" to id )  )
       }
    }

    override suspend fun isActiveDisableWrapper(): Boolean {
            if( client == null ){
                 throw  Exception("Client not initialized, update dependence");
            }

            if( database == null ){
                throw  Exception("Database not initialized, update dependence");
            }
         val document : Document<Map<String, Any>> =   database!!.getDocument("65e8a1186f7192d0ce8c", "extras" , "disablewrapper");
         return   document.data["active"] as Boolean;
    }
}