package clss.ffied.kwest.dex3

//import clss.ffied.kwest.dex3.Adapeter.CategoryAdapter
import android.app.PendingIntent.getActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import clss.ffied.kwest.dex3.Adapeter.New_CategoryAdapter
import clss.ffied.kwest.dex3.entities.Category
import clss.ffied.kwest.dex3.entities.CategoryDao
import clss.ffied.kwest.dex3.entities.Databasee
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class Catcatcat : AppCompatActivity() {


    /*private val catViewModel: ViewModel_Category by viewModels {
        CatViewModelFactory((application as Cat_Application).repository)
    }*/

    private var db: Databasee? = null
    private var catDao: CategoryDao? = null
    var testarray = mutableListOf<Category>()
    var adapter : New_CategoryAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catcatcat)



        val recyclerView = findViewById<RecyclerView>(R.id.recview_category)
        adapter = New_CategoryAdapter()


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
                addCategory(et_cat_title.text.toString(), db)



            }

        }

/*CoroutineScope(Dispatchers.IO).launch {
    val note = db.getDatabase(applicationContext).catDao().getAlphabetizedCategories()
}*/

        GlobalScope.launch {
         var data = db.catDao().getCategories()
            data?.forEach { testarray.add(it) }
            adapter!!.submitList(testarray.toMutableList())

        }

    }


     suspend fun addCategory(et_title: String, db: Databasee){
        var title = et_title
        val category = Category(0, title)
        db.catDao().addCategory(category)
         testarray.add(category)
         adapter!!.inser
         this@Catcatcat.runOnUiThread(java.lang.Runnable {
             adapter!!.notifyDataSetChanged()
             Toast.makeText(this,"thread",Toast.LENGTH_SHORT).show()
         })

        //Toast.makeText(this,testarray.size.toString(),Toast.LENGTH_SHORT).show()
         println(testarray.size.toString() + " size")

    }




}