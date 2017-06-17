package <%= appPackage %>.presentation.presenter

import android.os.Bundle
import android.support.annotation.NonNull
import <%= appPackage %>.presentation.view.view.BaseView

/**
 * Created by nelo on 20/2/17.
 */
interface BasePresenter {

    fun onDestroy()
    fun setView(@NonNull v: BaseView)
}