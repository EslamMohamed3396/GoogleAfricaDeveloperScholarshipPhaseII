package com.googleafricadeveloperscholarshipphaseii.views.activity.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.googleafricadeveloperscholarshipphaseii.R
import com.googleafricadeveloperscholarshipphaseii.adapter.viewPager.FragmentTapAdapter
import com.googleafricadeveloperscholarshipphaseii.databinding.ActivityHomeBinding
import com.googleafricadeveloperscholarshipphaseii.views.activity.submit.SubmitActivity
import com.googleafricadeveloperscholarshipphaseii.views.fragment.learningLeaders.LeaderLearningFragment
import com.googleafricadeveloperscholarshipphaseii.views.fragment.skillsIQ.SkillLeadersFragment

class HomeActivity : AppCompatActivity() {
    private lateinit var homeBinding: ActivityHomeBinding
    private lateinit var fragmentTapAdapter: FragmentTapAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBinding()
        initAdapter()
        setViewPager()
        tapLayout()
        click()
    }

    //region init databinding
    private fun initDataBinding() {
        homeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        homeBinding.lifecycleOwner = this
    }

    //endregion

    //region init tapLayout

    private fun tapLayout() {
        homeBinding.tabLayout.setupWithViewPager(homeBinding.viewPager)
    }

    //endregion

    //region init click

    private fun click() {
        homeBinding.btnSubmit.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    SubmitActivity::class.java
                )
            )
        }
    }

    //endregion

    //region init view Pager & Adapter
    private fun initAdapter() {
        fragmentTapAdapter = FragmentTapAdapter(supportFragmentManager, 1)
    }

    private fun skillFragment() {
        fragmentTapAdapter.addFrag(
            SkillLeadersFragment(),
            resources.getString(R.string.skill_iq_leaders)
        )

    }

    private fun learnFragment() {
        fragmentTapAdapter.addFrag(
            LeaderLearningFragment(),
            resources.getString(R.string.learning_leader)
        )

    }

    private fun setViewPager() {
        homeBinding.viewPager.adapter = fragmentTapAdapter
        learnFragment()
        skillFragment()
    }
    //endregion
}