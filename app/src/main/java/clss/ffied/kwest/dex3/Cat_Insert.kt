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
                var edittext = editCategory(db,db.catDao().loadSingle(cat_id).title)
                this@Cat_Insert.runOnUiThread(java.lang.Runnable {
                    et_cat_title.setText(edittext.toString())
                })
            }
        }


        btn_insert_cat.setOnClickListener {

            if (editmode){
                CoroutineScope(Dispatchers.IO).launch {
                    var title1 = et_cat_title.text.toString()   ///doesnt get editext but something weird like full description of widget

                    this@Cat_Insert.runOnUiThread(java.lang.Runnable {
                        Toast.makeText(applicationContext,title1,Toast.LENGTH_SHORT).show()
                    })
                    db.catDao().updateCategory(updateCat(db,cat_id,title1.toString()))

                    val intent = Intent(applicationContext, Catcatcat::class.java)
                    startActivity(intent)
                }
            }
            else{
                CoroutineScope(Dispatchers.IO).launch {
                    var title = et_cat_title
                    val category = Category(0, title.text.toString())
                    addCategoryToDb(db,category)
                    val intent = Intent(applicationContext, Catcatcat::class.java)
                    startActivity(intent)
                }
            }




        }

    }

    suspend fun addCategoryToDb(db : Databasee, category: Category){
        CoroutineScope(Dispatchers.IO).launch {
            db.catDao().addCategory(category)
        }
    }

    suspend fun editCategory(db: Databasee,  title:String):String{
        var tit :String="test function of edit category"
        tit = title
        return tit
    }

    suspend fun updateCat( db: Databasee ,id:Long, title: String): Category{
        var category = db.catDao().loadSingle(id)
        category.title = title
        return category
    }

}