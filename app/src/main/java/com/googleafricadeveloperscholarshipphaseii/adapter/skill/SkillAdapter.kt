package com.googleafricadeveloperscholarshipphaseii.adapter.skill

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.googleafricadeveloperscholarshipphaseii.R
import com.googleafricadeveloperscholarshipphaseii.databinding.ItemSkillIqBinding
import com.googleafricadeveloperscholarshipphaseii.model.skills.response.ResponseSkills

class SkillAdapter : RecyclerView.Adapter<SkillAdapter.LearningAdapterViewHolder>() {
    private var responseSkillList = listOf<ResponseSkills?>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LearningAdapterViewHolder {
        val skillItemBinding: ItemSkillIqBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_skill_iq,
            parent,
            false
        )
        return LearningAdapterViewHolder(skillItemBinding)
    }

    override fun getItemCount(): Int {
        return responseSkillList.size
    }

    override fun onBindViewHolder(holder: LearningAdapterViewHolder, position: Int) {
        val responseSkills: ResponseSkills = responseSkillList.get(position)!!
        holder.bindData(responseSkills)
    }

    fun setResponseSkillList(responseSkillList: List<ResponseSkills>?) {
        responseSkillList!!.sortedByDescending { it.score }
        this.responseSkillList = responseSkillList!!
        notifyDataSetChanged()
    }

    inner class LearningAdapterViewHolder(private var skillItemBinding: ItemSkillIqBinding) :
        RecyclerView.ViewHolder(skillItemBinding.root) {
        fun bindData(item: ResponseSkills) {
            skillItemBinding.skillIQ = item
            skillItemBinding.executePendingBindings()
        }
    }
}