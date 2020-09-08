package com.testkotlin.API

import com.googleafricadeveloperscholarshipphaseii.model.hours.resonse.ResponseHours
import com.googleafricadeveloperscholarshipphaseii.model.skills.response.ResponseSkills
import com.testkotlin.Utilits.Constants
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {
    private var sBuilder: OkHttpClient.Builder? = null;
    private var apiServices: ApiServices? = null;

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
        apiServices = retrofit.create(ApiServices::class.java);
    }

    private fun okHttp(): OkHttpClient {
        if (sBuilder == null) {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            sBuilder = OkHttpClient.Builder()
                .connectTimeout(300, TimeUnit.SECONDS)
                .readTimeout(300, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
        }
        return sBuilder!!.build();
    }

    companion object {
        private var apiClient: ApiClient? = null;
        fun getInstance(): ApiClient {
            if (apiClient == null) {
                apiClient = ApiClient();
            }
            return apiClient!!;
        }
    }

    fun <T> request(api: Observable<List<T>>, callBack: NetworkCallBack<T>) {
        api.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<T>> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {
                    callBack.onDispose(d)
                }

                override fun onNext(t: List<T>) {
                    callBack.onSuccess(t)
                }

                override fun onError(e: Throwable) {
                    callBack.onFailure(e.message)
                }
            });
    }

    fun getResponseHours(): Observable<List<ResponseHours>> {
        return apiServices!!.getHours();
    }

    fun getResponseSkills(): Observable<List<ResponseSkills>> {
        return apiServices!!.getSkills();
    }
}


