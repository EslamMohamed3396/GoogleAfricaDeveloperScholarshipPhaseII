package com.googleafricadeveloperscholarshipphaseii.viewModels.uploadProject

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.testkotlin.API.ApiClientDoc
import com.testkotlin.API.NetworkCallBackDoc
import io.reactivex.disposables.Disposable
import okhttp3.ResponseBody

class UploadProjectViewModel : ViewModel() {
    private lateinit var uploadProject: MutableLiveData<Int>
    private lateinit var d: Disposable
    private val TAG = "UploadProjectViewModel"
    fun uploadProjectLiveData(
        email: String,
        firstName: String,
        lastName: String,
        link: String
    ): LiveData<Int> {
        uploadProject = MutableLiveData()
        uploadProject(email, firstName, lastName, link)
        return uploadProject
    }

    private fun uploadProject(
        email: String,
        firstName: String,
        lastName: String,
        link: String
    ) {
        ApiClientDoc.getInstance()
            .request(ApiClientDoc.getInstance().uploadDoc(email, firstName, lastName, link),
                object : NetworkCallBackDoc<ResponseBody> {
                    override fun onSuccess(response: ResponseBody?) {
                        uploadProject.postValue(1)
                    }

                    override fun onFailure(error: String?) {
                        uploadProject.postValue(0)
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