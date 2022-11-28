package com.example.triky.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.triky.R
import com.example.triky.data.User
import kotlinx.android.synthetic.main.recycler_list_view_holder.view.*

class Adapter(
    val click: (
        list: List<User>,
        itemView: ArrayList<View>,
        position: ArrayList<Int>,
        number: Int
    ) -> Unit,
    val updateTaskWhenBoxTrue: (task: User) -> Unit
) : RecyclerView.Adapter<Adapter.AdapterViewHolder>() {


    var stringList = ArrayList<User>()
    private var selectedList = ArrayList<User>()
    private var stringListView = ArrayList<View>()
    private var stringListPosition = ArrayList<Int>()


    var num = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        return AdapterViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_list_view_holder, parent, false)
        )

    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {

        val currentItem = stringList[position]
        holder.checkTask(currentItem)
        if (stringListPosition.contains(position)) {
            holder.itemView.recycler_list_view_holder.setBackgroundResource(R.drawable.radius_recycler_view_holder)
            holder.itemView.checkBox.setButtonDrawable(R.drawable.ic_checkbox_cyan)
        } else {
            holder.itemView.recycler_list_view_holder.setBackgroundResource(R.drawable.recycler_list_view_holder_drawable)
            holder.itemView.checkBox.setButtonDrawable(R.drawable.unselected_checkbox)
        }
        holder.itemView.checkBox.isChecked = false
        holder.itemView.list_holder.text = currentItem.textInfo
        holder.changeTask(currentItem)
        holder.clickListener(currentItem, holder, position)


        /** Calendar date HomeScreenFragment**/
        if (currentItem.date == "") {
            holder.itemView.date_list_holder.isVisible = false
        } else {
            holder.itemView.date_list_holder.isVisible = true
            holder.itemView.date_list_holder.text = currentItem.date
        }

        /** Choose Color Task HomeScreenFragment **/
        when (currentItem.color) {
            0 -> { holder.itemView.red_task_color.isVisible = true }
            1 -> { holder.itemView.orange_task_color.isVisible = true }
            2 -> { holder.itemView.yellow_task_color.isVisible = true }
            3 -> { holder.itemView.green_task_color.isVisible = true}
            4 -> { holder.itemView.lightBlue_task_color.isVisible = true }
            5 -> { holder.itemView.blue_task_color.isVisible = true }
            6 -> { holder.itemView.purple_task_color.isVisible = true }
            7 -> { holder.itemView.lightPurple_task_color.isVisible = true }
            8 -> { holder.itemView.pink_task_color.isVisible = true }
            else -> {
                holder.itemView.red_task_color.isVisible = false
                holder.itemView.orange_task_color.isVisible = false
                holder.itemView.yellow_task_color.isVisible = false
                holder.itemView.green_task_color.isVisible = false
                holder.itemView.lightBlue_task_color.isVisible = false
                holder.itemView.blue_task_color.isVisible = false
                holder.itemView.purple_task_color.isVisible = false
                holder.itemView.lightPurple_task_color.isVisible = false
                holder.itemView.pink_task_color.isVisible = false
            }
        }
    }

    override fun getItemCount(): Int = stringList.size

    fun setData(user: List<User>) {
        this.stringList.clear()
        this.stringList.addAll(user)
        notifyDataSetChanged()
    }

    private var listenerUpdateTask: UpdateTask? = null

    interface UpdateTask {
        fun updateTask(user: User)
    }

    fun editItem(listener: UpdateTask) {
        this.listenerUpdateTask = listener
    }


    inner class AdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        //when click on item we go to UpdateFragment
        fun changeTask(user: User) {
            if (num == 0) {
                itemView.setOnClickListener {
                    listenerUpdateTask?.updateTask(user)
                }
            }
        }

        //when checkBox = True item go in completed recycler
        fun checkTask(task: User) {
            itemView.checkBox.setOnClickListener {
                task.selected = true
                stringList.remove(task)
                updateTaskWhenBoxTrue(task)
                notifyDataSetChanged()
                num = 0
            }
        }

        fun clickListener(user: User, holder: AdapterViewHolder, number: Int) {
            if (num == 0) {
                itemView.setOnLongClickListener {
                    if (!user.checked) {
                        user.checked = true
                        selectedList.add(user)
                        stringListView.add(itemView)
                        stringListPosition.add(adapterPosition)
                        click.invoke(selectedList, stringListView, stringListPosition, num)
                        num = 1
                        notifyDataSetChanged()
                    }
                    true
                }
            } else if (num == 1) {
                itemView.setOnClickListener {
                    if (!user.checked) {
                        user.checked = true
                        selectedList.add(user)
                        stringListView.add(itemView)
                        stringListPosition.add(adapterPosition)
                        click.invoke(selectedList, stringListView, stringListPosition, num)
                        notifyDataSetChanged()
                    } else {
                        user.checked = false
                        holder.itemView.checkBox.isChecked = false
                        selectedList.remove(user)
                        stringListView.remove(itemView)
                        stringListPosition.remove(adapterPosition)
                        click.invoke(selectedList, stringListView, stringListPosition, num)
                        notifyDataSetChanged()
                    }
                }
            }
        }
    }
}