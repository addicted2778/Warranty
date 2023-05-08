package com.warranty

import android.app.Application
import android.content.Context
import org.acra.ACRA
import org.acra.ReportingInteractionMode
import org.acra.annotation.ReportsCrashes


@ReportsCrashes(formKey = "", mailTo = "tortugaapp@whyq.sg", mode = ReportingInteractionMode.SILENT)
class MyApplication : Application() {

    //private var mFirebaseAnalytics: FirebaseAnalytics? = null

    override fun onCreate() {
        super.onCreate()

        ACRA.init(this)

        //  mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)

        if (isConnectedWithStagin) {
            doStagingTask()
        } else {
            doLiveTask()
        }

        assignURL()
        setUpCrashlytics()

        // FirebaseApp.initializeApp(applicationContext)
        // AppEventsLogger.activateApp(this)

        // setAutoLogAppEventsEnabled(true)
    }

    private fun setUpCrashlytics() {
        // FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true)
        //  val crashlytics = FirebaseCrashlytics.getInstance()
        //  crashlytics.setCustomKey("Mobile", AppData.getString(applicationContext, Constant.CONTACT_NUMBER).toString())
    }

    fun doStagingTask() {
        BASE_URL = "https://kbdevs.com/warranty/apiv1/"
    }

    fun doLiveTask() {
        BASE_URL = "https://kbdevs.com/warranty/apiv1/"

    }

    fun assignURL() {

        SIGNUP_URL = BASE_URL + "user/login"
        SUBMIT_OTP = BASE_URL + "user/verifyOTP"

    }

    companion object {

        const val isConnectedWithStagin = true
        var BASE_URL = ""
        var SIGNUP_URL = ""
        var SUBMIT_OTP = ""


        private var instance: MyApplication? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }

        @Synchronized
        fun getInstance(): MyApplication {
            return instance!!
        }
    }

    init {
        instance = this
    }
}