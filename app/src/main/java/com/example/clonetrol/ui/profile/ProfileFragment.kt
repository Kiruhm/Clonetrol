package com.example.clonetrol.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.example.clonetrol.databinding.FragmentProfileBinding
import com.example.clonetrol.ui.login.LoginActivity

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.logOutLayout.setOnClickListener {
            activity?.let {
                it.startActivity(Intent(it, LoginActivity::class.java))
                it.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                it.finish()

            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}