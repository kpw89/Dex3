 
package clss.ffied.kwest.dex3

//import clss.ffied.kwest.dex3.Adapeter.CategoryAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.Button
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


class Catcatcat : AppCompatActivity(), New_CategoryAdapter.onItemClickListener {


    /*private val catViewModel: ViewModel_Category by viewModels {
        CatViewModelFactory((application as Cat_Application).repository)
    }*/

    private var db: Databasee? = null
    private var catDao: CategoryDao? = null
    var testarray = mutableListOf<Category>()
    var adapter : New_CategoryAdapter? = New_CategoryAdapter(testarray,this)
    var deleteModee = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catcatcat)


        val btn_new_cat = findViewById<Button>(R.id.btn_new_cat)
        val btn_del_cat = findViewById<Button>(R.id.btn_cat_del)


        var db = Room.databaseBuilder(
                applicationContext,
                Databasee::class.java, "item_database"
        ).build()

        GlobalScope.launch {
            var data = db.catDao().getCategories()
            data?.forEach { testarray.add(it) }
            adapter!!.submitList(testarray.toMutableList())

        }


        val recyclerView = findViewById<RecyclerView>(R.id.recview_category)



        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)






        btn_new_cat.setOnClickListener() {
            CoroutineScope(Dispatchers.IO).launch {
                addCategory( db)
            }
        }

        btn_del_cat.setOnClickListener() {
            CoroutineScope(Dispatchers.IO).launch {
                deleteModee = true
            }
        }


    }


    override fun onItemClick(position: Int) {
       //click marks card , then decide per button to delete or add

//HOLD TO DELETE

        ///HIER WEITER
        if (deleteModee){
            //db?.catDao()?.delete(testarray.get(position))

            CoroutineScope(Dispatchers.IO).launch {
                var db1 = Room.databaseBuilder(
                    applicationContext,
                    Databasee::class.java, "item_database"
                ).build()
                deleteFromCategoryToDb(db1,testarray.get(position))
            }
            Toast.makeText(this,"delete Mode ", Toast.LENGTH_SHORT).show()
            val intent = Intent(applicationContext, Catcatcat::class.java)
            startActivity(intent)
        }
        else{
            val intent = Intent(applicationContext, Itemitemitem::class.java)
            intent.putExtra("catid",testarray.get(position).id_cat)
            startActivity(intent)
        }

    }

     suspend fun addCategory( db: Databasee){
         CoroutineScope(Dispatchers.IO).launch {
             val intent = Intent(applicationContext, Cat_Insert::class.java)
             startActivity(intent)
         }
    }

    suspend fun deleteFromCategoryToDb(db : Databasee, category: Category){
        CoroutineScope(Dispatchers.IO).launch {
            db.catDao().delete(category)
        }
    }

    override fun onBackPressed(){

        }



}