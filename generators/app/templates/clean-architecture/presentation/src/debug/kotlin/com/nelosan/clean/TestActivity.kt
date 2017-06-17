package <%= appPackage %>.almacen

import android.os.Bundle
import android.widget.FrameLayout
import <%= appPackage %>.almacen.view.activity.BaseActivity

/**
 * Created by nelo on 10/4/17.
 */
class TestActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var framelayout = FrameLayout(this)
        framelayout.id = R.id.fragmentContainer
        setContentView(framelayout)
    }
}