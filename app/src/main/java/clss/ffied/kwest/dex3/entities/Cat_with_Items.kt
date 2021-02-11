package clss.ffied.kwest.dex3.entities

import androidx.room.Embedded
import androidx.room.Relation

data class Cat_with_Items (

        @Embedded val category: Category,
        @Relation(
                parentColumn = "id_cat",
                entityColumn = "id_cat"
        )
        val items: List<Item>
)