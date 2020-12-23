package clss.ffied.kwest.dex3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import clss.ffied.kwest.dex3.Adapeter.CategoryAdapter
import clss.ffied.kwest.dex3.entities.Category
import clss.ffied.kwest.dex3.entities.Databasee
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class Catcatcat : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catcatcat)

        val recyclerView = findViewById<RecyclerView>(R.id.recview_category)
        val adapter = CategoryAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val et_cat_title = findViewById<EditText>(R.id.et_title_cat)
        val btn_new_cat = findViewById<Button>(R.id.btn_new_cat)
        val db = Room.databaseBuilder(
                applicationContext,
                Databasee::class.java, "item_database"
        ).build()

        btn_new_cat.setOnClickListener() {
            CoroutineScope(Dispatchers.IO).launch {
                addCategory(et_cat_title.text.toString(),db)
            }

        }

CoroutineScope(Dispatchers.IO).launch {
    val note = db.getDatabase(applicationContext).catDao().getCategories()
}

 ///INIT RECYCLERVIEW !!!!!!!!!


    }


     suspend fun addCategory(et_title: String, db: Databasee){
        var title = et_title
        val category = Category(0,title)
        db.catDao().addCategory(category)
    }




}