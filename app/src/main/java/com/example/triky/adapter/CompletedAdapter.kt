package com.example.triky.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.triky.R
import com.example.triky.data.User
import kotlinx.android.synthetic.main.completed_list_view_holder.view.*

class CompletedAdapter(val updateTaskWhenBoxFalse: (task: User) -> Unit) :
    RecyclerView.Adapter<CompletedAdapter.CompletedListViewHolder>() {

    private var completedList = ArrayList<User>()

    inner class CompletedListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun unCompleted(task: User) {
            itemView.completedCheckBox2.setOnClickListener {
                task.selected = false
                updateTaskWhenBoxFalse(task)
                completedList.remove(task)
                notifyDataSetChanged()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompletedListViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.completed_list_view_holder, parent, false)
        view.completed_item.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        return CompletedListViewHolder(view)
    }

    override fun onBindViewHolder(holder: CompletedListViewHolder, position: Int) {
        val currentItem = completedList[position]
        holder.itemView.completedCheckBox2.isChecked = true
        holder.itemView.completed_item.text = currentItem.textInfo
        holder.unCompleted(currentItem)
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int = completedList.size

    fun setCompletedData(user: List<User>) {
        this.completedList.clear()
        this.completedList.addAll(user)
        notifyDataSetChanged()
    }

}