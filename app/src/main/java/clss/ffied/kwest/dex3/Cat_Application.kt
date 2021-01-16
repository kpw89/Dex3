package clss.ffied.kwest.dex3

import android.app.Application
import clss.ffied.kwest.dex3.entities.Databasee
import clss.ffied.kwest.dex3.msc.CatRepository

class Cat_Application: Application() {

    val database by lazy { Databasee.getDatabase(this) }
    val repository by lazy { CatRepository(database.catDao()) }

}

