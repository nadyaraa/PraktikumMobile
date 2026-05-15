package com.example.booksshx.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.booksshx.R
import com.example.booksshx.data.Books
import com.example.booksshx.databinding.ItemBookBinding
import timber.log.Timber

class Adapter(
    private val books: List<Books>,
    private val onDetail: (Int) -> Unit,
    private val onBeli: (String) -> Unit,
    private val isHorizontal: Boolean = false
) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    class ViewHolder(val binding: ItemBookBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        if (isHorizontal) {
            binding.root.layoutParams.width = 350.dpToPx(parent.context)
        }

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = books[position]
        val context = holder.itemView.context

        with(holder.binding) {
            tvTitle.text = book.title
            tvYear.text = book.publicationYear.toString()
            tvWriter.text = context.getString(R.string.penulis, book.writer)
            tvPublisher.text = book.publisher
            tvDesc.text = book.description
            imgBook.setImageResource(book.imageId)

            btnDetail.setOnClickListener {
                Timber.Forest.d("Tombol detail diklik untuk buku ID ${book.id}")
                onDetail(book.id)
            }

            btnBeli.setOnClickListener {
                Timber.Forest.d("Tombol beli diklik untuk URL ${book.olshopUrl}")
                onBeli(book.olshopUrl)
            }
        }
    }

    override fun getItemCount() = books.size

    private fun Int.dpToPx(context: Context): Int =
        (this * context.resources.displayMetrics.density).toInt()
}