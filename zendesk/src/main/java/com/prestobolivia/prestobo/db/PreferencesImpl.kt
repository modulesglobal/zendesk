package com.prestobolivia.prestobo.db

import android.content.Context
import android.content.SharedPreferences

internal  class PreferencesImpl() : PreferencesInterface {

    private var sharedPreferences: SharedPreferences? = null

    override fun initPreferences( context : Context ): Unit {
            sharedPreferences = context.getSharedPreferences(  PreferencesInterface.Key.key , Context.MODE_PRIVATE );
    }

    override fun getValueOfPreference(key: String): String {
             if( sharedPreferences == null ){
                 throw  Exception("No preferences initialized");
             }
          return  sharedPreferences!!.getString(key, "" )!!;
    }
}