package <%= appPackage %>.presentation.view.activity

import android.app.Fragment
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import <%= appPackage %>.presentation.AndroidApplication
import <%= appPackage %>.presentation.navigation.Navigator


/**
 * Created by nelo on 20/2/17.
 */
open class BaseActivity : AppCompatActivity() {

    val app: AndroidApplication get() = application as AndroidApplication
    val navigator: Navigator get() = app.navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    protected fun addFragment(containerViewId: Int, fragment: Fragment) {
        val fragmentTransaction = this.fragmentManager.beginTransaction()
        fragmentTransaction.add(containerViewId, fragment)
        fragmentTransaction.commit()
    }

    fun changeFragment(containerViewId: Int, fragment: Fragment) {
        val transaccion = fragmentManager.beginTransaction()
        transaccion.replace(containerViewId, fragment)
        transaccion.addToBackStack(null)
        transaccion.commit()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

}
