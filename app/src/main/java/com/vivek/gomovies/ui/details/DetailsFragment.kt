package com.vivek.gomovies.ui.details


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import coil.load
import com.vivek.gomovies.R
import com.vivek.gomovies.databinding.FragmentDetailsBinding
import com.vivek.gomovies.ui.details.adaptor.ImagesAdapter
import com.vivek.gomovies.ui.viewModel.PeopleViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PeopleViewModel by viewModels()

    private var personId: Int = -1
    private lateinit var imagesAdapter: ImagesAdapter

    private var isBioExpanded = false
    private val maxBioLines = 4

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            personId = it.getInt("person_id", -1)
        }
      //  binding.toolbar.setNavigationIcon(R.drawable.arrow_back)

        if (personId != -1) {
            viewModel.fetchPersonImages(personId)
            observePersonDetails(personId)
            observePersonImageList()
            setupImagesRecyclerView()
        }

        binding.readMore.setOnClickListener {
            toggleBioText()
        }

    }

    private fun observePersonImageList() {
        lifecycleScope.launchWhenStarted {
            viewModel.personImages.collect { images ->
                imagesAdapter.submitList(images)
            }
        }
    }

    private fun observePersonDetails(personId: Int) {
        lifecycleScope.launchWhenStarted {
            viewModel.getPersonDetails(personId).collect { personDetails ->
                binding.personName.text = personDetails.name
                binding.personBio.text = personDetails.biography
                binding.blurredBackground.load(personDetails.real_path)
                binding.profileImage.load(personDetails.real_path)

                binding.personBio.maxLines = maxBioLines
                binding.readMore.isVisible = personDetails.biography?.length!! > 200

            }
        }
    }

    private fun setupImagesRecyclerView() {
        imagesAdapter = ImagesAdapter()
        binding.recyclerViewImages.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = imagesAdapter
        }
    }
    private fun toggleBioText() {
        isBioExpanded = !isBioExpanded
        if (isBioExpanded) {
            binding.personBio.maxLines = Int.MAX_VALUE
            binding.readMore.text = "Read Less"
        } else {
            binding.personBio.maxLines = maxBioLines
            binding.readMore.text = "Read More"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

