package com.example.githubapi.domain.repository

import com.example.githubapi.data.entity.VerificationInfo
import io.reactivex.rxjava3.core.Single


interface IVerificationInfoTBRepository {
    fun getVerificationInfo(): Single<VerificationInfo>
    fun putVerificationInfo(verificationInfo: VerificationInfo)
    fun clear()
}