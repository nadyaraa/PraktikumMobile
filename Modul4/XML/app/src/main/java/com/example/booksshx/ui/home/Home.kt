package com.example.booksshx.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.booksshx.R
import com.example.booksshx.databinding.FragmentHomeBinding
import com.example.booksshx.viewmodel.BookVMFactory
import com.example.booksshx.viewmodel.BookView
import kotlinx.coroutines.launch
import timber.log.Timber

class Home : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BookView by viewModels {
        BookVMFactory("Toko Buku NEXGENT")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvHorizontal.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL, false
        )
        binding.rvVertical.layoutManager = LinearLayoutManager(requireContext())

        val beliAction = { url: String ->
            val intent = Intent(Intent.ACTION_VIEW, url.toUri())
            startActivity(intent)
        }

        val detailAction = { id: Int ->
            viewModel.onBookClicked(id)
        }

        lifecycleScope.launch {
            viewModel.booksState.collect { bookList ->
                binding.rvHorizontal.adapter = Adapter(bookList, detailAction, beliAction, true)
                binding.rvVertical.adapter = Adapter(bookList, detailAction, beliAction, false)
            }
        }

        lifecycleScope.launch {
            viewModel.navigateToDetail.collect { id ->
                id?.let {
                    val book = viewModel.booksState.value.find { b ->
                        b.id == it
                    }

                    Timber.Forest.d("Berpindah ke halaman detail ${book?.title}")

                    val bundle = Bundle().apply {
                        putInt("bookId", it)
                    }

                    findNavController().navigate(R.id.action_home_to_detail, bundle)
                    viewModel.onNavigatedToDetail()
                }
            }
        }
    }
}