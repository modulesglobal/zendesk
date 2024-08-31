package com.org.zendesk

import android.app.Activity
import android.app.AlertDialog
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.startup.Initializer
import com.org.zendesk.appwritter.Appwriter
import com.org.zendesk.appwritter.AppwriterMethods
import com.org.zendesk.db.PreferencesImpl
import com.org.zendesk.db.PreferencesInterface
import com.org.zendesk.messaging.ZendeskMessaging
import com.org.zendesk.messaging.ZendeskMessagingImpl
import kotlinx.coroutines.runBlocking


object  ZendeskGlobal {
     fun notifyToUser(){

     }
}

private object ModalHelper {
    private var alertDialog: AlertDialog? = null
    fun showModal(activity: Activity) {
        if( alertDialog == null ){
            activity.runOnUiThread {
                val inflater = LayoutInflater.from( activity );
                val view = inflater.inflate( R.layout.message_window , null);
                alertDialog =  AlertDialog.Builder(activity, R.style.CustomAlertDialog )
                    .setView( view )
                    .setCancelable(false)
                    .create()
                alertDialog!!.setCanceledOnTouchOutside(false);
                alertDialog!!.show();
            }
        }
    }

    fun dismissModal(){
        alertDialog?.let {
            it.dismiss()
            alertDialog = null
        }
    }
}

class MyApp : Initializer<Unit> {

    private var zendeskMessaging: ZendeskMessaging? = null
    private var appWritter : AppwriterMethods? = null;
    private var flutterSecureStorage :   FlutterSecureStorage? = null;
    override fun create(context: Context) {
        listenLifeCicyleOfApp( context );
        flutterSecureStorage = FlutterSecureStorage( context , hashMapOf()  );
        appWritter = Appwriter();
        appWritter!!.initClient(context);
        zendeskMessaging = ZendeskMessagingImpl();
        zendeskMessaging!!.initializeObjects();
        zendeskMessaging!!.initializeConnection();

    }
    override  fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return kotlin.collections.mutableListOf();
    }

    private suspend fun getCredentials(){
            val id : String? =  flutterSecureStorage!!.read(flutterSecureStorage!!.addPrefixToKey("uid")) ;
            val token : String? = flutterSecureStorage!!.read(flutterSecureStorage!!.addPrefixToKey("uid"));
            val refreshToken : String? = flutterSecureStorage!!.read(flutterSecureStorage!!.addPrefixToKey("uid"));
            if( id != null && token != null && refreshToken != null && id.isNotEmpty() && token.isNotEmpty() && refreshToken.isNotEmpty() ){
                appWritter!!.saveData( id , token, refreshToken );
            }else{
                appWritter!!.saveData( "none" , "emptyToken", "emptyRefreshToken" );
            }
    }

    private fun listenLifeCicyleOfApp(context: Context){
        (context.applicationContext as? Application)?.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityResumed(activity: Activity) {
                zendeskMessaging!!.subscribeToChannel();
                zendeskMessaging!!.subscribeToEvent { it ->
                    it.data.let {
                         if( it.toInt() == 1 ){
                             ModalHelper.showModal(activity)
                         }else{
                            ModalHelper.dismissModal();
                         }
                    }
                }

                runBlocking {
                  val isActiveDisableWrappter =  appWritter!!.isActiveDisableWrapper();
                   if( isActiveDisableWrappter ){
                       ModalHelper.showModal(activity)
                   } else{
                       ModalHelper.dismissModal();
                   }
                    getCredentials();
                }
            }
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}
            override fun onActivityStarted(activity: Activity) {}
            override fun onActivityPaused(activity: Activity) {}
            override fun onActivityStopped(activity: Activity) {}
            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
            override fun onActivityDestroyed(activity: Activity) {}
        })
    }

}