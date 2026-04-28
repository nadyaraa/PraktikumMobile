package com.example.bookshop_xml

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookshop_xml.databinding.FragmentHomeBinding
import androidx.core.net.toUri

class Home : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BookView by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val detailAction = { id: Int ->
            val bundle = Bundle().apply {
                putInt("bookId", id)
            }
            findNavController().navigate(R.id.action_home_to_detail, bundle)
        }

        val beliAction = { url: String ->
            val intent = Intent(Intent.ACTION_VIEW, url.toUri())
            startActivity(intent)
        }

        binding.rvHorizontal.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvHorizontal.adapter = Adapter(viewModel.books, detailAction, beliAction, true)

        binding.rvVertical.layoutManager = LinearLayoutManager(requireContext())
        binding.rvVertical.adapter = Adapter(viewModel.books, detailAction, beliAction, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}