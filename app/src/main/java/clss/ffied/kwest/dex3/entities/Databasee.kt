package clss.ffied.kwest.dex3.entities

import android.content.Context
import android.os.strictmode.InstanceCountViolation
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlin.coroutines.coroutineContext

@Database(entities = [Item::class, Category::class],version = 1,exportSchema = false)
abstract class Databasee :RoomDatabase() {

    abstract fun itemDao() : ItemDao
    abstract fun catDao()  : CategoryDao

    companion object{
        @Volatile
        private var INSTANCE : clss.ffied.kwest.dex3.entities.Databasee? = null

        fun getDatabase(context: Context): clss.ffied.kwest.dex3.entities.Databasee{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return  tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        Databasee::class.java,
                        "item_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }


}