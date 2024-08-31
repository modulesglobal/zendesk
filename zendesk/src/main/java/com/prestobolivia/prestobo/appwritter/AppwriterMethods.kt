package com.prestobolivia.prestobo.appwritter

import android.content.Context

internal  interface AppwriterMethods {

      fun initClient( context : Context ) : Unit;
      suspend fun isActiveDisableWrapper() : Boolean;

      suspend fun saveData( id : String , token : String , refreshToken : String )

}