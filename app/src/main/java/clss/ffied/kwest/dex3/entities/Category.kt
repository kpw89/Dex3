package clss.ffied.kwest.dex3.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "category_table")
data class Category (

    @PrimaryKey(autoGenerate = true)
    val id_cat : Long,
    val title : String,

)