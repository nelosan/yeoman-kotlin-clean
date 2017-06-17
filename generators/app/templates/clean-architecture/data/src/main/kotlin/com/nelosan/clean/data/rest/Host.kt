package <%= appPackage %>.data.rest

import android.os.Build

/**
 * Created by nelo on 12/4/17.
 */
class Host {

    companion object{

        private val TESTHOST = ""
        private val RELEASEHOST = ""

        fun getHost(): String{
            if(Build.FINGERPRINT.startsWith("generic"))
                return TESTHOST
            return RELEASEHOST
        }
    }

}