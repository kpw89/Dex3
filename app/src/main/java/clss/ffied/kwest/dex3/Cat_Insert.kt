package clss.ffied.kwest.dex3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.room.Room
import clss.ffied.kwest.dex3.entities.Category
import clss.ffied.kwest.dex3.entities.CategoryDao
import clss.ffied.kwest.dex3.entities.Databasee
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class Cat_Insert : AppCompatActivity() {

    var db: Databasee? = null
    private var catDao: CategoryDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cat__insert)

        val et_cat_title = findViewById<EditText>(R.id.et_cat_title_insert)
        val btn_insert_cat = findViewById<Button>(R.id.btn_ok)

        var title = et_cat_title
        val category = Category(0, title.toString())

        var cat_id = intent.getLongExtra("catid",0)
        var editmode = intent.getBooleanExtra("editmode",false)
        Toast.makeText(applicationContext,"catid: "+cat_id.toString(),Toast.LENGTH_SHORT).show()

        val db = Room.databaseBuilder(
            applicationContext,
            Databasee::class.java, "item_database"
        ).build()

        if (editmode){
            CoroutineScope(Dispatchers.IO).launch {
                var edittext = editCategory(db,cat_id)
                //Toast.makeText(applicationContext,"editext: "+edittext,Toast.LENGTH_SHORT).show()
                this@Cat_Insert.runOnUiThread(java.lang.Runnable {
                    et_cat_title.setText(edittext)
                })
            }
        }


        btn_insert_cat.setOnClickListener {

                CoroutineScope(Dispatchers.IO).launch {
                    var title = et_cat_title
                    val category = Category(0, title.text.toString())
                    addCategoryToDb(db,category)
                    val intent = Intent(applicationContext, Catcatcat::class.java)
                    startActivity(intent)
                }



        }

    }

    suspend fun addCategoryToDb(db : Databasee, category: Category){
        CoroutineScope(Dispatchers.IO).launch {
            db.catDao().addCategory(category)
        }
    }

    suspend fun editCategory(db: Databasee, id:Long): String{
        var tit :String="test function of edit category"
        CoroutineScope(Dispatchers.IO).launch {
            tit= db.catDao().loadSingle(id).title
        }
        return tit
    }

}