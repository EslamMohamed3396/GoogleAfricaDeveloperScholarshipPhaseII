package com.googleafricadeveloperscholarshipphaseii.adapter.viewPager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import java.util.*

class FragmentTapAdapter(
    manager: FragmentManager?,
    behavior: Int
) :
    FragmentPagerAdapter(manager!!, behavior) {
    private val mFragmentList: MutableList<Fragment> =
        ArrayList()
    private val mFragmentTitleList: MutableList<String> =
        ArrayList()

    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }

    fun addFrag(fragment: Fragment, title: String) {
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
        notifyDataSetChanged()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mFragmentTitleList[position]
    }
}
