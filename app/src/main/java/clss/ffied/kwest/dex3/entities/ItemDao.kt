package clss.ffied.kwest.dex3.entities

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addItem(item: Item)

    @Query("SELECT * FROM item_table ORDER BY id_item ASC")
    fun readAllItems(): LiveData<List<Item>>

    @Transaction
    @Query("SELECT * FROM item_table ORDER BY id_item DESC")
    fun getItems(): List<Item>
}