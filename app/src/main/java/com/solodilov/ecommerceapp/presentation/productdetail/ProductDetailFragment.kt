package com.solodilov.ecommerceapp.presentation.productdetail

import android.content.Context
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.solodilov.ecommerceapp.App
import com.solodilov.ecommerceapp.R
import com.solodilov.ecommerceapp.databinding.FragmentProductDetailBinding
import com.solodilov.ecommerceapp.presentation.productlist.ProductAdapter
import javax.inject.Inject

class ProductDetailFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: ProductDetailViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[ProductDetailViewModel::class.java]
    }

    private var _binding: FragmentProductDetailBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("FragmentProductDetailBinding is null")

    private var imageSlideAdapter: ImageSlideAdapter? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentProductDetailBinding.inflate(layoutInflater, container, false)

        initViews()
        observeViewModel()

        return binding.root
    }

    private fun initViews() {
        arguments?.let {
            val args = ProductDetailFragmentArgs.fromBundle(it)
            val productId = args.productId
            viewModel.loadProductDetail(productId)
            binding.errorLayout.tryButton.setOnClickListener { viewModel.loadProductDetail(productId) }
        }
        binding.backButton.setOnClickListener { findNavController().popBackStack() }
        binding.stepper.decreaseCount.setOnClickListener { viewModel.decreaseProductCount() }
        binding.stepper.increaseCount.setOnClickListener { viewModel.increaseProductCount() }
    }

    private fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner, ::processState)
    }

    private fun processState(state: ProductDetailScreenState) {
        when (state) {
            is ProductDetailScreenState.Loading -> renderLoadingState()
            is ProductDetailScreenState.Content -> renderContentState(state)
            is ProductDetailScreenState.Error -> renderErrorState()
        }
    }

    private fun renderLoadingState() {
        toggleState(
            isLoading = true,
            isContent = false,
            isError = false,
        )
    }

    private fun renderContentState(contentState: ProductDetailScreenState.Content) {
        binding.apply {
            productTitle.text = contentState.content.title
            productDescription.text = contentState.content.description
        }
        viewModel.productCount.observe(viewLifecycleOwner) { count ->
            binding.stepper.itemCount.text = count.toString()
            binding.productTotalPrice.text = getString(
                R.string.price_format,
                (count * contentState.content.price)
            )
        }

        setUpViewPager(contentState.content.images)

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

    private fun toggleState(isLoading: Boolean, isContent: Boolean, isError: Boolean) {
        binding.progressBar.isVisible = isLoading
        binding.productDetail.isVisible = isContent
        binding.errorLayout.root.isVisible = isError
    }

    private fun setUpViewPager(images: List<String>) {
        imageSlideAdapter = ImageSlideAdapter(images)
        val slider = binding.slider.imageSlider
        slider.adapter = imageSlideAdapter
        binding.slider.indicator.attachTo(slider)
    }

    override fun onDestroyView() {
        imageSlideAdapter = null
        _binding = null
        super.onDestroyView()
    }
}