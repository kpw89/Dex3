package clss.ffied.kwest.dex3.Adapeter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import clss.ffied.kwest.dex3.R
import clss.ffied.kwest.dex3.entities.Ingredient
import clss.ffied.kwest.dex3.entities.Item

class New_IngredientAdapter(private val ingredientlist: List<Ingredient>, private val listener: New_IngredientAdapter.onItemClickListener): ListAdapter<Ingredient, New_IngredientAdapter.IngredientViewHolder>(
        New_IngredientAdapter.ItemComparator()
)
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        //  return CategoryViewHolder.create(parent)
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.category_item_layout,
            parent, false)
        return IngredientViewHolder(itemView)
    }

    override fun submitList(list: List<Ingredient>?) {
        super.submitList(list?.let { ArrayList(it) })
    }

    inner class IngredientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val itemIngredientView: TextView = itemView.findViewById(R.id.txt_title) //?????? txt_title
        init{
            itemView.setOnClickListener(this)
        }
        fun bind(text: String?) {
            itemIngredientView.text = text
        }
        override fun onClick(p0: View?) {
            if(adapterPosition!= RecyclerView.NO_POSITION){
                listener.onItemClick(adapterPosition)
            }
        }
    }

    interface onItemClickListener{
        fun onItemClick(position: Int){
        }
    }

    class ItemComparator : DiffUtil.ItemCallback<Ingredient>() {


        override fun areItemsTheSame(oldItem: Ingredient, newItem: Ingredient): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Ingredient, newItem: Ingredient): Boolean {
            return oldItem.title == newItem.title
        }
    }

    override fun getItemCount() = ingredientlist.size
    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {

        val currentItem = ingredientlist[position]
        val id_from_category = ingredientlist[position].id_ingredient   /////??????
        holder.bind(currentItem.title)

    }

}