package pl.mbui.okeapp.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import okhttp3.internal.concurrent.Task
import pl.mbui.okeapp.R
import pl.mbui.okeapp.domain.model.ShowModel
import pl.mbui.okeapp.ui.util.adapter.OverloadAdapter
import pl.mbui.okeapp.ui.util.adapter.OverloadViewHolder

class SearchAdapter : OverloadAdapter<ShowModel>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OverloadViewHolder<ShowModel> = ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.search_adapter_item, parent, false))

    override fun itemComparator(oldItem: ShowModel, newItem: ShowModel): Boolean = oldItem.show.id == newItem.show.id

    private inner class ItemViewHolder(view: View) : OverloadViewHolder<ShowModel>(view) {

        override fun bind(item: ShowModel) {

        }
    }
}