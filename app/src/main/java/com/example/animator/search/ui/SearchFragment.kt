package com.example.animator.search.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animator_domain.common.Results
import com.example.animator_domain.models.poster.AnimePosterEntity
import com.example.animator_domain.usecases.GetAnimePostersFromSearchUseCase
import com.example.animator.R
import com.example.animator.app.App
import com.example.animator.databinding.FragmentSearchBinding
import com.example.animator.details.ui.DetailsFragment
import com.example.animator.search.adapters.PostersAdapter
import com.example.animator.utils.AnimatorUtils
import com.example.animator.utils.BannerUtils
import com.example.animator.utils.RxSearchObservable
import javax.inject.Inject


class SearchFragment : Fragment(), SearchContract.View<List<AnimePosterEntity>> {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy {
        PostersAdapter { posterId ->
            openDetailsPage(posterId = posterId)
        }
    }

    @Inject
    lateinit var getAnimePostersFromSearchUseCase: GetAnimePostersFromSearchUseCase

    private lateinit var presenter: SearchPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().applicationContext as App).appComponent.inject(this)

        presenter = SearchPresenter(
            getAnimePostersFromSearchUseCase = getAnimePostersFromSearchUseCase,
            view = this
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentSearchBinding.inflate(layoutInflater)

        binding.svFragmentSearchSearch.setOnClickListener {
            binding.svFragmentSearchSearch.onActionViewExpanded()
        }

        binding.svFragmentSearchSearch.setOnCloseListener {
            binding.svFragmentSearchSearch.onActionViewExpanded()
            false
        }

        if (savedInstanceState != null) {
            binding.gifFragmentSearchLogo.alpha = savedInstanceState.getFloat(GIF_KEY)
            binding.tvFragmentSearchTitle.alpha = savedInstanceState.getFloat(TITLE_KEY)
            binding.tvFragmentSearchTitleDescription.alpha =
                savedInstanceState.getFloat(DESCRIPTION_KEY)
        }

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        onSearch()
    }

    private fun openDetailsPage(posterId: Int) {
        if (resources.getBoolean(R.bool.isTablet)) {
            parentFragmentManager.beginTransaction()
                .add(
                    R.id.tabletContainer, DetailsFragment.newInstance(posterId = posterId),
                    DetailsFragment.TAG_FRAGMENT
                )
                .commit()
        } else {
            findNavController().navigate(
                SearchFragmentDirections.actionSearchFragmentToDetailsFragment(
                    id = posterId
                )
            )
        }
    }

    @SuppressLint("CheckResult")
    private fun onSearch() {
        RxSearchObservable.fromView(binding.svFragmentSearchSearch)
            .subscribe {
                presenter.getAnimePostersFromSearch(it)
                if (it == "") {
                    with(binding) {
                        AnimatorUtils.toStartView(
                            requireContext(),
                            gifFragmentSearchLogo,
                            tvFragmentSearchTitle,
                            tvFragmentSearchTitleDescription
                        )
                    }
                    clearFragmentStack()
                } else {
                    with(binding) {
                        AnimatorUtils.toCloseView(
                            requireContext(),
                            gifFragmentSearchLogo,
                            tvFragmentSearchTitle,
                            tvFragmentSearchTitleDescription
                        )
                    }
                }
            }
    }

    private fun clearFragmentStack() {
        val fragment: Fragment? =
            parentFragmentManager.findFragmentByTag(DetailsFragment.TAG_FRAGMENT)
        if (fragment != null) {
            parentFragmentManager.beginTransaction().remove(fragment).commit()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (_binding != null) {
            outState.putFloat(GIF_KEY, binding.gifFragmentSearchLogo.alpha)
            outState.putFloat(TITLE_KEY, binding.tvFragmentSearchTitle.alpha)
            outState.putFloat(DESCRIPTION_KEY, binding.tvFragmentSearchTitleDescription.alpha)
        }
    }


    private fun setAdapter() = with(binding) {
        rvFragmentSearchList.adapter = this@SearchFragment.adapter
        rvFragmentSearchList.layoutManager = LinearLayoutManager(context)
    }


    override fun updateViewData(result: Results<List<AnimePosterEntity>>) {

        when (result) {
            is Results.Success -> {
                adapter.submitList(result.data)
            }
            is Results.Error -> {
                BannerUtils.showToast(
                    getString(R.string.an_error_has_occurred, result.exception),
                    requireContext()
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.clearDisposable()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val GIF_KEY = "GIF KEY"
        private const val TITLE_KEY = "TITLE KEY"
        private const val DESCRIPTION_KEY = "DESCRIPTION KEY"
    }
}
