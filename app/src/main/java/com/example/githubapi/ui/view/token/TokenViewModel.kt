package com.example.githubapi.ui.view.token

import androidx.lifecycle.ViewModel
import com.example.githubapi.Constants
import com.example.githubapi.data.data_source.GitHubClient
import com.example.githubapi.data.repository.SharedPrefRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class TokenViewModel @Inject constructor() : ViewModel() {
    val logic = TokenScreenLogic()

    private var disposable: Disposable? = null

    private val _uiState: MutableStateFlow<TokenUiState> = MutableStateFlow(TokenUiState())
    val uiState: StateFlow<TokenUiState> = _uiState

    private lateinit var accessTokenParam: AccessTokenRequestParam

    @Inject lateinit var repository: SharedPrefRepository

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
            .subscribe { tokenInfo ->
                if (tokenInfo.accessToken.isNotEmpty()) {
                    // 正常系
                    // todo トークン保存処理
                    repository.putString(
                        Constants.TOKEN_KEY,
                        tokenInfo.accessToken
                    )
                    disposable?.dispose()
                    return@subscribe
                }

                if (tokenInfo.error.isEmpty()) {
                    // 購読解除処理
                    // return
                }

                // errorメッセージあり

                // authorization_pending
                // 認証待ち状態
                // 処理なし

                // slow_down
                // 一旦、購読解除
                // 新しく(interval 更新)アクセストークンリクエスト処理を開始する

                // ユーザーキャンセル
                // 検証コード使用不可
                // access_denied

                // デバイスコード有効期間切れ
                // 検証コード使用不可
                // expired_token

                // コーディングミス系(処理しない)
                /// unsupported_grant_type
                /// incorrect_client_credentials
                /// incorrect_device_code
                // device_flow_disabled

            }
    }

    override fun onCleared() {
        super.onCleared()

        disposable = null
    }
}