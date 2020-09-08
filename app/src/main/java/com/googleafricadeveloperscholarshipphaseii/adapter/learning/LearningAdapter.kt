package com.googleafricadeveloperscholarshipphaseii.adapter.learning

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.googleafricadeveloperscholarshipphaseii.R
import com.googleafricadeveloperscholarshipphaseii.databinding.ItemLearningBinding
import com.googleafricadeveloperscholarshipphaseii.model.hours.resonse.ResponseHours

class LearningAdapter : RecyclerView.Adapter<LearningAdapter.LearningAdapterViewHolder>() {
    private var responseHoursList = listOf<ResponseHours?>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LearningAdapterViewHolder {
        val learningBinding: ItemLearningBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_learning,
            parent,
            false
        )
        return LearningAdapterViewHolder(learningBinding)
    }

    override fun getItemCount(): Int {
        return responseHoursList.size
    }

    override fun onBindViewHolder(holder: LearningAdapterViewHolder, position: Int) {
        val responseHours: ResponseHours = responseHoursList.get(position)!!
        holder.bindData(responseHours)
    }

    fun setResponseHoursList(responseHoursList: List<ResponseHours>?) {
        this.responseHoursList = responseHoursList!!
        notifyDataSetChanged()
    }

    inner class LearningAdapterViewHolder(private var learningBinding: ItemLearningBinding) :
        RecyclerView.ViewHolder(learningBinding.root) {
        fun bindData(item: ResponseHours) {
            learningBinding.learningHours = item
            learningBinding.executePendingBindings()
        }
    }
}