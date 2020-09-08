package com.googleafricadeveloperscholarshipphaseii.viewModels.skillIq

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.googleafricadeveloperscholarshipphaseii.model.skills.response.ResponseSkills
import com.testkotlin.API.ApiClient
import com.testkotlin.API.NetworkCallBack
import io.reactivex.disposables.Disposable

class SkillIQViewModel : ViewModel() {
    private lateinit var skillIQMutableLiveData: MutableLiveData<List<ResponseSkills>>
    private lateinit var d: Disposable
    private val TAG = "SkillIQViewModel"
    fun responseSkillIQ(): LiveData<List<ResponseSkills>> {
        skillIQMutableLiveData = MutableLiveData()
        getLearningHours()
        return skillIQMutableLiveData
    }

    private fun getLearningHours() {
        ApiClient.getInstance().request(ApiClient.getInstance().getResponseSkills(),
            object : NetworkCallBack<ResponseSkills> {
                override fun onSuccess(response: List<ResponseSkills>?) {
                    skillIQMutableLiveData.value = response!!
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