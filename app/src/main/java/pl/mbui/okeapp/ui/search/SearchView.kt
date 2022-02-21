package pl.mbui.okeapp.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.search_view.*
import pl.mbui.okeapp.R
import pl.mbui.okeapp.ui.util.reactive.ReactiveFragment

@AndroidEntryPoint
class SearchView : ReactiveFragment() {

    private val searchViewModel: SearchViewModel by viewModels()
    private val searchAdapter: SearchAdapter by lazy { SearchAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sv_recycler.adapter = searchAdapter

        disposable.add(searchViewModel.videos.subscribe { searchAdapter.update(it) })

        sv_search.addTextChangedListener {
            searchViewModel.searchVideos(it.toString())
        }
    }
}