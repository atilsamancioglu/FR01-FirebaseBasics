package com.atilsamancioglu.firebasetestproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_layout.view.*

class TweetAdapter(var tweetList : ArrayList<Tweet>): RecyclerView.Adapter<TweetAdapter.TweetHolder>() {

    class TweetHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout,parent,false)
        return TweetHolder(view)
    }

    override fun onBindViewHolder(holder: TweetHolder, position: Int) {

        holder.itemView.tweetTextRecyclerRow.text = tweetList.get(position).tweet
        holder.itemView.emailTextRecyclerRow.text = tweetList.get(position).user

    }

    override fun getItemCount(): Int {
        return tweetList.size
    }


}