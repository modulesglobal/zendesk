package com.org.zendesk

import android.app.Activity
import android.app.AlertDialog
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.startup.Initializer
import com.org.zendesk.messaging.ZendeskMessaging
import com.org.zendesk.messaging.ZendeskMessagingImpl

private object ModalHelper {
    fun showModal(context: Context) {
        val inflater = LayoutInflater.from( context );
        val view = inflater.inflate( R.layout.message_window , null);
        val alertDialog =  AlertDialog.Builder(context, R.style.CustomAlertDialog )
            .setView( view )
            .setCancelable(false)
            .create()
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }
}

class MyApp : Initializer<Unit> {

    var zendeskMessaging: ZendeskMessaging? = null
    override fun create(context: Context) {
        listenLifeCicyleOfApp( context );
        zendeskMessaging = ZendeskMessagingImpl();
        zendeskMessaging!!.initializeObjects();
        zendeskMessaging!!.initializeConnection();
    }
    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return kotlin.collections.mutableListOf();
    }

    private fun listenLifeCicyleOfApp(context: Context){
        (context.applicationContext as? Application)?.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityResumed(activity: Activity) {
                zendeskMessaging!!.initializeChannel();
                zendeskMessaging!!.subscribeToChannel {
                    Log.i("PUSHER", "Message received ${it.data}")
                }
                ModalHelper.showModal(activity)
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