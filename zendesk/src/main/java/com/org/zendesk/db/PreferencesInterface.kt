package com.org.zendesk.db

import android.content.Context
import android.content.SharedPreferences

internal interface PreferencesInterface {

      object  Key {
            val key : String = "FlutterSecureStorage"
      }
      fun initPreferences(context : Context)
      fun getValueOfPreference( key : String ) : String

}