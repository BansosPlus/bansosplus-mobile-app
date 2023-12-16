package com.dicoding.bansosplus.navigation.views.history

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.bansosplus.SessionManager
import com.dicoding.bansosplus.databinding.FragmentBansosBinding
import com.dicoding.bansosplus.databinding.FragmentHistoryBinding
import com.dicoding.bansosplus.navigation.data.model.BansosStatusItem
import com.dicoding.bansosplus.navigation.views.adapter.HistoryListAdapter
import com.dicoding.bansosplus.navigation.views.history.detailHistory.DetailHistoryActivity
import com.dicoding.bansosplus.navigation.views.home.detailBansos.DetailBansosActivity

class HistoryFragment : Fragment() {
    private lateinit var viewModel: HistoryViewModel
    private lateinit var sessionManager: SessionManager
    private var _binding: FragmentHistoryBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)

        sessionManager = SessionManager(requireContext())

        viewModel = ViewModelProvider(this, HistoryViewModelFactory(sessionManager)).get(HistoryViewModel::class.java)

        binding.listBansosView.setHasFixedSize(true)
        binding.listBansosView.layoutManager = LinearLayoutManager(context)

        val adapter = HistoryListAdapter(ArrayList(), requireContext()) { selectedItem ->

            Log.d("FEEDBACK PAGE", "bansos id : ${selectedItem.bansosId}")
            Log.d("FEEDBACK PAGE", "bansos regis id : ${selectedItem.id}")
            val intent = Intent(requireContext(), DetailHistoryActivity::class.java)
            intent.putExtra("bansosRegistrationId", selectedItem.id.toString())
            intent.putExtra("bansosId", selectedItem.bansosId.toString())
            startActivity(intent)
        }

        binding.listBansosView.adapter = adapter

        val bansosRegistrationListUpdateObserver: Observer<ArrayList<BansosStatusItem>> =
            Observer<ArrayList<BansosStatusItem>> { bansosRegistrationList ->
                adapter.updateBansosRegistrationList( bansosRegistrationList )
            }

        viewModel.bansosRegistrationList.observe(viewLifecycleOwner, bansosRegistrationListUpdateObserver)

        viewModel.getBansos()

        return  binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}