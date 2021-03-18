package clss.ffied.kwest.dex3.entities

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCategory(category: Category)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addItem(item: Item)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateCategory(category: Category)

    @Delete
    suspend fun delete(category: Category)

    /*@Query("SELECT * FROM category_table ORDER BY id_cat ASC")
    fun readAllItems(): LiveData<List<Item>>*/

    @Transaction
    @Query("SELECT * FROM category_table ORDER BY id_cat DESC")
    suspend fun getCat_with_Items(): List<Cat_with_Items>

    @Transaction
    @Query("SELECT * FROM category_table ORDER BY id_cat DESC")
    fun getCategories(): List<Category>

    @Query("SELECT * FROM category_table WHERE id_cat=:id  ")
    fun loadSingle(id: Long): Category

    @Query("SELECT * FROM category_table ORDER BY title ASC")
    fun getAlphabetizedCategories(): Flow<List<Category>>

    @Query("SELECT * FROM category_table WHERE title LIKE :title")
    fun findByTitle(title: String): LiveData<List<Category>>


}