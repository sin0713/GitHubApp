package com.example.githubapi.ui.view.token

import androidx.lifecycle.ViewModel
import com.example.githubapi.data.data_source.GitHubClient
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class TokenViewModel : ViewModel() {
    val logic = TokenScreenLogic()

    private var disposable: Disposable? = null

    private val _uiState: MutableStateFlow<TokenUiState> = MutableStateFlow(TokenUiState())
    val uiState: StateFlow<TokenUiState> = _uiState

    private lateinit var accessTokenParam: AccessTokenRequestParam

    init {
        getVerificationInfo()
    }

    private fun getVerificationInfo() {
        disposable = GitHubClient.getVerificationInfo()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                { response ->
                    _uiState.update {
                        it.copy(
                            userCode = response.userCode,
                            authenticationUrl = response.verificationUri
                        )
                    }

                    // todo データの永続化必須
                    val interval = (response.interval + 1).toLong()
                    accessTokenParam = AccessTokenRequestParam(
                        deviceCode = response.deviceCode,
                        interval = interval,
                        expireIn = response.expiresTime.toLong()
                    )

                    disposable?.dispose()
                },
                { error ->
                    disposable?.dispose()
                }
            )
    }

    fun getAccessToken() {
        disposable = GitHubClient.getAccessToken(
            deviceCode = accessTokenParam.deviceCode,
            interval = accessTokenParam.interval,
            timeOut =  accessTokenParam.expireIn
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
            }
    }

    override fun onCleared() {
        super.onCleared()

        disposable = null
    }
}