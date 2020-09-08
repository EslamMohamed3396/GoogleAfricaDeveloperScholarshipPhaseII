package com.googleafricadeveloperscholarshipphaseii.views.fragment.skillsIQ

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.googleafricadeveloperscholarshipphaseii.R
import com.googleafricadeveloperscholarshipphaseii.adapter.skill.SkillAdapter
import com.googleafricadeveloperscholarshipphaseii.databinding.FragmentSkillLeadersBinding
import com.googleafricadeveloperscholarshipphaseii.viewModels.skillIq.SkillIQViewModel

class SkillLeadersFragment : Fragment() {

    private lateinit var fragmentSkillLeadersBinding: FragmentSkillLeadersBinding
    private lateinit var skillAdapter: SkillAdapter
    private  val TAG = "SkillLeadersFragment"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return initDataBinding(inflater, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLearningHoursViewModel()
    }

    //region inflate DataBinding
    private fun initDataBinding(inflater: LayoutInflater, container: ViewGroup?): View? {
        fragmentSkillLeadersBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_skill_leaders, container, false)
        return fragmentSkillLeadersBinding.root
    }
    //endregion


    //region setUp Adapter And Recycler
    private fun initRecycler() {
        skillAdapter = SkillAdapter()
        fragmentSkillLeadersBinding.skillAdapter = skillAdapter
    }
    //endregion

    //region init viewModel
    private fun initLearningHoursViewModel() {
        initRecycler()
        val skillIqViewModel = ViewModelProvider(this).get(SkillIQViewModel::class.java)
        skillIqViewModel.responseSkillIQ()
            .observe(viewLifecycleOwner, Observer { responseSkill ->
                skillAdapter.setResponseSkillList(responseSkill)
            })
    }
    //endregion
}