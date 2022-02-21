package pl.mbui.okeapp.domain.repository

import pl.mbui.okeapp.domain.service.SearchService
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val searchService: SearchService
) {

    fun searchVideos(query: String) = searchService.getVideos(query)

}