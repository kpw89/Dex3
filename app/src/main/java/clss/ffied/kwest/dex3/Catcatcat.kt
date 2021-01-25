package clss.ffied.kwest.dex3

//import clss.ffied.kwest.dex3.Adapeter.CategoryAdapter
import android.app.PendingIntent.getActivity
import android.content.Intent
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


class Catcatcat : AppCompatActivity(), New_CategoryAdapter.onItemClickListener {


    /*private val catViewModel: ViewModel_Category by viewModels {
        CatViewModelFactory((application as Cat_Application).repository)
    }*/

    private var db: Databasee? = null
    private var catDao: CategoryDao? = null
    var testarray = mutableListOf<Category>()
    var adapter : New_CategoryAdapter? = New_CategoryAdapter(testarray,this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catcatcat)

        val et_cat_title = findViewById<EditText>(R.id.et_title_cat)
        val btn_new_cat = findViewById<Button>(R.id.btn_new_cat)


        val db = Room.databaseBuilder(
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
                addCategory(et_cat_title.text.toString(), db)
            }

        }



    }


    override fun onItemClick(position: Int) {
        Toast.makeText(this,"pos: "+position.toString(),Toast.LENGTH_SHORT).show()
        Toast.makeText(this,"id: "+testarray.get(position).id_cat.toString(),Toast.LENGTH_SHORT).show()
      //  Toast.makeText(this,"txt: "+testarray.get(position).title,Toast.LENGTH_SHORT).show()



        ///HIER WEITER
       /* val intent = Intent(applicationContext, Itemitemitem::class.java)
        intent.putExtra("position",position)
        startActivity(intent)*/
    }

     suspend fun addCategory(et_title: String, db: Databasee){

         CoroutineScope(Dispatchers.IO).launch {
             val intent = Intent(applicationContext, Cat_Insert::class.java)
             startActivity(intent)
         }

    }




}