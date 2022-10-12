package com.solodilov.ecommerceapp.presentation.cart

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.solodilov.ecommerceapp.App
import com.solodilov.ecommerceapp.R
import com.solodilov.ecommerceapp.databinding.FragmentCartBinding
import javax.inject.Inject

class CartFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: CartViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[CartViewModel::class.java]
    }

    private var _binding: FragmentCartBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("FragmentCartBinding is null")

    private var cartProductAdapter: CartProductAdapter? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCartBinding.inflate(layoutInflater, container, false)

        initViews()
        observeViewModel()

        return binding.root
    }

    private fun initViews() {
        cartProductAdapter = CartProductAdapter()
        binding.cartProductList.adapter = cartProductAdapter

        binding.errorLayout.tryButton.setOnClickListener { viewModel.loadCartProductList() }
    }

    private fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner, ::processState)
    }


    private fun processState(state: CartScreenState) {
        when (state) {
            is CartScreenState.Loading -> renderLoadingState()
            is CartScreenState.Content -> renderContentState(state)
            is CartScreenState.Error -> renderErrorState()
        }
    }

    private fun renderLoadingState() {
        toggleState(
            isLoading = true,
            isContent = false,
            isError = false,
        )
    }

    private fun renderContentState(contentState: CartScreenState.Content) {
        if (contentState.content != null) {
            cartProductAdapter?.submitList(contentState.content.products)
            binding.totalPrice.text = getString(
                R.string.price_format,
                contentState.content.total
            )
            toggleState(
                isLoading = false,
                isContent = true,
                isError = false,
            )
            binding.emptyCart.isVisible = false
        } else {
            toggleState(
                isLoading = false,
                isContent = false,
                isError = false,
            )
            binding.emptyCart.isVisible = true
        }

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
        binding.cartContent.isVisible = isContent
        binding.errorLayout.root.isVisible = isError
    }

    override fun onDestroyView() {
        cartProductAdapter = null
        _binding = null
        super.onDestroyView()
    }
}