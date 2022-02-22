package pl.mbui.okeapp.ui.util.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class OverloadAdapter<ItemType> : RecyclerView.Adapter<OverloadViewHolder<ItemType>>() {

    protected var items: List<ItemType>? = null

    fun getItemAtPosition(position: Int) = items?.get(position) ?: throw UninitializedPropertyAccessException("Adapter item not initialized")

    fun getItemAtPositionOrNull(position: Int) = items?.getOrNull(position)

    override fun getItemCount(): Int = items?.size ?: 0

    override fun onBindViewHolder(holder: OverloadViewHolder<ItemType>, position: Int) {
        items?.getOrNull(position)?.let { itemType ->
            holder.bind(item = itemType)
        }
    }

    abstract override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OverloadViewHolder<ItemType>

    abstract fun itemComparator(oldItem: ItemType, newItem: ItemType): Boolean

    open fun update(items: List<ItemType>) {
        if (this.items == null){
            this.items = items
            notifyItemRangeChanged(0, items.size)
        } else {
            val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback(){
                override fun getOldListSize(): Int = itemCount

                override fun getNewListSize(): Int = items.size

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) = this@OverloadAdapter.items?.let { itemComparator(it[oldItemPosition], items[newItemPosition]) } ?: false

                override fun areContentsTheSame(
                    oldItemPosition: Int,
                    newItemPosition: Int
                ): Boolean = this@OverloadAdapter.items?.let { it[oldItemPosition] == items[newItemPosition] } ?: false

            })

            this@OverloadAdapter.items = items
            diff.dispatchUpdatesTo(this)
        }
    }

    fun clear() {
        val itemCount = items?.size ?: 0
        if (itemCount > 0){
            items = emptyList()
            notifyItemRangeRemoved(0, itemCount)
        }
    }
}