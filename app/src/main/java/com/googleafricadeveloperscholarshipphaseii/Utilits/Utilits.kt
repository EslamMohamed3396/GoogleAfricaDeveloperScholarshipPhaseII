package com.googleafricadeveloperscholarshipphaseii.Utilits

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.Window
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.googleafricadeveloperscholarshipphaseii.R
import com.squareup.picasso.Picasso

class Utilits {


    companion object {
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun imageBadge(imageView: ImageView, url: String) {
            Picasso.get().load(url).into(imageView)
        }


        fun status(context: Activity?, imStatus: Int, tvStatus: String) {
            val dialog = Dialog(context!!)
            dialog.window!!.setLayout(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.window!!
                .setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window!!.setGravity(Gravity.CENTER)

            dialog.setContentView(R.layout.dialog_status)
            dialog.setCancelable(true)
            val image =
                dialog.findViewById<ImageView>(R.id.ima_status)
            image.setImageResource(imStatus)
            val status =
                dialog.findViewById<TextView>(R.id.tv_status)
            status.setText(tvStatus)
            dialog.show()
        }

    }
}