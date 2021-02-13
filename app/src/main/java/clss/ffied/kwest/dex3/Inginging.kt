package clss.ffied.kwest.dex3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import clss.ffied.kwest.dex3.Adapeter.New_IngredientAdapter
import clss.ffied.kwest.dex3.Adapeter.New_ItemAdapter
import clss.ffied.kwest.dex3.entities.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Inginging : AppCompatActivity(), New_IngredientAdapter.onItemClickListener {

    private var db: Databasee? = null
    private var catDao: CategoryDao? = null
    private var itemDao : ItemDao? = null
    private var ingredientDao : IngredientDao? = null
    var testarray = mutableListOf<Ingredient>()
    var adapter : New_IngredientAdapter? = New_IngredientAdapter(testarray,this)
    var item_id =0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inginging)

        //position is not id
        //kind_entities.get(position).getId()
        item_id = intent.getLongExtra("itemid",0)
        Toast.makeText(this, "item id : "+ item_id.toString(), Toast.LENGTH_SHORT).show()

        val recyclerView = findViewById<RecyclerView>(R.id.recview_ingredient)
        val btn_newIngredient : Button = findViewById(R.id.btn_new_ingredient)

        val db = Room.databaseBuilder(
                applicationContext,
                Databasee::class.java, "item_database"
        ).build()

        btn_newIngredient.setOnClickListener() {
            CoroutineScope(Dispatchers.IO).launch {
                addIngredient(db)
            }
        }

        GlobalScope.launch {
            var data = db.ingredientDao().getIngredientsbyItemId(item_id)
            data?.forEach { testarray.add(it) }
            adapter!!.submitList(testarray.toMutableList())
        }



        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

    }

    suspend fun addIngredient( db: Databasee){

        CoroutineScope(Dispatchers.IO).launch {
            val intent = Intent(applicationContext, Ing_Insert::class.java)
            intent.putExtra("itemid",item_id)
            startActivity(intent)
        }

    }
}