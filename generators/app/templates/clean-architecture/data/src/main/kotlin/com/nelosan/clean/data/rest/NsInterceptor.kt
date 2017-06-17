package <%= appPackage %>.data.rest

import android.content.Context
import <%= appPackage %>.data.exceptions.NotFoundException
import <%= appPackage %>.data.exceptions.ServerError
import <%= appPackage %>.data.exceptions.TimeoutException
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.net.SocketTimeoutException

/**
 * Created by nelo on 1/3/17.
 */
class NsInterceptor(val context: Context) : Interceptor{

    override fun intercept(chain: Interceptor.Chain?): Response {
        val request = getRequest(chain)

        try {

            val response = chain!!.proceed(request)
            when(response!!.code()){
                  500 -> throw ServerError(context)
                  404 -> throw NotFoundException(context)
            }

            return response

            } catch (exception: SocketTimeoutException) {
                  exception.printStackTrace()
                  throw TimeoutException(context)
            }

    }

    fun getRequest(chain: Interceptor.Chain?): Request{
        return chain!!.request()
    }

}