/*
package clss.ffied.kwest.dex3.Adapeter

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import clss.ffied.kwest.dex3.R
import clss.ffied.kwest.dex3.entities.Category

class CategoryAdapter(): RecyclerView.Adapter<CategoryAdapter.CategoryHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CategoryHolder(inflater, parent)
    }



    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
      val category : Category = categories[position]
        holder.bind(category)
    }

    override fun getItemCount() = categories.size

    */
/*class CategoryHolder(val view : View) : RecyclerView.ViewHolder(view){
        
    }*//*


    class CategoryHolder(inflater: LayoutInflater, parent: ViewGroup) :
            RecyclerView.ViewHolder(inflater.inflate(R.layout.category_item_layout, parent, false)) {
        private var mTitleView: TextView? = null


        init {
            mTitleView = itemView.findViewById(R.id.txt_title)

        }

        fun bind(category: Category) {
            mTitleView?.text = category.title
        }

    }

}*/
