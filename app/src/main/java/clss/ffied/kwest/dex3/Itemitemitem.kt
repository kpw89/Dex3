package clss.ffied.kwest.dex3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Itemitemitem : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_itemitemitem)

        //position is not id
        //kind_entities.get(position).getId()
        val position = intent.getIntExtra("position",0)
    }
}