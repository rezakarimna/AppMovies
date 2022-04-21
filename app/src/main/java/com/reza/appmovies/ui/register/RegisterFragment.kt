package com.reza.appmovies.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import com.google.android.material.snackbar.Snackbar
import com.reza.appmovies.R
import com.reza.appmovies.data.models.BodyRegister
import com.reza.appmovies.databinding.FragmentRegisterBinding
import com.reza.appmovies.utils.StoreUserData
import com.reza.appmovies.utils.showInVisible
import com.reza.appmovies.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    //binding
    lateinit var binding: FragmentRegisterBinding

    @Inject
    lateinit var storeUserData: StoreUserData

    @Inject
    lateinit var bodyRegister: BodyRegister

    //Other
    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.submitBtn.setOnClickListener {
            initAndValidationField()
            //sendData
            viewModel.sendRegisterUser(bodyRegister)
            //loading
            observeLoading()
            //save user
            observeAndSaveUserRegister()
        }

    }

    private fun initAndValidationField() {
        binding.apply {
            val name = nameEdt.text.toString()
            val email = emailEdt.text.toString()
            val password = passwordEdt.text.toString()
            if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                validationBodyRegister(name, email, password)
            } else {
                Snackbar.make(
                    binding.submitBtn,
                    getString(R.string.fillAllFields),
                    Snackbar.LENGTH_SHORT
                )
                    .show()
            }
        }
    }

    private fun validationBodyRegister(name: String, email: String, password: String) {
        bodyRegister.name = name
        bodyRegister.email = email
        bodyRegister.password = password
    }

    private fun observeLoading() {
        viewModel.loading.observe(viewLifecycleOwner) {
            if (it) {
                binding.submitLoading.showInVisible(true)
                binding.submitBtn.showInVisible(false)
            } else {
                binding.submitLoading.showInVisible(false)
                binding.submitBtn.showInVisible(true)
            }
        }
    }

    private fun observeAndSaveUserRegister() {
        viewModel.registerUser.observe(viewLifecycleOwner) { response ->
            lifecycle.coroutineScope.launchWhenCreated {
                storeUserData.saveUserToken(response.name.toString())
            }
        }
    }
}