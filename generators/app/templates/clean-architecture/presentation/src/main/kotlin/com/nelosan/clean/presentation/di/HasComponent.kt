package <%= appPackage %>.presentation.di

/**
 * Created by nelo on 21/2/17.
 */
interface HasComponent<C> {
    fun getSubComponent(): C
}