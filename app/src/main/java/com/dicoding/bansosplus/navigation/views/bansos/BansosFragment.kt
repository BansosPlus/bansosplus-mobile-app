package com.dicoding.bansosplus.navigation.views.bansos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dicoding.bansosplus.databinding.FragmentBansosBinding

class BansosFragment : Fragment() {

    private var _binding: FragmentBansosBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val bansosViewModel =
            ViewModelProvider(this).get(BansosViewModel::class.java)

        _binding = FragmentBansosBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.tvStatusPengajuan
        bansosViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}