package com.example.otaku.anime

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.otaku.R
import com.example.otaku.databinding.FragmentAnimeBinding
import com.example.otaku.home.ui.HomeFragment
import com.example.otaku.search.ui.SearchFragment
import com.google.android.material.tabs.TabLayoutMediator


class AnimeFragment : Fragment() {

    private var _binding: FragmentAnimeBinding? = null
    private val binding get() = _binding!!


    private val fragmentList by lazy { listOf(HomeFragment(), SearchFragment()) }
    private val titleList by lazy {
        listOf(
            getString(R.string.tabView_home_title),
            getString(R.string.tabView_search_title)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAnimeBinding.inflate(layoutInflater)

        val adapter = ViewPagerAdapter(requireActivity(), fragmentList)
        binding.viewPager2.apply {
            this.adapter = adapter
            isUserInputEnabled = false
        }


        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, pos ->
            tab.text = titleList[pos]
        }.attach()
        return binding.root

    }


}