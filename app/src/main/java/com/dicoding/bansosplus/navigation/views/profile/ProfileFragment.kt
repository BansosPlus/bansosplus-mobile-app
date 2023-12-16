package com.dicoding.bansosplus.navigation.views.profile

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.dicoding.bansosplus.SessionManager
import com.dicoding.bansosplus.databinding.FragmentProfileBinding
import com.dicoding.bansosplus.navigation.views.scanQr.ScanQrActivity
import com.dicoding.bansosplus.repository.UserRepository
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

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

        val root : View = binding.root

        sessionManager = SessionManager(requireContext())

        if (sessionManager.fetchRole() !== "admin") {
            binding.buttonQr.visibility = View.GONE
        } else {
            binding.buttonQr.setOnClickListener(){
                val intent = Intent(requireContext(), ScanQrActivity::class.java)
                startActivity(intent)
            }
        }

        viewModel = ViewModelProvider(this, ProfileViewModelFactory(sessionManager)).get(ProfileViewModel::class.java)

        binding.apply {
            viewModel.profileData.observe(viewLifecycleOwner) { userDetails ->
                if (userDetails != null) {
                    tvProfileNameUser.text = userDetails.name
                    tvProfileNikUser.text = userDetails.nik
                    tvProfileKkUser.text = userDetails.noKk
                    if (userDetails.imageUrl != "") {
                        Glide.with(this@ProfileFragment)
                            .load(userDetails.imageUrl)
                            .into(ivProfileUser)
                    }
                }
            }

            viewModel.getUserData()

            ivProfileUser.setOnClickListener {
                openImageGallery()
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun openImageGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        resultLauncher.launch(intent)
    }

    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val selectedImageUri: Uri? = result.data?.data
            if (selectedImageUri != null) {
                lifecycleScope.launch() {
                    uploadImage(selectedImageUri)
                }
            }
        }
    }

    private suspend fun uploadImage(uri: Uri) {
        try {
            val filePath = getPathFromUri(uri)
            val file = File(filePath)

            if (file.exists()) {
                val requestFile = RequestBody.create(MediaType.parse("image/*"), file)
                val multipartBody = MultipartBody.Part.createFormData("file", file.name, requestFile)

                val response = UserRepository(sessionManager).update(multipartBody)
                if (response.isSuccessful) {
                    Log.i("BANSOS", "Update user successfully")
                    viewModel.getUserData()
                } else {
                    Log.e("BANSOS", "Response failed: ${response.code()} - ${response.message()}")
                }
            } else {
                Log.e("BANSOS", "File not found at path: $filePath")
            }
        } catch (e: Exception) {
            Log.e("BANSOS", "Error during file upload", e)
        }
    }

    private fun getPathFromUri(uri: Uri): String {
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = requireContext().contentResolver.query(uri, projection, null, null, null)

        cursor?.use {
            if (it.moveToFirst()) {
                val columnIndex = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                return it.getString(columnIndex)
            }
        }

        return uri.path ?: ""
    }
}