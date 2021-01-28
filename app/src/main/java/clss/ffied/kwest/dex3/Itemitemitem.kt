package clss.ffied.kwest.dex3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Itemitemitem : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_itemitemitem)

        //position is not id
        //kind_entities.get(position).getId()
        val cat_id = intent.getLongExtra("catid",0)
        Toast.makeText(this, "cat id : "+ cat_id.toString(),Toast.LENGTH_SHORT).show()


        val btn_newItem : Button = findViewById(R.id.btn_new_item)

        btn_newItem.setOnClickListener() {
            CoroutineScope(Dispatchers.IO).launch {

            }
        }


    }
}