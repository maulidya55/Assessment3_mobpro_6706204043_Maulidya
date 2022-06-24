package com.d3if4043.kalkulator_jodoh.ui.couple_goals

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.d3if4043.kalkulator_jodoh.PicassoClient
import com.d3if4043.kalkulator_jodoh.R
import com.d3if4043.kalkulator_jodoh.model.CoupleGoals

class CoupleGoalsAdapter(var context: Context): RecyclerView.Adapter<CoupleGoalsAdapter.MyViewHolder>() {

    var coupleGoalsList: List<CoupleGoals> = listOf()
    var coupleGoalsListFiltered: List<CoupleGoals> = listOf()

    fun setCoupleGoalsList(context: Context, coupleGoalsList: List<CoupleGoals>) {
        this.context = context
        if (coupleGoalsList == null) {
            this.coupleGoalsList = coupleGoalsList
            this.coupleGoalsListFiltered = coupleGoalsList
            notifyItemChanged(0, coupleGoalsListFiltered.size)
        } else {
            val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun getOldListSize(): Int {
                    return this@CoupleGoalsAdapter.coupleGoalsList.size
                }

                override fun getNewListSize(): Int {
                    return coupleGoalsList.size
                }

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    return this@CoupleGoalsAdapter.coupleGoalsList.get(oldItemPosition)
                        .goals === coupleGoalsList[newItemPosition].goals
                }

                override fun areContentsTheSame(
                    oldItemPosition: Int,
                    newItemPosition: Int
                ): Boolean {
                    val newGoals: CoupleGoals = this@CoupleGoalsAdapter.coupleGoalsList.get(oldItemPosition)
                    val oldGoals: CoupleGoals = coupleGoalsList[newItemPosition]
                    return newGoals.goals === oldGoals.goals
                }
            })
            this.coupleGoalsList = coupleGoalsList
            this.coupleGoalsListFiltered = coupleGoalsList
            result.dispatchUpdatesTo(this)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_couple_goals, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoupleGoalsAdapter.MyViewHolder, position: Int) {
        holder.goals!!.text = coupleGoalsListFiltered.get(position).goals

        val coupleGoals: CoupleGoals = coupleGoalsListFiltered.get(position)

        val images: String = coupleGoals.image
        val goals: String = coupleGoals.goals

        holder.image!!.setImageURI((Uri.parse(images)))
        holder.goals!!.text = goals

        PicassoClient.downloadImage(context, coupleGoalsListFiltered.get(position).image, holder.image)
    }

    interface ItemClickListener {
        fun onItemClick(pos: Int)
    }

    inner class MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!),
        View.OnClickListener {

        private lateinit var itemClickListener: ItemClickListener

        var image: ImageView? = null
        var goals: TextView? = null

        init {
            image = itemView!!.findViewById<View>(R.id.imageView) as ImageView
            goals = itemView.findViewById<View>(R.id.goals) as TextView
        }

        override fun onClick(p0: View?) {
            TODO("Not yet implemented")
        }
    }

    override fun getItemCount(): Int {
        return coupleGoalsListFiltered.size
    }
}