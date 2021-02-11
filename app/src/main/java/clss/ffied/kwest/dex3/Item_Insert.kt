package clss.ffied.kwest.dex3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.room.Room
import clss.ffied.kwest.dex3.entities.Category
import clss.ffied.kwest.dex3.entities.Databasee
import clss.ffied.kwest.dex3.entities.Item
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Item_Insert : Cat_Insert()  {

    var cat_id=0L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item__insert2)

        val et_cat_title = findViewById<EditText>(R.id.et_item_title_insert)
        val btn_insert_cat = findViewById<Button>(R.id.btn_item_ok)

        var title = et_cat_title
        cat_id = intent.getLongExtra("catid",0)

        val db = Room.databaseBuilder(
            applicationContext,
            Databasee::class.java, "item_database"
        ).build()

        btn_insert_cat.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                var title = et_cat_title
                val item = Item(0, cat_id,title.text.toString())
                addItemToDb(db,item)
                val intent = Intent(applicationContext, Itemitemitem::class.java)
                intent.putExtra("catid",cat_id)
                startActivity(intent)
            }

        }
    }


    suspend fun addItemToDb(db : Databasee, item: Item){
        CoroutineScope(Dispatchers.IO).launch {
           // db.catDao().addCategory(category)
            db.itemDao().addItem(item)
        }
    }
}