package clss.ffied.kwest.dex3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import clss.ffied.kwest.dex3.Adapeter.New_ItemAdapter
import clss.ffied.kwest.dex3.entities.CategoryDao
import clss.ffied.kwest.dex3.entities.Databasee
import clss.ffied.kwest.dex3.entities.Item
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Itemitemitem : AppCompatActivity(), New_ItemAdapter.onItemClickListener {

    private var db: Databasee? = null
    private var catDao: CategoryDao? = null
    var testarray = mutableListOf<Item>()
    var adapter : New_ItemAdapter? = New_ItemAdapter(testarray,this)
    var cat_id =0L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_itemitemitem)

        //position is not id
        //kind_entities.get(position).getId()
        cat_id = intent.getLongExtra("catid",0)
        Toast.makeText(this, "cat id : "+ cat_id.toString(),Toast.LENGTH_SHORT).show()

        val recyclerView = findViewById<RecyclerView>(R.id.recview_item)
        val btn_newItem : Button = findViewById(R.id.btn_new_item)

        val db = Room.databaseBuilder(
            applicationContext,
            Databasee::class.java, "item_database"
        ).build()

        btn_newItem.setOnClickListener() {
            CoroutineScope(Dispatchers.IO).launch {
                addItem(db)
            }
        }






        GlobalScope.launch {
            var data = db.itemDao().getItemsbyCatid(cat_id)
            data?.forEach { testarray.add(it) }
            adapter!!.submitList(testarray.toMutableList())
        }



        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)




    }

    override fun onItemClick(position: Int) {
//HOLD TO DELETE

        ///HIER WEITER
        val intent = Intent(applicationContext, Inginging::class.java)
        intent.putExtra("itemid",testarray.get(position).id_item)
        startActivity(intent)
    }

    suspend fun addItem( db: Databasee){

        CoroutineScope(Dispatchers.IO).launch {
            val intent = Intent(applicationContext, Item_Insert::class.java)
            intent.putExtra("catid",cat_id)
            startActivity(intent)
            //val intent = Intent(applicationContext, Item_Insert::class.java)
           // startActivity(intent)
        }

    }
}