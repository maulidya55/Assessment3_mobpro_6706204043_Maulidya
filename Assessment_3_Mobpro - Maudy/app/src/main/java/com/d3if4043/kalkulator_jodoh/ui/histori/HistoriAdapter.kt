package com.d3if4043.kalkulator_jodoh.ui.histori

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.d3if4043.kalkulator_jodoh.databinding.ItemHistoriBinding
import com.d3if4043.kalkulator_jodoh.db.InputEntity
import com.d3if4043.kalkulator_jodoh.model.generateHasil
import java.text.SimpleDateFormat
import java.util.*



class HistoriAdapter : ListAdapter<InputEntity, HistoriAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<InputEntity>() {
            override fun areItemsTheSame(oldData: InputEntity, newData: InputEntity): Boolean {
                return oldData.id == newData.id
            }

            override fun areContentsTheSame(oldData: InputEntity, newData: InputEntity): Boolean {
                return oldData == newData
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoriBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemHistoriBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val dateFormatter = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID"))

        fun bind(item: InputEntity) = with(binding) {
            val hasil = item.generateHasil()
            inisialTextView.text = hasil.nama.substring(0, 1)

            tanggalTextView.text = dateFormatter.format(Date(item.tanggal))
            namaTextView.text = hasil.nama
            namaPasanganTextView.text = hasil.namaPasangan
        }
    }
}