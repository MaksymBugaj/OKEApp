package pl.mbui.okeapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.mbui.okeapp.domain.service.SearchShowsService
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    fun provideSearchShowsService(retrofit: Retrofit) = retrofit.create(SearchShowsService::class.java)

}