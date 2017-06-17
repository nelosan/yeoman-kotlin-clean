package <%= appPackage %>.domain.interactor

import io.reactivex.observers.DisposableObserver

/**
 * Created by nelo on 27/2/17.
 */
open class DefaultObserver<T> : DisposableObserver<T>() {

    override fun onNext(t: T) {}

    override fun onComplete() {}

    override fun onError(exception: Throwable ){}
}