package <%= appPackage %>.data.exceptions

import android.content.Context
import <%= appPackage %>.data.R

/**
 * Created by nelo on 1/3/17.
 */
class ServerError(context: Context) : RuntimeException(context.resources.getString(R.string.error_500))