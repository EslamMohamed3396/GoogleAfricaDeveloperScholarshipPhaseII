package com.googleafricadeveloperscholarshipphaseii.Utilits

import android.content.Context
import android.widget.EditText
import android.widget.TextView
import com.googleafricadeveloperscholarshipphaseii.R

import java.util.regex.Pattern

object UtilsValidation {

    fun ValidEmail(mEmail: TextView, context: Context): Boolean {
        val valid_email =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{3,5})$"
        val matcher_email = Pattern.compile(valid_email)
            .matcher(mEmail.text.toString().toLowerCase())
        return if (matcher_email.matches()) {
            true
        } else {
            mEmail.error = context.resources.getString(R.string.invalidEmail)
            mEmail.requestFocus()
            false
        }
    }


    fun name(
        mUserName: TextView,
        context: Context
    ): Boolean {
        val vaildName = "^(?![ .]*$)[\\p{L} .]*$"
        val mName = mUserName.text.toString().toLowerCase()
        val matcher_phone =
            Pattern.compile(vaildName).matcher(mName)
        return if (matcher_phone.matches()) {
            true
        } else {
            mUserName.error = context.resources.getString(R.string.invalidName)
            false
        }
    }


}