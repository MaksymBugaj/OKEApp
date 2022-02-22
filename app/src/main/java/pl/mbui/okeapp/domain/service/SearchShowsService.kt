package pl.mbui.okeapp.domain.service

import io.reactivex.Single
import pl.mbui.okeapp.domain.model.ShowModel
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchShowsService {

    @GET("/search/shows")
    fun getShows(
        @Query("q") query: String
    ): Single<List<ShowModel>>
}