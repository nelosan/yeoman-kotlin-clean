package <%= appPackage %>.domain.check


/**
 * Created by nelo on 16/2/17.
 */
class Preconditions {

    companion object{
        fun checkNotNull(obj: Any?): Any{
            if(obj == null){
                throw NullPointerException()
            }
            return obj
        }
    }
}