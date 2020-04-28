package github.aeharake.choters

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import timber.log.Timber

class ApplicationController : Application() {

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this);
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}