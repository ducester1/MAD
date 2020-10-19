package com.example.madlevel4task2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_game.view.*
import kotlinx.android.synthetic.main.item_game.view.*

class GameAdapter(private val games: ArrayList<Game>): RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun databind(game: Game) {
            itemView.tvDate.text = game.date.toString()

            when(game.computerMove) {
                GameMoves.ROCK -> itemView.ivHistoryComputer.setImageResource(R.drawable.rock)
                GameMoves.PAPER -> itemView.ivHistoryComputer.setImageResource(R.drawable.paper)
                GameMoves.SCISSOR -> itemView.ivHistoryComputer.setImageResource(R.drawable.scissors)
            }

            when(game.playerMove) {
                GameMoves.ROCK -> itemView.ivHistoryPlayer.setImageResource(R.drawable.rock)
                GameMoves.PAPER -> itemView.ivHistoryPlayer.setImageResource(R.drawable.paper)
                GameMoves.SCISSOR -> itemView.ivHistoryPlayer.setImageResource(R.drawable.scissors)
            }

            when(game.result) {
                GameResults.WIN -> itemView.tvHistoryResult.setText(R.string.win)
                GameResults.LOSE -> itemView.tvHistoryResult.setText(R.string.lose)
                GameResults.DRAW -> itemView.tvHistoryResult.setText(R.string.draw)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
            .inflate(R.layout.item_game, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(games[position])
    }

    override fun getItemCount(): Int {
        return games.size
    }
}