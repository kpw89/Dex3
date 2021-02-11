package clss.ffied.kwest.dex3.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ingredient_table")
data class Ingredient (
        @PrimaryKey(autoGenerate = true)
        val id_ingredient : Long,
        val id_item : Long,
        val title : String,
)