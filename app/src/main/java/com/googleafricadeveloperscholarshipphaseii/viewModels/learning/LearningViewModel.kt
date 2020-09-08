package com.googleafricadeveloperscholarshipphaseii.viewModels.learning

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.googleafricadeveloperscholarshipphaseii.model.hours.resonse.ResponseHours
import com.testkotlin.API.ApiClient
import com.testkotlin.API.NetworkCallBack
import io.reactivex.disposables.Disposable

class LearningViewModel : ViewModel() {
    private lateinit var hoursMutableLiveData: MutableLiveData<List<ResponseHours>>
    private lateinit var d: Disposable
    private val TAG = "LearningViewModel"
    fun responseHoursLiveData(): LiveData<List<ResponseHours>> {
        hoursMutableLiveData = MutableLiveData()
        getLearningHours()
        return hoursMutableLiveData
    }

    private fun getLearningHours() {
        ApiClient.getInstance().request(ApiClient.getInstance().getResponseHours(),
            object : NetworkCallBack<ResponseHours> {
                override fun onSuccess(response: List<ResponseHours>?) {
                    hoursMutableLiveData.value = response!!
                }

                override fun onFailure(error: String?) {
                    Log.d(TAG, "onFailure: " + error)
                }

                override fun onDispose(disposable: Disposable?) {
                    d = disposable!!
                }


            })
    }

    override fun onCleared() {
        super.onCleared()
        d.dispose()
    }
}