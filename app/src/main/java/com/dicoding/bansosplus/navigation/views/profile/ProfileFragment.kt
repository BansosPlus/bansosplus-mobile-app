package com.dicoding.bansosplus.navigation.views.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dicoding.bansosplus.SessionManager
import com.dicoding.bansosplus.databinding.FragmentProfileBinding
import com.dicoding.bansosplus.navigation.views.scanQr.ScanQrActivity

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ProfileViewModel
    private lateinit var sessionManager: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        binding.buttonQr.setOnClickListener(){
            val intent = Intent(requireContext(), ScanQrActivity::class.java)
            startActivity(intent)
        }

        val root : View = binding.root

        sessionManager = SessionManager(requireContext())

        viewModel = ViewModelProvider(this, ProfileViewModelFactory(sessionManager)).get(ProfileViewModel::class.java)
// get user data from viewmodel return bearer token
//        viewModel.use
        binding.apply {
            tvProfileNameUser.text = "${sessionManager.fetchName()?:""}"
            tvProfileNikUser.text = "${sessionManager.fetchNIK()?:""}"
            tvProfileKkUser.text = "${sessionManager.fetchNoKK()?:""}"
//            Glide.with(this)
//                .load(getImageUrl)
//                .into(ivProfileUser)
        }

//        viewModel.getUserData().observe(viewLifecycleOwner){user ->
//            binding.tvProfileNameUser.text = user.name
//            binding.tvProfileNikUser.text = user.nik
//            binding.tvProfileKk.text = user.noKk
////            binding.ivProfileUser.loadImage(user.photoUrl)
//        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}