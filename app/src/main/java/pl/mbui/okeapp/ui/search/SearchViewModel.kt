package pl.mbui.okeapp.ui.search

import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.BehaviorSubject
import pl.mbui.okeapp.domain.model.ShowModel
import pl.mbui.okeapp.domain.usecase.SearchVideosUseCase
import pl.mbui.okeapp.ui.util.reactive.ReactiveViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchVideosUseCase: SearchVideosUseCase
): ReactiveViewModel() {

    val videos = BehaviorSubject.create<List<ShowModel>>()

    fun searchVideos(query: String){
        if(!validateQuery(query)) return

        searchVideosUseCase.searchVideos(query)
            .subscribeBy ({ Timber.e(it) }, { showModels -> videos.onNext(showModels) })
            .addTo(disposable)
    }

    private fun validateQuery(query: String): Boolean{
        return query.isNotEmpty() && query.length > 3
    }
}