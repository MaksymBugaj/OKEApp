package pl.mbui.okeapp.domain.usecase

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import pl.mbui.okeapp.domain.repository.SearchRepository
import javax.inject.Inject

class SearchVideosUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {

    fun searchVideos(query: String) = searchRepository.searchVideos(query).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}