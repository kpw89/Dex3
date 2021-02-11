package clss.ffied.kwest.dex3.entities

import androidx.room.Embedded
import androidx.room.Relation

data class Items_with_Ingredients (

        @Embedded val item: Item,
        @Relation(
                parentColumn = "id_item",
                entityColumn = "id_item"
        )
        val ingredient: List<Ingredient>
)