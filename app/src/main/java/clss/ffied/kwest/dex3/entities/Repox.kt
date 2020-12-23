package clss.ffied.kwest.dex3.entities

import androidx.lifecycle.LiveData

class Repox(private val itemDao: ItemDao) {

    val readAllDatax : LiveData<List<Item>> = itemDao.readAllItems()

    suspend fun addItem(item: Item){
        itemDao.addItem(item)
    }
}