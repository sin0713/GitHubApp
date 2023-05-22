package com.example.githubapi.data.repository

import com.example.githubapi.data.dao.VerificationInfoDao
import com.example.githubapi.data.entity.VerificationInfo
import com.example.githubapi.domain.repository.IVerificationInfoTBRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class VerificationInfoTBRepository @Inject constructor() : IVerificationInfoTBRepository{
    @Inject lateinit var dao: VerificationInfoDao

    override fun getVerificationInfo(): Single<VerificationInfo> {
        return Single.create {
            dao.getLatest()
        }

    }

    override fun putVerificationInfo(verificationInfo: VerificationInfo) {
        TODO("Not yet implemented")
    }

    override fun clear() {
        TODO("Not yet implemented")
    }
}