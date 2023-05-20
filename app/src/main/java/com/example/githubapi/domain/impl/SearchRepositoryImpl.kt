package com.example.githubapi.domain.impl

import android.annotation.SuppressLint
import com.example.githubapi.domain.repository.IRemoteRepository
import com.example.githubapi.domain.use_case.SearchRepositoryUseCase
import com.example.githubapi.ui.HomeUiState
import com.example.githubapi.ui.mapper.RepositoryCardMapper
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor() : SearchRepositoryUseCase {
    @Inject lateinit var repository: IRemoteRepository
    @Inject lateinit var mapper: RepositoryCardMapper

    @SuppressLint("CheckResult")
    override fun handle(
        token: String,
        searchWord: String,
        onComplete: (HomeUiState) -> Unit
    ) {
        if (searchWord.isEmpty()) {
            onComplete(
                HomeUiState(errorMessage = "input is Empty")
            )
            return
        }

         repository.searchRepositoryApi(token, searchWord)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { apiResult ->
                mapper.execute(apiResult)
            }
             .subscribe (
                 // onNext
                 { response ->
                     if (response.isEmpty()){
                         onComplete(
                             HomeUiState(errorMessage = "No Result")
                         )
                         return@subscribe
                     }
                     onComplete(HomeUiState(data = response))

                 },
                 // onError
                 {
                     onComplete(HomeUiState(errorMessage = "error"))
                 }
             )
    }
}