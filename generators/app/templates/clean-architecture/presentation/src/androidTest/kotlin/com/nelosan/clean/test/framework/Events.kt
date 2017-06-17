package <%= appPackage %>.test.framework

import android.support.annotation.IdRes
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.matcher.ViewMatchers.withId

/**
 * Created by nelo on 22/3/17.
 */
class Events {

    fun clickOnView(@IdRes viewId: Int) {
        onView(withId(viewId)).perform(click())
    }

}