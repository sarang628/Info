package com.sarang.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.screen_info.databinding.FragmentInfoBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * 레스토랑 상세정보 프레그먼트 입니다.
 * [InfoViewModel]
 */
@AndroidEntryPoint
class InfoFragment : Fragment() {
    private val mViewModel: InfoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentInfoBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        activity?.intent?.let {
           mViewModel.load(it.getIntExtra("restaurantId", 0))
        }

        subscribeUI(binding)
        return binding.root
    }

    private fun subscribeUI(binding: FragmentInfoBinding) {
        mViewModel.restaurant.observe(viewLifecycleOwner) {
            binding.restaurant = it
        }

        mViewModel.menus.observe(viewLifecycleOwner) {
            binding.menus = it
        }

        mViewModel.hoursOfOperstaions.observe(viewLifecycleOwner) {
            binding.hoursOfOperations = it
        }
    }
}