package com.d121211056.task.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.d121211056.task.R
import com.d121211056.task.databinding.ItemGameBinding
import com.d121211056.task.domain.GameItem

class GameAdapter : RecyclerView.Adapter<GameAdapter.MyGameViewHolder>() {

    private var games: List<GameItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyGameViewHolder {
        return MyGameViewHolder(ItemGameBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyGameViewHolder, position: Int) {

        val game = games[position]

        holder.binding.apply {

            mImage.load(game.thumbnail) {
                placeholder(R.drawable.ic_image)
                error(R.drawable.ic_image)
                crossfade(true)
                crossfade(400)
            }
            mTitle.text = game.title

        }

    }

    override fun getItemCount(): Int {
        return games.size
    }

    class MyGameViewHolder(val binding: ItemGameBinding): RecyclerView.ViewHolder(binding.root)

    fun setGames(games: List<GameItem>) {
        this.games = games
    }

}