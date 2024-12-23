package com.vivek.gomovies.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.vivek.gomovies.R
import com.vivek.gomovies.databinding.FragmentPeopleBinding
import com.vivek.gomovies.ui.details.DetailsFragment
import com.vivek.gomovies.ui.main.adaptor.CustomLoadStateAdapter
import com.vivek.gomovies.ui.main.adaptor.PeopleAdapter
import com.vivek.gomovies.ui.viewModel.PeopleViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PeopleFragment : Fragment() {

    private var _binding: FragmentPeopleBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PeopleViewModel by viewModels()
    private lateinit var peopleAdapter: PeopleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPeopleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        peopleAdapter = PeopleAdapter { person ->
            val bundle = Bundle().apply {
                putString("person_name", "Dummy")
            }
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, DetailsFragment::class.java, bundle)
                .addToBackStack(null)
                .commit()
        }

        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = peopleAdapter.withLoadStateFooter(
                footer = CustomLoadStateAdapter { peopleAdapter.retry() }
            )
        }

        peopleAdapter.addLoadStateListener { loadState ->
            binding.progressBar.isVisible = loadState.refresh is LoadState.Loading
            binding.errorText.isVisible = loadState.refresh is LoadState.Error
        }

        binding.retryButton.setOnClickListener {
            peopleAdapter.retry()
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launchWhenStarted {
            viewModel.popularPeopleFlow.collect { pagingData ->
                peopleAdapter.submitData(pagingData)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
