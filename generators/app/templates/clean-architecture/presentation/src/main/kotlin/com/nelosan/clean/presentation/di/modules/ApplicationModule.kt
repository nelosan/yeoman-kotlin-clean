package <%= appPackage %>.presentation.di.modules

import android.app.Application
import dagger.Module
import javax.inject.Singleton
import dagger.Provides
import javax.inject.Named
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers

/**
 * Created by nelo on 20/2/17.
 */
@Module
class ApplicationModule(private val application: Application) {

    @Provides @Named("executor_thread") fun provideExecutorThread() = Schedulers.newThread()

    @Provides @Named("ui_thread") fun provideUiThread() =  AndroidSchedulers.mainThread()

    @Provides @Singleton fun provideApplication() = application

}