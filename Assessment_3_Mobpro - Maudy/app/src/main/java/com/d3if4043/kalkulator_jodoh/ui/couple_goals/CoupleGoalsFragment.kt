package com.d3if4043.kalkulator_jodoh.ui.couple_goals

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.d3if4043.kalkulator_jodoh.R
import com.d3if4043.kalkulator_jodoh.databinding.FragmentCoupleGoalsBinding
import com.d3if4043.kalkulator_jodoh.model.CoupleGoals
import com.d3if4043.kalkulator_jodoh.network.CoupleGoalsApiService
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CoupleGoalsFragment : Fragment() {

    private lateinit var binding: FragmentCoupleGoalsBinding
    private val viewModel: CoupleGoalsViewModel by lazy {
        ViewModelProvider(this).get(CoupleGoalsViewModel::class.java)
    }
    lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: CoupleGoalsAdapter
    lateinit var coupleGoalsList: List<CoupleGoals>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCoupleGoalsBinding.inflate(layoutInflater,
            container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.recyclerview
        recyclerAdapter = CoupleGoalsAdapter(requireContext())
        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager = GridLayoutManager(context, 1)


        coupleGoalsList = ArrayList<CoupleGoals>()
        val apiInterface = CoupleGoalsApiService.create().getCoupleGoals()

        apiInterface.enqueue(object : Callback<List<CoupleGoals>> {
            override fun onResponse(
                call: Call<List<CoupleGoals>>,
                response: Response<List<CoupleGoals>>
            ) {
                if(response.body()?.isEmpty() == true) { // if data null
                    binding.recyclerview.visibility = View.GONE
                    binding.emptyView.visibility = View.VISIBLE

                    binding.progressBar.visibility = View.GONE
                } else { // data not null
                    binding.emptyView.visibility = View.GONE

                    coupleGoalsList = response.body()!!
                    recyclerAdapter.setCoupleGoalsList(requireContext(), coupleGoalsList)
                    binding.progressBar.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<List<CoupleGoals>>, t: Throwable) {
                val snackbar = Snackbar.make(view, getString(R.string.koneksi_error),
                    Snackbar.LENGTH_LONG).setAction("OK", null)
                val snackbarView = snackbar.view
                val textView =
                    snackbarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
                textView.setTextColor(Color.BLACK)
                snackbar.show()
            }
        })
        viewModel.scheduleUpdater(requireActivity().application)
    }
}