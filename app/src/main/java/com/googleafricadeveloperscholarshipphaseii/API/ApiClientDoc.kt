package com.testkotlin.API

import com.testkotlin.Utilits.Constants
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClientDoc {
    private var sBuilder: OkHttpClient.Builder? = null;
    private var apiServices: ApiServices? = null;

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_DOC)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
        apiServices = retrofit.create(ApiServices::class.java);
    }


    private fun okHttp(): OkHttpClient {
        if (sBuilder == null) {
            sBuilder = OkHttpClient.Builder()
                .connectTimeout(300, TimeUnit.SECONDS)
                .readTimeout(300, TimeUnit.SECONDS)
                .addInterceptor(HttpLoggingInterceptor().apply {
                    HttpLoggingInterceptor.Level.BODY;
                });
        }
        return sBuilder!!.build();
    }

    companion object {
        private var apiClient: ApiClientDoc? = null;
        fun getInstance(): ApiClientDoc {
            if (apiClient == null) {
                apiClient = ApiClientDoc();
            }
            return apiClient!!;
        }
    }

    fun <T> request(api: Observable<T>, callBack: NetworkCallBackDoc<T>) {
        api.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<T> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {
                    callBack.onDispose(d)
                }

                override fun onNext(t: T) {
                    callBack.onSuccess(t)
                }

                override fun onError(e: Throwable) {
                    callBack.onFailure(e.message)
                }
            });
    }

    fun uploadDoc(
        email: String,
        firstName: String,
        lastName: String,
        link: String
    ): Observable<ResponseBody> {
        return apiServices!!.uploadDoc(email, firstName, lastName, link)
    }

}


