package com.example.githubapi.domain.impl

import com.example.githubapi.Constants
import com.example.githubapi.domain.mapper.RepositoryCardMapper
import com.example.githubapi.domain.model.RepositoryModel
import com.example.githubapi.domain.repository.IApiRemoteRepository
import com.example.githubapi.domain.repository.ISharedPrefRepository
import com.example.githubapi.domain.use_case.ISearchRepositoryUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class SearchRepositoryUseCase @Inject constructor() : ISearchRepositoryUseCase {
    @Inject lateinit var repository: IApiRemoteRepository
    @Inject lateinit var sharedPrefRepository: ISharedPrefRepository
    @Inject lateinit var mapper: RepositoryCardMapper

    // 購読解除管理
    private lateinit var disposable: Disposable

    override fun handle(
        searchWord: String,
        showDialog: () -> Unit,
        onStart: () -> Unit,
        onComplete: (List<RepositoryModel>) -> Unit,
        onError: (String) -> Unit
    ) {
        // 検索ワード検索
        if (searchWord.isBlank()) {
            onError("input word is Empty")
            return
        }

        // accessToken チェック
        val token: String =
            sharedPrefRepository.getStringData(Constants.TOKEN_KEY)
        if (token.isEmpty()) {
            showDialog()
            return
        }

        // APIリクエスト
        disposable = repository.searchRepositoryApi(token, searchWord)
           .map { apiResult ->
               mapper.execute(apiResult)
           }
            .doOnSubscribe{ onStart() }
            .subscribe (
                // onNext
                { response ->
                    if (response.isEmpty()){
                        onError("NoResult")
                        disposable.dispose()
                        return@subscribe
                    }

                    onComplete(response)
                    disposable.dispose()
                },
                // onError
                {
                    onError("error")
                    disposable.dispose()
                }
            )
    }
}