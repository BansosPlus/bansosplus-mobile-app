package com.dicoding.bansosplus.navigation.views.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.bansosplus.SessionManager
import com.dicoding.bansosplus.databinding.FragmentHomeBinding
import com.dicoding.bansosplus.navigation.data.model.BansosItem
import com.dicoding.bansosplus.navigation.views.adapter.BansosListAdapter
import com.dicoding.bansosplus.navigation.views.home.detailBansos.DetailBansosActivity

class HomeFragment : Fragment() {
    private lateinit var viewModel: HomeViewModel
    private lateinit var sessionManager: SessionManager
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        sessionManager = SessionManager(requireContext())

        viewModel = ViewModelProvider(this, HomeViewModelFactory(sessionManager)).get(HomeViewModel::class.java)

        binding.username.text = "${sessionManager.fetchName() ?: ""} !"

        binding.listBansosView.setHasFixedSize(true)
        binding.listBansosView.layoutManager = LinearLayoutManager(context)

        val adapter = BansosListAdapter(ArrayList(), requireContext()) { selectedItem ->
            val intent = Intent(requireContext(), DetailBansosActivity::class.java)
            intent.putExtra("bansosId", selectedItem.id.toString())
            startActivity(intent)
        }

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