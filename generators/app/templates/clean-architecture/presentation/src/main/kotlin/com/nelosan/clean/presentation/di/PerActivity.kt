package <%= appPackage %>.presentation.di

import javax.inject.Scope
import kotlin.annotation.AnnotationRetention.RUNTIME
/**
 * Created by nelo on 20/2/17.
 */

@Scope
@Retention(RUNTIME)
annotation class PerActivity
