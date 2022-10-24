package com.solodilov.ecommerceapp.presentation.productlist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.chip.Chip
import com.solodilov.ecommerceapp.App
import com.solodilov.ecommerceapp.R
import com.solodilov.ecommerceapp.databinding.FragmentProductListBinding
import com.solodilov.ecommerceapp.domain.entity.Category
import javax.inject.Inject

class ProductListFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: ProductListViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[ProductListViewModel::class.java]
    }

    private var _binding: FragmentProductListBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("FragmentProductListBinding is null")

    private var productAdapter: ProductAdapter? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentProductListBinding.inflate(layoutInflater, container, false)

        initViews()
        observeViewModel()

        return binding.root
    }

    private fun initViews() {
        productAdapter = ProductAdapter { product ->
            startProductDetailFragment(productId = product.id)
        }
        binding.productList.adapter = productAdapter

        binding.errorLayout.tryButton.setOnClickListener { viewModel.loadProductList() }

        Category.values().iterator().forEach { addChip(it) }
    }

    private fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner, ::processState)
        viewModel.category.observe(viewLifecycleOwner) { currency ->
            checkChipCurrency(currency)
        }
    }

    private fun checkChipCurrency(category: Category) {
        binding.groupChipsCategory.children.iterator().forEach {
            val chip = it as Chip
            chip.isChecked = chip.text == category.name
        }
    }


    private fun processState(state: ProductListScreenState) {
        when (state) {
            is ProductListScreenState.Loading -> renderLoadingState()
            is ProductListScreenState.Content -> renderContentState(state)
            is ProductListScreenState.Error -> renderErrorState()
        }
    }

    private fun renderLoadingState() {
        toggleState(
            isLoading = true,
            isContent = false,
            isError = false,
        )
    }

    private fun renderContentState(contentState: ProductListScreenState.Content) {
        productAdapter?.submitList(contentState.content)
        toggleState(
            isLoading = false,
            isContent = true,
            isError = false,
        )
    }

    private fun renderErrorState() {
        toggleState(
            isLoading = false,
            isContent = false,
            isError = true,
        )
    }

    private fun addChip(category: Category) {
        val chip = layoutInflater.inflate(
            R.layout.single_chip_layout,
            binding.groupChipsCategory,
            false
        ) as Chip
        chip.apply {
            text = category.name
            setOnClickListener {
                viewModel.setCategory(category)
                viewModel.loadProductList()
            }
            binding.groupChipsCategory.addView(chip)
        }
    }

    private fun toggleState(isLoading: Boolean, isContent: Boolean, isError: Boolean) {
        binding.progressBar.isVisible = isLoading
        binding.contentProductList.isVisible = isContent
        binding.errorLayout.root.isVisible = isError
    }

    private fun startProductDetailFragment(productId: Int) {
        val action = ProductListFragmentDirections
            .actionProductListFragmentToProductDetailFragment(productId)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        productAdapter = null
        _binding = null
        super.onDestroyView()
    }
}