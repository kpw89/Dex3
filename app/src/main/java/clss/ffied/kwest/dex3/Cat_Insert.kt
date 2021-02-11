package clss.ffied.kwest.dex3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
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


        /*db.catDao().addCategory(category)*/

        val db = Room.databaseBuilder(
            applicationContext,
            Databasee::class.java, "item_database"
        ).build()


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

}