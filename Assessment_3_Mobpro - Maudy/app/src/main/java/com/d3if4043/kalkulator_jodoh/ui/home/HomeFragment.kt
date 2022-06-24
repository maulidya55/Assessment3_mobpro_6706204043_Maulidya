package com.d3if4043.kalkulator_jodoh.ui.home

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.d3if4043.kalkulator_jodoh.R
import com.d3if4043.kalkulator_jodoh.databinding.FragmentHomeBinding
import com.d3if4043.kalkulator_jodoh.db.InputDb
import com.d3if4043.kalkulator_jodoh.model.Output

class HomeFragment: Fragment() {
    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by lazy {
        val db = InputDb.getInstance(requireContext())
        val factory = HomeViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[HomeViewModel::class.java]

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.generateHasilButton.setOnClickListener { generateHasil() }
        binding.shareButton.setOnClickListener{ shareData() }
        viewModel.getHasil().observe(requireActivity(), { showResult(it) })

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_about -> {
                findNavController().navigate(
                    R.id.action_homeFragment_to_aboutFragment)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun generateHasil() {
        val nama = binding.namaInp.text.toString()
        val namaPasangan = binding.namaPasanganInp.text.toString()
        if (TextUtils.isEmpty(nama) || TextUtils.isEmpty(namaPasangan)) {
            Toast.makeText(context, R.string.nama_invalid, Toast.LENGTH_LONG).show()
            return
        }
        viewModel.generateHasil(nama, namaPasangan)
    }

    private fun showResult(result: Output?) {
        if (result == null) return
        binding.hasilText.text = result.hasil
        binding.shareButton.visibility = View.VISIBLE
    }

    private fun shareData() {
        val message = "Hai, aku baru saja menggunakan kalkulator jodoh dan aku semakin yakin bahwa dia bukan jodohku :(!"
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, message)
        if (shareIntent.resolveActivity(
                requireActivity().packageManager) != null) {
            startActivity(shareIntent)
        }
    }
}