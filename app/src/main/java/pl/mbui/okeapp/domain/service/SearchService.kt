package pl.mbui.okeapp.domain.service

import io.reactivex.Single
import pl.mbui.okeapp.domain.model.ShowModel
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET("/search/shows")
    fun getVideos(
        @Query("q") query: String
    ): Single<List<ShowModel>>
}