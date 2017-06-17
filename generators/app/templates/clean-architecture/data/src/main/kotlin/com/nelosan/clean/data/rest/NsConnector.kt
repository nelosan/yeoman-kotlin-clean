package <%= appPackage %>.data.rest


import android.app.Application
import <%= appPackage %>.data.parser.Serializer
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by nelo on 16/2/17.
 */
class NsConnector {

    var ENDPOINT = Host.getHost()

    val CONNECTION_TIMEOUT: Long = 10

    val application: Application
    val serializer: Serializer
    lateinit var vnAdapter: Retrofit

    constructor(application: Application) {
        this.application = application
        createApi("user", "pass", "1")
        serializer = Serializer()
    }

    fun createApi(user: String, pass: String, version: String) {
        var client: OkHttpClient = OkHttpClient
                .Builder()
                .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(MyInterceptor(application))
                .build()


        vnAdapter = Retrofit
                .Builder()
                .baseUrl(ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
    }

}