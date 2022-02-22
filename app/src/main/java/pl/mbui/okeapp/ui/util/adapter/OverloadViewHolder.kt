package pl.mbui.okeapp.ui.util.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

open class OverloadViewHolder<ItemType>(view: View) : RecyclerView.ViewHolder(view) {

    open fun bind(item: ItemType) {}
}