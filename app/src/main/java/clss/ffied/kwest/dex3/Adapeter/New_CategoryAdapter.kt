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

class New_CategoryAdapter: ListAdapter<Category, New_CategoryAdapter.CategoryViewHolder>(CategoryComparator()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.title)
    }

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val catItemView: TextView = itemView.findViewById(R.id.txt_title)

        fun bind(text: String?) {
            catItemView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): CategoryViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                        .inflate(R.layout.category_item_layout, parent, false)
                return CategoryViewHolder(view)
            }
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
}

