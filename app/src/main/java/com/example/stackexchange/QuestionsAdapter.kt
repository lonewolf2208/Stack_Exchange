package com.example.stackexchange

import android.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.stackexchange.databinding.HomeCardBinding
import com.example.stackexchange.model.HomeData.Item
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.material.chip.Chip
import java.sql.Date
import java.text.SimpleDateFormat


class QuestionsAdapter: ListAdapter<Item, QuestionsAdapter.QuestionViewHolder>(QuestionComparator()) {

    var clickListener: ClickListener?=null

    fun onClickListener( clickListener: ClickListener)
    {
        this.clickListener=clickListener
    }
    interface ClickListener{
        fun OnClick(position:Int,tagPosition:Int)
        fun OnTextClick(position: Int)
    }
    // View Holder class to hold the view
    inner class QuestionViewHolder(private val binding: HomeCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(questions_list: Item) {
            binding.apply {
                if(adapterPosition%5==0) {
                    val mAdView: AdView =this.adView3
                    val adRequest: AdRequest = AdRequest.Builder().build()
                    mAdView.loadAd(adRequest)
                    this.adView3.visibility = View.VISIBLE
                }
                this.question.text=questions_list.title.toString()
                this.Date.text= convertLongToTime(questions_list.creation_date.toLong()).toString()
                if(questions_list.owner.profile_image!=null) {
                    this.Picture.load(questions_list.owner.profile_image.toString())
                }
                this.question.setOnClickListener {
                    clickListener?.OnTextClick(adapterPosition)
                }
                this.Name.text=questions_list.owner.display_name.toString()
                for(i in 0..questions_list.tags.size-1){
                    val chip = Chip(this.chips.context)
                    chip.isCheckable=true
                    chip.id = i
                    chip.setText(questions_list.tags[i])
                    this.chips.addView(chip)
                }
                this.chips.setOnCheckedChangeListener { group, checkedId ->
                    clickListener?.OnClick(adapterPosition,checkedId)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val binding =
            HomeCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuestionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    class QuestionComparator : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item) =
            oldItem==newItem

        override fun areContentsTheSame(oldItem: Item, newItem: Item) =
            oldItem == newItem
    }
}
fun convertLongToTime(time: Long): String {
    val date = Date(time)
    val format = SimpleDateFormat("yyyy.MM.dd HH:mm")
    return format.format(date)
}


// Comparator class to check for the changes made.
// If there are no changes then no need to do anything.
