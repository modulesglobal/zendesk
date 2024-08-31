package com.prestobolivia.prestobo.db

import android.content.Context

internal interface PreferencesInterface {

      object  Key {
            val key : String = "FlutterSecureStorage"
      }
      fun initPreferences(context : Context)
      fun getValueOfPreference( key : String ) : String

}