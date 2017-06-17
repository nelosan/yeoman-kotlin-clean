package <%= appPackage %>.test.framework

import android.app.FragmentManager
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import <%= appPackage %>.R
import <%= appPackage %>.TestActivity
import <%= appPackage %>.view.fragment.BaseFragment
import junit.framework.Assert
import org.junit.runner.RunWith


/**
 * Created by nelo on 10/4/17.
 */
@RunWith(AndroidJUnit4::class)
open class FragmentTestRule<F: BaseFragment> : ActivityTestRule<TestActivity> {

    private var mFragmentClass: Class<F>
    private var mFragment: F? = null
    private val layout: Int

    constructor(fragmentClass: Class<F>, layout: Int) : super(TestActivity::class.java, true, false) {
        mFragmentClass = fragmentClass
        this.layout = layout
    }

    override fun afterActivityLaunched() {
        super.afterActivityLaunched()
        activity.runOnUiThread {
            try {
                val manager: FragmentManager = activity.fragmentManager
                val transaction = manager.beginTransaction()
                mFragment = mFragmentClass.getDeclaredConstructor(Int::class.java).newInstance(layout)
                transaction.replace(R.id.fragmentContainer, mFragment)
                transaction.commit()

            }
            catch (e: Exception){
                Assert.fail(String.format("%s: Could not insert %s into TestActivity: %s",
                        javaClass.getSimpleName(),
                        mFragmentClass.getSimpleName(),
                        e.message))
            }
        }
    }

    fun getFragment(): F?{
        return mFragment
    }

}