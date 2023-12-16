package com.dicoding.bansosplus.navigation.views.bansos

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.bansosplus.SessionManager
import com.dicoding.bansosplus.databinding.FragmentBansosBinding
import com.dicoding.bansosplus.navigation.data.model.BansosStatusItem
import com.dicoding.bansosplus.navigation.views.adapter.StatusBansosListAdapter
import com.dicoding.bansosplus.navigation.views.bansos.acceptedBansos.DiterimaBansosActivity
import com.dicoding.bansosplus.navigation.views.home.detailBansos.DetailBansosActivity

class BansosFragment : Fragment() {
    private lateinit var viewModel: BansosViewModel
    private lateinit var sessionManager: SessionManager
    private var _binding: FragmentBansosBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBansosBinding.inflate(inflater, container, false)

        sessionManager = SessionManager(requireContext())

        viewModel = ViewModelProvider(this, BansosViewModelFactory(sessionManager)).get(BansosViewModel::class.java)

        binding.listBansosView.setHasFixedSize(true)
        binding.listBansosView.layoutManager = LinearLayoutManager(context)

        val adapter = StatusBansosListAdapter(ArrayList(), requireContext()) { selectedItem ->
            val intent = Intent(requireContext(), DiterimaBansosActivity::class.java)

            intent.putExtra("bansosRegistrationId", selectedItem.id.toString())
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