package com.example.githubapi.ui.view

import androidx.lifecycle.ViewModel
import com.example.githubapi.data.data_source.ApiClient
import com.example.githubapi.data.pojo.RepositoryCard
import com.example.githubapi.data.pojo.search.RepositoryInfo
import com.example.githubapi.data.pojo.search.SearchRepositoryInfo
import com.example.githubapi.ui.mapper.RepositoryCardMapper
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeViewModel : ViewModel() {
    private val mapper: RepositoryCardMapper = RepositoryCardMapper()
    private var _cardData: Single<List<RepositoryCard>> =
        ApiClient.search("codelab")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { apiResult ->
                mapper.execute(apiResult)
            }

    val cardData: Single<List<RepositoryCard>>
        get() = _cardData
}