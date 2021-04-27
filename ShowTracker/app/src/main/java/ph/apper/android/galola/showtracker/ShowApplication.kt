package ph.apper.android.galola.showtracker

import android.app.Application

class ShowApplication : Application() {
    companion object {
        var userLocation:String = ""
    }

    override fun onCreate() {
        super.onCreate()
    }
}