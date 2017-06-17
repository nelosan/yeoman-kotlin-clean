package <%= appPackage %>.presentation.view.fragment

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import butterknife.ButterKnife
import butterknife.Unbinder
import <%= appPackage %>.presentation.di.HasComponent
import <%= appPackage %>.presentation.view.activity.BaseActivity

/**
 * Created by nelo on 21/2/17.
 */
open class BaseFragment(val layout: Int) : Fragment(){

    private lateinit var unbinder: Unbinder

    val app get() = (activity as BaseActivity).app

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val fragmentView = inflater?.inflate(layout, container, false)
        unbinder = ButterKnife.bind(this, fragmentView!!)
        return fragmentView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unbinder.unbind()
    }

    fun showToastMessage(message: String){
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    protected fun <C> getComponent(componentType: Class<C>): C {
        return componentType.cast((activity as HasComponent<C>).getSubComponent())
    }

}