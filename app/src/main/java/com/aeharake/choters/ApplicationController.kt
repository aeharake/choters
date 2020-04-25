package com.aeharake.choters

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco

class ApplicationController : Application() {

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this);
    }
}