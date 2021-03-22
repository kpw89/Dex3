package clss.ffied.kwest.dex3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.room.Room
import clss.ffied.kwest.dex3.entities.Databasee
import clss.ffied.kwest.dex3.entities.Ingredient
import clss.ffied.kwest.dex3.entities.Item
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Ing_Insert : Cat_Insert() {

    var item_id=0L
    var cat_id=0L
    var ing_id=0L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ing__insert)


        val et_cat_title = findViewById<EditText>(R.id.et_ingredient_title_insert)
        val btn_insert_cat = findViewById<Button>(R.id.btn_ingredient_ok)

        var title = et_cat_title
        item_id = intent.getLongExtra("itemid",0)
        cat_id = intent.getLongExtra("catid",0)
        ing_id = intent.getLongExtra("ingid",0)
        var editmode = intent.getBooleanExtra("editmode",false)

        val db = Room.databaseBuilder(
                applicationContext,
                Databasee::class.java, "item_database"
        ).build()

        if (editmode){
            CoroutineScope(Dispatchers.IO).launch {
                var edittext = editCategory(db,db.ingredientDao().loadSingle(ing_id).title)
                this@Ing_Insert.runOnUiThread(java.lang.Runnable {
                    et_cat_title.setText(edittext.toString())
                })
            }
        }

        btn_insert_cat.setOnClickListener {
            if (editmode){
                CoroutineScope(Dispatchers.IO).launch {
                    var title1 = et_cat_title.text.toString()   ///doesnt get editext but something weird like full description of widget

                    this@Ing_Insert.runOnUiThread(java.lang.Runnable {
                        Toast.makeText(applicationContext,title1, Toast.LENGTH_SHORT).show()
                    })
                    db.ingredientDao().updateIngredient(updateIng(db,ing_id,title1.toString()))

                    val intent = Intent(applicationContext, Inginging::class.java)
                    intent.putExtra("itemid",item_id)
                    intent.putExtra("catid",cat_id)
                    startActivity(intent)
                }
            }
            else{
                CoroutineScope(Dispatchers.IO).launch {
                    var title = et_cat_title
                    val ingredient = Ingredient(0, item_id,title.text.toString())
                    addIngredientToDb(db,ingredient)
                    val intent = Intent(applicationContext, Inginging::class.java)
                    intent.putExtra("itemid",item_id)
                    intent.putExtra("catid",cat_id)
                    startActivity(intent)
                }
            }


        }
    }

    suspend fun addIngredientToDb(db : Databasee, ingredient: Ingredient){
        CoroutineScope(Dispatchers.IO).launch {
            db.ingredientDao().addIngredient(ingredient)
        }
    }
    suspend fun updateIng( db: Databasee ,id:Long, title: String): Ingredient{
        var ing = db.ingredientDao().loadSingle(id)
        ing.title = title
        return ing
    }
}