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
import clss.ffied.kwest.dex3.entities.ItemDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val btn_add_item = findViewById<Button>(R.id.btn_add_item)
        val btn_new = findViewById<Button>(R.id.btn_main_new)
        val db = Room.databaseBuilder(
                applicationContext,
                Databasee::class.java, "item_database"
        ).build()

        val itemDao : ItemDao


        btn_new.setOnClickListener {
            CoroutineScope(IO).launch {
                val intent = Intent(applicationContext, Catcatcat::class.java)
                startActivity(intent)
            }

        }

        btn_add_item.setOnClickListener {

        }


    }

    /*suspend fun addUser(et_title: EditText, et_content:EditText ,db :Databasee){
        var title = et_title.text.toString()
        var content = et_content.text.toString()
        val item = Item(0,0L,title)
        db.itemDao().addItem(item)

    }*/

   /* suspend fun addCategory(et_title: EditText, db: Databasee){
        var title = et_title.text.toString()
        val category = Category(0,title)
        db.catDao().addCategory(category)
    }*/
}