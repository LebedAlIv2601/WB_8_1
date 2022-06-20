package com.example.wb_8_1.presentation.main

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import android.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wb_8_1.R
import com.example.wb_8_1.utils.Resource
import com.example.wb_8_1.appComponent
import com.example.wb_8_1.databinding.FragmentMainBinding
import com.example.wb_8_1.domain.model.DotaHeroModelDomain
import com.example.wb_8_1.presentation.about.AboutFragment
import com.example.wb_8_1.presentation.details.DetailsFragment
import javax.inject.Inject


class MainFragment : Fragment() {

    private var binding: FragmentMainBinding? = null

    private var adapter: MainAdapter? = null

    private val vm: MainViewModel by viewModels {
        viewModelFactory
    }

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMainBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        setupListeners()

        setupObservers()
    }

    private fun setupListeners() {

        binding?.mainToolbar?.setOnMenuItemClickListener {

            if(it.itemId == R.id.main_menu_about_item) {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, AboutFragment())
                    .addToBackStack(null)
                    .commit()
                true
            } else {
                false
            }

        }

        binding?.mainDotaRefreshLayout?.setOnRefreshListener {
            vm.getDotaHeroes()
        }

        binding?.mainTryAgainButton?.setOnClickListener {
            binding?.mainTryAgainButton?.visibility = View.GONE
            vm.getDotaHeroes()
        }
    }

    private fun setupObservers() {
        vm.dotaHeroesList.observe(viewLifecycleOwner, Observer { resource ->
            when (resource) {
                is Resource.Success -> {
                    if (resource.data != null) {
                        adapter?.submitList(resource.data)
                        binding?.mainProgressBar?.visibility = View.GONE
                        binding?.mainDotaRefreshLayout?.isRefreshing = false
                    }
                }
                is Resource.Error -> {
                    Toast.makeText(context, resource.message, Toast.LENGTH_SHORT).show()
                    binding?.mainProgressBar?.visibility = View.GONE
                    binding?.mainTryAgainButton?.visibility = View.VISIBLE
                    binding?.mainDotaRefreshLayout?.isRefreshing = false
                }
                is Resource.Loading -> {
                    binding?.mainProgressBar?.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun navigateToDetails(hero: DotaHeroModelDomain) {

        val detailsFragment = DetailsFragment()

        val heroInfoBundle = Bundle()
        heroInfoBundle.putSerializable("hero", hero)

        detailsFragment.arguments = heroInfoBundle

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, detailsFragment)
            .addToBackStack(null)
            .commit()
    }


    private fun setupRecyclerView() {
        adapter = MainAdapter { navigateToDetails(it) }
        binding?.apply {
            mainRecyclerView.adapter = adapter
            mainRecyclerView.layoutManager = LinearLayoutManager(context)
            mainRecyclerView.itemAnimator = null
            val dividerItemDecoration = DividerItemDecoration(
                mainRecyclerView.context,
                DividerItemDecoration.VERTICAL
            )
            ContextCompat.getDrawable(requireContext(), R.drawable.divider_item)?.let {
                dividerItemDecoration.setDrawable(
                    it
                )
            }
            mainRecyclerView.addItemDecoration(
                dividerItemDecoration
            )
        }
    }

    override fun onDestroyView() {
        binding = null
        adapter = null
        super.onDestroyView()
    }

}