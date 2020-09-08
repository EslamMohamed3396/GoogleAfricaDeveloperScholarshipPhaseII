package com.googleafricadeveloperscholarshipphaseii.views.fragment.learningLeaders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.googleafricadeveloperscholarshipphaseii.R
import com.googleafricadeveloperscholarshipphaseii.adapter.learning.LearningAdapter
import com.googleafricadeveloperscholarshipphaseii.databinding.FragmentLeaderLearningBinding
import com.googleafricadeveloperscholarshipphaseii.viewModels.learning.LearningViewModel


class LeaderLearningFragment : Fragment() {
    private lateinit var leaderLearningFragment: FragmentLeaderLearningBinding
    private lateinit var learningAdapter: LearningAdapter
    private val TAG = "LeaderLearningFragment"
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
        leaderLearningFragment =
            DataBindingUtil.inflate(inflater, R.layout.fragment_leader_learning, container, false)
        return leaderLearningFragment.root
    }
    //endregion


    //region setUp Adapter And Recycler
    private fun initRecycler() {
        learningAdapter = LearningAdapter()
        leaderLearningFragment.learningAdapter = learningAdapter
    }
    //endregion

    //region init viewModel
    private fun initLearningHoursViewModel() {
        initRecycler()
        val learningViewModel = ViewModelProvider(this).get(LearningViewModel::class.java)
        learningViewModel.responseHoursLiveData()
            .observe(viewLifecycleOwner, Observer { responseHours ->
                learningAdapter.setResponseHoursList(responseHours!!)
            })
    }
    //endregion

}