package clss.ffied.kwest.dex3.Adapeter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import clss.ffied.kwest.dex3.R
import clss.ffied.kwest.dex3.entities.Category
//
class New_CategoryAdapter(private val catlist: List<Category>, private val listener: onItemClickListener): ListAdapter<Category, New_CategoryAdapter.CategoryViewHolder>(CategoryComparator()
){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
      //  return CategoryViewHolder.create(parent)
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.category_item_layout,
                parent, false)
        return CategoryViewHolder(itemView)
    }
    //add this shit for sum reasn
    override fun submitList(list: List<Category>?) {
        super.submitList(list?.let { ArrayList(it) })
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        //val current = getItem(position)
        //holder.bind(current.title)
        val currentItem = catlist[position]
        val id_from_category = catlist[position].id_cat
        holder.bind(currentItem.title)
        //holder.itemView.
        

    }

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {


        private val catItemView: TextView = itemView.findViewById(R.id.txt_title)

        init{
            itemView.setOnClickListener(this)
        }

        fun bind(text: String?) {
            catItemView.text = text
        }


        override fun onClick(p0: View?) {
            if(adapterPosition!=RecyclerView.NO_POSITION){
                listener.onItemClick(adapterPosition)
            }

        }



    }

    interface onItemClickListener{
        fun onItemClick(position: Int){

        }
    }

    class CategoryComparator : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.title == newItem.title
        }
    }

    override fun getItemCount() = catlist.size
}

