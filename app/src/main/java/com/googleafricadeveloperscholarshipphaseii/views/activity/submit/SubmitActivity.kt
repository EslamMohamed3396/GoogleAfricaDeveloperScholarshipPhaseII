package com.googleafricadeveloperscholarshipphaseii.views.activity.submit

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.googleafricadeveloperscholarshipphaseii.R
import com.googleafricadeveloperscholarshipphaseii.Utilits.Utilits
import com.googleafricadeveloperscholarshipphaseii.Utilits.UtilsValidation
import com.googleafricadeveloperscholarshipphaseii.databinding.ActivitySubmitBinding
import com.googleafricadeveloperscholarshipphaseii.viewModels.uploadProject.UploadProjectViewModel

class SubmitActivity : AppCompatActivity() {
    private lateinit var activitySubmitBinding: ActivitySubmitBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBinding()
        cick()
    }

    //region init databinding
    private fun initDataBinding() {
        activitySubmitBinding = DataBindingUtil.setContentView(this, R.layout.activity_submit)
        activitySubmitBinding.lifecycleOwner = this
    }

    //endregion


    //region check Data

    private fun checkData(): Boolean {
        return UtilsValidation.name(activitySubmitBinding.edFirstName, this)
                && UtilsValidation.name(activitySubmitBinding.edLastName, this)
                && UtilsValidation.name(activitySubmitBinding.edLinkProject, this)
                && UtilsValidation.ValidEmail(activitySubmitBinding.edEmail, this)

    }

    //endregion

    //region init upload ViewModel
    private fun initUploadViewModel() {
        val uploadProjectViewModel =
            ViewModelProvider(this).get(UploadProjectViewModel::class.java)
        uploadProjectViewModel.uploadProjectLiveData(
            activitySubmitBinding.edFirstName.text.toString(),
            activitySubmitBinding.edLastName.text.toString(),
            activitySubmitBinding.edLinkProject.text.toString(),
            activitySubmitBinding.edEmail.text.toString()
        ).observe(this, Observer { response ->
            if (response == 1) {
                Utilits.status(
                    this,
                    R.drawable.successful,
                    resources.getString(R.string.sucess)
                )
            } else {
                Utilits.status(
                    this,
                    R.drawable.error,
                    resources.getString(R.string.not_success)
                )
            }
        })

    }
    //endregion


    //region confirmation dialog
    fun confirmationDilog() {
        val dialog = Dialog(this)
        dialog.window!!.setLayout(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!
            .setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window!!.setGravity(Gravity.CENTER)
        dialog.setContentView(R.layout.dialog_confirmation)
        val confirm: Button = dialog.findViewById(R.id.btn_yes);
        val close: ImageView = dialog.findViewById(R.id.ima_close);
        confirm.setOnClickListener {
            initUploadViewModel()
            dialog.dismiss()
        }
        close.setOnClickListener { dialog.dismiss() }
        dialog.setCancelable(false)
        dialog.show()
    }


    //endregion

    //region click
    private fun cick() {
        activitySubmitBinding.back.setOnClickListener { finish() }
        activitySubmitBinding.submitBtn.setOnClickListener {
            if (checkData()) {
                confirmationDilog()
            }
        }
    }

    //endregion
}
