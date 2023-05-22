package com.example.githubapi.domain.repository

import com.example.githubapi.data.pojo.token.VerificationInfo
import io.reactivex.rxjava3.core.Single

interface IAuthRemoteRepository {
    fun getVerificationInfo(): Single<VerificationInfo>
}