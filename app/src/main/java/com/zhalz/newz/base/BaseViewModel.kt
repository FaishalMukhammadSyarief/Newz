package com.zhalz.newz.base

import com.crocodic.core.base.viewmodel.CoreViewModel
import com.zhalz.newz.retrofit.ApiService
import javax.inject.Inject

open class BaseViewModel: CoreViewModel() {

    @Inject
    lateinit var apiService: ApiService

    override fun apiLogout() {}

    override fun apiRenewToken() { }

}