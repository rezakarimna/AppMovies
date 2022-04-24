package com.reza.appmovies.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import com.reza.appmovies.R
import com.reza.appmovies.databinding.FragmentSplashBinding
import com.reza.appmovies.utils.StoreUserData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : Fragment() {
    //binding
    lateinit var binding: FragmentSplashBinding

    @Inject
    lateinit var storeUserData: StoreUserData
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //set delay
        lifecycle.coroutineScope.launchWhenCreated {
            delay(2000)
            // set check token
            storeUserData.getUserToke().collect{
                if (it.isEmpty()){
                    findNavController().navigate(R.id.actionSplashToRegister)
                } else {
                    findNavController().navigate(R.id.actionToHome)
                }
            }
        }
    }
}