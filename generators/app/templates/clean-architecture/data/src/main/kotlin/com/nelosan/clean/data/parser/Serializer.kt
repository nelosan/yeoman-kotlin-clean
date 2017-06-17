package <%= appPackage %>.data.parser

import com.google.gson.Gson
import javax.inject.Singleton


/**
 * Created by nelo on 8/3/17.
 */
@Singleton
class Serializer {

    private val gson = Gson()

    fun serialize(obj: Any, clazz: Class<*>): String {
        return gson.toJson(obj, clazz)
    }

    fun <T> deserialize(string: String, clazz: Class<T>): T {
        return gson.fromJson(string, clazz)
    }

}