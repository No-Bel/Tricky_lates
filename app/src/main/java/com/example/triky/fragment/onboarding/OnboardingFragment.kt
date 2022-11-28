package com.example.triky.fragment.onboarding

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.triky.databinding.OnboardingBinding


class OnboardingFragment : Fragment() {

    private var _binding: OnboardingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = OnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
    }

    private fun setListener() {
        binding.onBoardingBtn.setOnClickListener {
            listener?.buttonClicked()
        }
    }

    private var listener : ButtonClicked? = null
    interface ButtonClicked {
        fun buttonClicked ()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as ButtonClicked
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}