package pl.mbui.okeapp.ui.search

import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import pl.mbui.okeapp.domain.model.ShowModel
import pl.mbui.okeapp.domain.usecase.SearchShowsUseCase
import pl.mbui.okeapp.ui.util.reactive.ReactiveViewModel
import timber.log.Timber
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class SearchShowsViewModel @Inject constructor(
    private val searchShowsUseCase: SearchShowsUseCase
) : ReactiveViewModel() {

    val shows = BehaviorSubject.create<List<ShowModel>>()
    val networkError = PublishSubject.create<Unit>()
    val showLoadingAnimation = BehaviorSubject.createDefault(false)

    fun searchVideos(query: String) {
        if (!validateQuery(query)) return

        showLoadingAnimation.onNext(true)
        searchShowsUseCase.searchShows(query)
            .doOnEvent { _, _ -> showLoadingAnimation.onNext(false) }
            .subscribeBy({
                Timber.e(it)
                if (it is UnknownHostException) networkError.onNext(Unit)
            }, { showModels -> shows.onNext(showModels) })
            .addTo(disposable)
    }

    private fun validateQuery(query: String): Boolean {
        return query.isNotEmpty() && query.length >= 3
    }
}