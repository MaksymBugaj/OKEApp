package pl.mbui.okeapp.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.search_view.*
import pl.mbui.okeapp.R
import pl.mbui.okeapp.ui.util.reactive.ReactiveFragment
import java.util.concurrent.TimeUnit

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
        sv_textInputLayout.setEndIconOnClickListener {
            searchViewModel.searchVideos(sv_search.text.toString())
        }

        disposable.addAll(
            searchViewModel.videos.subscribe { searchAdapter.update(it) },
            searchViewModel.showLoadingAnimation.subscribe { sv_progress.visibility = if(it) View.VISIBLE else View.GONE },
            searchViewModel.networkError.throttleFirst(750, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe { showDialog() }
        )

        sv_search.addTextChangedListener {
            searchViewModel.searchVideos(it.toString())
        }
    }

    private fun showDialog() {
        context?.let { ctx ->
            pl.mbui.okeapp.ui.util.showDialog(ctx, R.string.no_internet)
        }
    }
}