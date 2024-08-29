package com.org.zendesk.appwritter

import android.app.Activity
import android.content.Context
import io.appwrite.Client

internal  interface AppwriterMethods {

      fun initClient( context : Context ) : Unit;
      suspend fun isActiveDisableWrapper() : Boolean;

}