package pl.mbui.okeapp.domain.usecase

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import pl.mbui.okeapp.domain.repository.SearchShowsRepository
import javax.inject.Inject

class SearchShowsUseCase @Inject constructor(
    private val searchShowsRepository: SearchShowsRepository
) {

    fun searchShows(query: String) = searchShowsRepository.searchShows(query).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}