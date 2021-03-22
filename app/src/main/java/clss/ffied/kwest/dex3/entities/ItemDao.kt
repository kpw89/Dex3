package clss.ffied.kwest.dex3.entities

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addItem(item: Item)

    @Delete
    suspend fun delete(item: Item)

    @Query("SELECT * FROM item_table ORDER BY id_item ASC")
    fun readAllItems(): LiveData<List<Item>>

    @Transaction
    @Query("SELECT * FROM item_table ORDER BY id_item DESC")
    fun getItems(): List<Item>

   /* @Transaction
    @Query("SELECT * FROM item_table WHERE id_cat ORDER BY id_item DESC")
    fun getItems(): List<Item>*/

    @Transaction
    @Query("SELECT * FROM item_table Where id_cat = :id_cat ORDER BY title ASC")
    fun getItemsbyCatid(id_cat:Long): List<Item>

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateItem(item: Item)

    @Query("SELECT * FROM item_table WHERE id_item=:id  ")
    fun loadSingle(id: Long): Item
}