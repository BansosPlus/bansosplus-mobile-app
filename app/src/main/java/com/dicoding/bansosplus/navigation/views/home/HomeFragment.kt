package com.dicoding.bansosplus.navigation.views.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.bansosplus.databinding.FragmentHomeBinding
import com.dicoding.bansosplus.navigation.data.model.BansosItem
import com.dicoding.bansosplus.navigation.views.adapter.BansosListAdapter

class HomeFragment : Fragment() {
    private lateinit var viewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        binding.listBansosView.setHasFixedSize(true)
        binding.listBansosView.layoutManager = LinearLayoutManager(context)

        var adapter = BansosListAdapter(ArrayList(), requireContext())
        adapter.notifyDataSetChanged()
        binding.listBansosView.adapter = adapter

        val bansosListUpdateObserver: Observer<ArrayList<BansosItem>> =
            Observer<ArrayList<BansosItem>> { bansosList ->
                adapter.updateBansosList( bansosList )
            }

        viewModel.bansosList.observe(viewLifecycleOwner, bansosListUpdateObserver)

        viewModel.getBansos()

        return  binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}