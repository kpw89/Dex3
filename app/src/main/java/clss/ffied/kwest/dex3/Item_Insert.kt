package clss.ffied.kwest.dex3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.room.Room
import clss.ffied.kwest.dex3.entities.Category
import clss.ffied.kwest.dex3.entities.Databasee
import clss.ffied.kwest.dex3.entities.Item
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Item_Insert : Cat_Insert()  {

    var cat_id=0L
    var item_id =0L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item__insert2)

        val et_cat_title = findViewById<EditText>(R.id.et_item_title_insert)
        val btn_insert_cat = findViewById<Button>(R.id.btn_item_ok)

        var title = et_cat_title
        cat_id = intent.getLongExtra("catid",0)
        item_id = intent.getLongExtra("itemid",0)
        var editmode = intent.getBooleanExtra("editmode",false)


        val db = Room.databaseBuilder(
            applicationContext,
            Databasee::class.java, "item_database"
        ).build()

        if (editmode){
            CoroutineScope(Dispatchers.IO).launch {
                var edittext = editCategory(db,db.itemDao().loadSingle(item_id).title)
                this@Item_Insert.runOnUiThread(java.lang.Runnable {
                    et_cat_title.setText(edittext.toString())
                })
            }
        }

        btn_insert_cat.setOnClickListener {

            if (editmode){
                CoroutineScope(Dispatchers.IO).launch {
                    var title1 = et_cat_title.text.toString()   ///doesnt get editext but something weird like full description of widget

                    this@Item_Insert.runOnUiThread(java.lang.Runnable {
                        Toast.makeText(applicationContext,title1, Toast.LENGTH_SHORT).show()
                    })
                    db.itemDao().updateItem(updateItem(db,item_id,title1.toString()))

                    val intent = Intent(applicationContext, Itemitemitem::class.java)
                    intent.putExtra("catid",cat_id)
                    startActivity(intent)
                }
            }else
            {
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
    }


    suspend fun addItemToDb(db : Databasee, item: Item){
        CoroutineScope(Dispatchers.IO).launch {
           // db.catDao().addCategory(category)
            db.itemDao().addItem(item)
        }
    }

    suspend fun updateItem( db: Databasee ,id:Long, title: String): Item{
        var item = db.itemDao().loadSingle(id)
        item.title = title
        return item
    }
}