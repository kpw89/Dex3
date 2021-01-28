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
import clss.ffied.kwest.dex3.entities.Item

class New_ItemAdapter(private val itemlist: List<Item>, private val listener: New_ItemAdapter.onItemClickListener): ListAdapter<Item, New_ItemAdapter.ItemViewHolder>(
    New_ItemAdapter.ItemComparator()
)

{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        //  return CategoryViewHolder.create(parent)
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.category_item_layout,
            parent, false)
        return ItemViewHolder(itemView)
    }
    //add this shit for sum reasn
    override fun submitList(list: List<Item>?) {
        super.submitList(list?.let { ArrayList(it) })
    }



    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {


        private val catItemView: TextView = itemView.findViewById(R.id.txt_title)

        init{
            itemView.setOnClickListener(this)
        }

        fun bind(text: String?) {
            catItemView.text = text
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

    class ItemComparator : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.title == newItem.title
        }
    }

    override fun getItemCount() = itemlist.size
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val currentItem = itemlist[position]
        val id_from_category = itemlist[position].id_item
        holder.bind(currentItem.title)

    }


}