package com.solodilov.ecommerceapp.presentation.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.solodilov.ecommerceapp.App
import com.solodilov.ecommerceapp.R
import com.solodilov.ecommerceapp.databinding.FragmentLoginBinding
import javax.inject.Inject

class LoginFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[LoginViewModel::class.java]
    }

    private var _binding: FragmentLoginBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("FragmentLoginBinding is null")


    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentLoginBinding.inflate(layoutInflater, container, false)

        initViews()
        observeViewModel()

        return binding.root
    }

    private fun initViews() {
        binding.signInButton.setOnClickListener {
            viewModel.signIn(
                binding.username.text.toString(),
                binding.password.text.toString()
            )
        }

        binding.errorLayout.tryButton.setOnClickListener { viewModel.signInWithToken() }
    }

    private fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner, ::processState)
        viewModel.loginSuccessEvent.observe(viewLifecycleOwner) { startProductListFragment() }
        viewModel.loginErrorEvent.observe(viewLifecycleOwner) { showLoginFailed() }
    }

    private fun processState(state: LoginScreenState) {
        when (state) {
            is LoginScreenState.Loading -> renderLoadingState()
            is LoginScreenState.Content -> renderContentState()
            is LoginScreenState.Error -> renderErrorState()
        }
    }

    private fun renderLoadingState() {
        toggleState(
            isLoading = true,
            isContent = false,
            isError = false,
        )
    }

    private fun renderContentState() {
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

    private fun startProductListFragment() {
        findNavController().navigate(R.id.action_login_fragment_to_product_list_fragment)
    }

    private fun showLoginFailed() {
        Snackbar.make(binding.root, R.string.auth_error, Snackbar.LENGTH_SHORT).show()
    }

    private fun toggleState(isLoading: Boolean, isContent: Boolean, isError: Boolean) {
        binding.progressBar.isVisible = isLoading
        binding.loginContainer.isVisible = isContent
        binding.errorLayout.root.isVisible = isError
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}