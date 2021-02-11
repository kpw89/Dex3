package clss.ffied.kwest.dex3.entities

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface IngredientDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addIngredient(ingredient: Ingredient)

   /* @Query("SELECT * FROM ingredient_table ORDER BY id_ingredient ASC")
    fun readAllIngredients(): LiveData<List<Ingredient>>*/

    @Transaction
    @Query("SELECT * FROM ingredient_table ORDER BY id_ingredient DESC")
    fun getIngredients(): List<Ingredient>

    /* @Transaction
     @Query("SELECT * FROM item_table WHERE id_cat ORDER BY id_item DESC")
     fun getItems(): List<Item>*/

    @Transaction
    @Query("SELECT * FROM ingredient_table Where id_ingredient = :id_ingredient ORDER BY title ASC")
    fun getIngredientsbyItemId(id_ingredient:Long): List<Ingredient>
}