package pl.mbui.okeapp.domain.repository

import pl.mbui.okeapp.domain.service.SearchShowsService
import javax.inject.Inject

class SearchShowsRepository @Inject constructor(
    private val searchShowsService: SearchShowsService
) {

    fun searchShows(query: String) = searchShowsService.getShows(query)

}