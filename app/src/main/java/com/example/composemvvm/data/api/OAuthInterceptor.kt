package com.example.composemvvm.data.api

import com.example.composemvvm.common.PreferenceDataStoreConstants.ACCESS_TOKEN_KEY
import com.example.composemvvm.common.PreferenceDataStoreHelper
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OAuthInterceptor @Inject constructor(private val dataStore: PreferenceDataStoreHelper) :
    Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return runBlocking {
            val token = getTokenAsync().await()
            val request = chain.request()
                .newBuilder()
                .header("Authorization", "Bearer $token")
                .method(chain.request().method, chain.request().body)
                .build()
            chain.proceed(request)
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private suspend fun getTokenAsync(): Deferred<String> {
        return GlobalScope.async {
            val tokenFlow = dataStore.getPreference(ACCESS_TOKEN_KEY, "")
            tokenFlow.first()
        }
    }
}