package com.example.bookshop_xml

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.bookshop_xml.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BookView by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bookId = arguments?.getInt("bookId") ?: 0
        val book = viewModel.books.find { it.id == bookId }

        book?.let {
            binding.tvDetailTitle.text = it.title
            binding.tvDetailWriter.text = "${getString(R.string.penulis)} ${it.writer}"
            binding.tvDetailPublisher.text = "${getString(R.string.penerbit)} ${it.publisher}"
            binding.tvDetailYear.text = getString(R.string.diterbitkan_pada, it.publicationYear)
            binding.tvDetailDesc.text = it.description
            binding.imgDetail.setImageResource(it.imageId)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}