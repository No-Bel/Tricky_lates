package com.example.triky.fragment.homeScreen

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.triky.R
import com.example.triky.adapter.Adapter
import com.example.triky.adapter.CompletedAdapter
import com.example.triky.data.User
import com.example.triky.databinding.FragmentHomeScreenBinding
import com.example.triky.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_home_screen.*
import java.util.*
import kotlin.collections.ArrayList


class HomeScreenFragment(val EditTask: (user: User) -> Unit): Fragment(), Adapter.UpdateTask {

    private var _binding: FragmentHomeScreenBinding? = null
    private val binding get() = _binding!!

    var userList: User = User(0, "", false, false, 0, "")
    private var newList = ArrayList<User>()
    private var completedList = ArrayList<User>()

    private lateinit var mUserViewModel: UserViewModel
    private lateinit var completedAdp: CompletedAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var completedRecyclerView: RecyclerView


    private var number = 0
    private var adapter: Adapter = Adapter(click = { list, itemView, position, number -> all(
        list,
        itemView,
        position,
        number
    )}, updateTaskWhenBoxTrue = { task -> whenCheckBoxTrue(task) })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDate()
        init()
        setListener()
    }

    private fun setListener() {
        binding.addTask.setOnClickListener {
            listener?.homeSwitchOnAddFragment()
        }
    }

    private fun init() {
        //Task RecyclerView
        val adapter = adapter
        recyclerView = binding.homeScreenRv
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter.editItem(this)

        //ViewModel
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            adapter.setData(user)
        })

        //complete Task RecyclerView
        completedAdp = CompletedAdapter(updateTaskWhenBoxFalse = { task -> whenCheckBoxFalse(task)})
        completedRecyclerView = binding.homeScreenCompleted
        completedRecyclerView.adapter = completedAdp
        completedRecyclerView.layoutManager = LinearLayoutManager(context)

        /** Completed tasks **/
        mUserViewModel.getCompletedDataVm().observe(viewLifecycleOwner, Observer { user ->
            //complete RecyclerView
            completedAdp.setCompletedData(user)
        })
    }


    /** (Cancel, Delete, Done) Btn **/
    private fun all(list: List<User>, itemView: ArrayList<View>, position: ArrayList<Int>, number: Number) {

        //setOnLongClickListener home_screen_below_btn Visible
            binding.homeScreenBelowBtn.isVisible = true

        this.newList = list as ArrayList<User>
        /** Done button **/
        binding.doneTaskBtn.setOnClickListener {
            newList.forEach { task ->
                task.selected = true
                task.checked = false
                mUserViewModel.updateItemWhenBoxTrueVm(task)
            }
            list.clear()
            itemView.clear()
            position.clear()
            adapter.num = 0
            binding.homeScreenBelowBtn.isVisible = false
            adapter.notifyDataSetChanged()

        }

        /** Cancel button **/
        cancel_task_btn.setOnClickListener {
            list.forEach {
                it.checked = false
            }
            adapter.num = 0
            list.clear()
            itemView.clear()
            position.clear()
            binding.homeScreenBelowBtn.isVisible = false
            adapter.notifyDataSetChanged()
        }


        /** Delete button  **/
        delete_task_button.setOnClickListener {
            mUserViewModel.deleteUserVm(list)
            position.clear()
            itemView.clear()
            adapter.num = 0
            binding.homeScreenBelowBtn.isVisible = false
        }

        if (position.isEmpty()) {
            binding.homeScreenBelowBtn.visibility = View.GONE
            adapter.num = 0
            adapter.notifyDataSetChanged()
        }
    }

    /** when checkBox = True, item go in completed recycler **/
    private fun whenCheckBoxTrue(task: User) {
        mUserViewModel.updateItemWhenBoxTrueVm(task)
    }

    /** when checkBox = False, completed item go to back in main recycler **/
    private fun whenCheckBoxFalse(task: User) {
        mUserViewModel.updateItemWhenBoxFalseVm(task)
    }

    private fun setDate() {
        val c = Calendar.getInstance()
        val month = c.get(Calendar.MONTH)
        val year = c.get(Calendar.YEAR)
        val dayOfTheWeek = c.get(Calendar.DAY_OF_WEEK)
        val dayOfMonth = c.get(Calendar.DAY_OF_MONTH)

        //Current Month and Year
        when (month) {
            0 -> { date_time_home_screen.text = ("January, $year") }
            1 -> { date_time_home_screen.text = ("February, $year") }
            2 -> { date_time_home_screen.text = ("March, $year") }
            3 -> { date_time_home_screen.text = ("April, $year") }
            4 -> { date_time_home_screen.text = ("May, $year") }
            5 -> { date_time_home_screen.text = ("June, $year") }
            6 -> { date_time_home_screen.text = ("July, $year") }
            7 -> { date_time_home_screen.text = ("August, $year") }
            8 -> { date_time_home_screen.text = ("September, $year") }
            9 -> { date_time_home_screen.text = ("October, $year") }
            10 -> { date_time_home_screen.text = ("November, $year") }
            11 -> { date_time_home_screen.text = ("December, $year") }
        }


        //ImgV VISIBLE || GONE
        when (dayOfTheWeek) {
            1 -> { //Sunday          კვირა
                Sunday.setTextColor(ContextCompat.getColor(requireContext(), R.color.what_day_we_got))
                Saturday.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey))
                Saturday_home_imgV.isVisible = false
                Sunday_home_imgV.isVisible = true
            }

            2 -> { //Monday          ორშაბათი
                Monday.setTextColor(ContextCompat.getColor(requireContext(), R.color.what_day_we_got))
                Sunday.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey))
                Sunday_home_imgV.isVisible = false
                Monday_home_imgV.isVisible = true
            }

            3 -> {//Tuesday         სამშაბათი
                Tuesday.setTextColor(ContextCompat.getColor(requireContext(), R.color.what_day_we_got))
                Monday.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey))
                Monday_home_imgV.isVisible = false
                Tuesday_home_imgV.isVisible = true
            }

            4 -> { //Wednesday       ოთხშაბათი
                Wednesday.setTextColor(ContextCompat.getColor(requireContext(), R.color.what_day_we_got))
                Tuesday.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey))
                Tuesday_home_imgV.isVisible = false
                Wednesday_home_imgV.isVisible = true
            }

            5 -> {//Thursday        ხუთშაბათი
                Thursday.setTextColor(ContextCompat.getColor(requireContext(), R.color.what_day_we_got))
                Wednesday.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey))
                Wednesday_home_imgV.isVisible = false
                Thursday_home_imgV.isVisible = true
            }

            6 -> { //Friday          პარასკევი
                Friday.setTextColor(ContextCompat.getColor(requireContext(), R.color.what_day_we_got))
                Thursday.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey))
                Thursday_home_imgV.isVisible = false
                Friday_home_imgV.isVisible = true
            }

            7 -> { //Saturday        შაბათი
                Saturday.setTextColor(ContextCompat.getColor(requireContext(), R.color.what_day_we_got))
                Friday.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey))
                Friday_home_imgV.isVisible = false
                Saturday_home_imgV.isVisible = true
            }
        }


        /**Month with 31 days**/
        // 0 January      2 March       4 May         6 July        7 August      9 October     11 December

        //October 2021
        if (month == 9) {

            if (dayOfMonth == 1 || dayOfMonth == 2 || dayOfMonth == 3
            ) {
                monday_hm.text = "27"
                tuesday_hm.text = "28"
                wednesday_hm.text = "29"
                thursday_hm.text = "30"
                friday_hm.text = "1"
                saturday_hm.text = "2"
                sunday_hm.text = "3"
            }

            if (dayOfMonth == 4 || dayOfMonth == 5 || dayOfMonth == 6 || dayOfMonth == 7
                || dayOfMonth == 8 || dayOfMonth == 9 || dayOfMonth == 10
            ) {
                monday_hm.text = "4"
                tuesday_hm.text = "5"
                wednesday_hm.text = "6"
                thursday_hm.text = "7"
                friday_hm.text = "8"
                saturday_hm.text = "9"
                sunday_hm.text = "10"
            }

            if (dayOfMonth == 11 || dayOfMonth == 12 || dayOfMonth == 13 || dayOfMonth == 14
                || dayOfMonth == 15 || dayOfMonth == 16 || dayOfMonth == 17
            ) {
                monday_hm.text = "11"
                tuesday_hm.text = "12"
                wednesday_hm.text = "13"
                thursday_hm.text = "14"
                friday_hm.text = "15"
                saturday_hm.text = "16"
                sunday_hm.text = "17"
            }

            if (dayOfMonth == 18 || dayOfMonth == 19 || dayOfMonth == 20 || dayOfMonth == 21
                || dayOfMonth == 22 || dayOfMonth == 23 || dayOfMonth == 24
            ) {
                monday_hm.text = "18"
                tuesday_hm.text = "19"
                wednesday_hm.text = "20"
                thursday_hm.text = "21"
                friday_hm.text = "22"
                saturday_hm.text = "23"
                sunday_hm.text = "24"
            }

            if (dayOfMonth == 25 || dayOfMonth == 26 || dayOfMonth == 27 || dayOfMonth == 28
                || dayOfMonth == 29 || dayOfMonth == 30 || dayOfMonth == 31
            ) {
                monday_hm.text = "25"
                tuesday_hm.text = "26"
                wednesday_hm.text = "27"
                thursday_hm.text = "28"
                friday_hm.text = "29"
                saturday_hm.text = "30"
                sunday_hm.text = "31"
            }
        }

        //December 2021
        if (month == 11) {

            if (dayOfMonth == 1 || dayOfMonth == 2 || dayOfMonth == 3 || dayOfMonth == 4
                || dayOfMonth == 5
            ) {
                monday_hm.text = "29"
                tuesday_hm.text = "30"
                wednesday_hm.text = "1"
                thursday_hm.text = "2"
                friday_hm.text = "3"
                saturday_hm.text = "4"
                sunday_hm.text = "5"
            }

            if (dayOfMonth == 6 || dayOfMonth == 7 || dayOfMonth == 8 || dayOfMonth == 9
                || dayOfMonth == 10 || dayOfMonth == 11 || dayOfMonth == 12
            ) {
                monday_hm.text = "6"
                tuesday_hm.text = "7"
                wednesday_hm.text = "8"
                thursday_hm.text = "9"
                friday_hm.text = "10"
                saturday_hm.text = "11"
                sunday_hm.text = "12"
            }

            if (dayOfMonth == 13 || dayOfMonth == 14 || dayOfMonth == 15 || dayOfMonth == 16
                || dayOfMonth == 17 || dayOfMonth == 18 || dayOfMonth == 19
            ) {
                monday_hm.text = "13"
                tuesday_hm.text = "14"
                wednesday_hm.text = "15"
                thursday_hm.text = "16"
                friday_hm.text = "17"
                saturday_hm.text = "18"
                sunday_hm.text = "19"
            }

            if (dayOfMonth == 20 || dayOfMonth == 21 || dayOfMonth == 22 || dayOfMonth == 23
                || dayOfMonth == 24 || dayOfMonth == 25 || dayOfMonth == 26
            ) {
                monday_hm.text = "20"
                tuesday_hm.text = "21"
                wednesday_hm.text = "22"
                thursday_hm.text = "23"
                friday_hm.text = "24"
                saturday_hm.text = "25"
                sunday_hm.text = "26"
            }

            if (dayOfMonth == 27 || dayOfMonth == 28 || dayOfMonth == 29 || dayOfMonth == 30
                || dayOfMonth == 31
            ) {
                monday_hm.text = "27"
                tuesday_hm.text = "28"
                wednesday_hm.text = "29"
                thursday_hm.text = "30"
                friday_hm.text = "31"
                saturday_hm.text = "1"
                sunday_hm.text = "2"
            }
        }

        //January 2022
        if (month == 0) {

            if (dayOfMonth == 1 || dayOfMonth == 2
            ) {
                monday_hm.text = "27"
                tuesday_hm.text = "28"
                wednesday_hm.text = "29"
                thursday_hm.text = "30"
                friday_hm.text = "31"
                saturday_hm.text = "1"
                sunday_hm.text = "2"
            }

            if (dayOfMonth == 3 || dayOfMonth == 4 || dayOfMonth == 5 || dayOfMonth == 6
                || dayOfMonth == 7 || dayOfMonth == 8 || dayOfMonth == 9
            ) {
                monday_hm.text = "3"
                tuesday_hm.text = "4"
                wednesday_hm.text = "5"
                thursday_hm.text = "6"
                friday_hm.text = "7"
                saturday_hm.text = "8"
                sunday_hm.text = "9"
            }

            if (dayOfMonth == 10 || dayOfMonth == 11 || dayOfMonth == 12 || dayOfMonth == 13
                || dayOfMonth == 14 || dayOfMonth == 15 || dayOfMonth == 16
            ) {
                monday_hm.text = "10"
                tuesday_hm.text = "11"
                wednesday_hm.text = "12"
                thursday_hm.text = "13"
                friday_hm.text = "14"
                saturday_hm.text = "15"
                sunday_hm.text = "16"
            }

            if (dayOfMonth == 17 || dayOfMonth == 18 || dayOfMonth == 19 || dayOfMonth == 20
                || dayOfMonth == 21 || dayOfMonth == 22 || dayOfMonth == 23
            ) {
                monday_hm.text = "17"
                tuesday_hm.text = "18"
                wednesday_hm.text = "19"
                thursday_hm.text = "20"
                friday_hm.text = "21"
                saturday_hm.text = "22"
                sunday_hm.text = "23"
            }

            if (dayOfMonth == 24 || dayOfMonth == 25 || dayOfMonth == 26 || dayOfMonth == 27
                || dayOfMonth == 28 || dayOfMonth == 29 || dayOfMonth == 30
            ) {
                monday_hm.text = "24"
                tuesday_hm.text = "25"
                wednesday_hm.text = "26"
                thursday_hm.text = "27"
                friday_hm.text = "28"
                saturday_hm.text = "29"
                sunday_hm.text = "30"
            }

            if (dayOfMonth == 31
            ) {
                monday_hm.text = "31"
                tuesday_hm.text = "1"
                wednesday_hm.text = "2"
                thursday_hm.text = "3"
                friday_hm.text = "4"
                saturday_hm.text = "5"
                sunday_hm.text = "6"
            }
        }

        //March
        if (month == 2) {

            if (dayOfMonth == 1 || dayOfMonth == 2 || dayOfMonth == 3 || dayOfMonth == 4
                || dayOfMonth == 5 || dayOfMonth == 6
            ) {
                monday_hm.text = "28"
                tuesday_hm.text = "1"
                wednesday_hm.text = "2"
                thursday_hm.text = "3"
                friday_hm.text = "4"
                saturday_hm.text = "5"
                sunday_hm.text = "6"
            }

            if (dayOfMonth == 7 || dayOfMonth == 8 || dayOfMonth == 9 || dayOfMonth == 10
                || dayOfMonth == 11 || dayOfMonth == 12 || dayOfMonth == 13
            ) {
                monday_hm.text = "7"
                tuesday_hm.text = "8"
                wednesday_hm.text = "9"
                thursday_hm.text = "10"
                friday_hm.text = "11"
                saturday_hm.text = "12"
                sunday_hm.text = "13"
            }

            if (dayOfMonth == 14 || dayOfMonth == 15 || dayOfMonth == 16 || dayOfMonth == 17
                || dayOfMonth == 18 || dayOfMonth == 19 || dayOfMonth == 20
            ) {
                monday_hm.text = "14"
                tuesday_hm.text = "15"
                wednesday_hm.text = "16"
                thursday_hm.text = "17"
                friday_hm.text = "18"
                saturday_hm.text = "19"
                sunday_hm.text = "20"
            }

            if (dayOfMonth == 21 || dayOfMonth == 22 || dayOfMonth == 23 || dayOfMonth == 24
                || dayOfMonth == 25 || dayOfMonth == 26 || dayOfMonth == 27
            ) {
                monday_hm.text = "21"
                tuesday_hm.text = "22"
                wednesday_hm.text = "23"
                thursday_hm.text = "24"
                friday_hm.text = "25"
                saturday_hm.text = "26"
                sunday_hm.text = "27"
            }

            if (dayOfMonth == 28 || dayOfMonth == 29 || dayOfMonth == 30 || dayOfMonth == 31
            ) {
                monday_hm.text = "28"
                tuesday_hm.text = "29"
                wednesday_hm.text = "30"
                thursday_hm.text = "31"
                friday_hm.text = "1"
                saturday_hm.text = "2"
                sunday_hm.text = "3"
            }
        }

        //May 2022
        if (month == 4) {

            if (dayOfMonth == 1
            ) {
                monday_hm.text = "25"
                tuesday_hm.text = "26"
                wednesday_hm.text = "27"
                thursday_hm.text = "28"
                friday_hm.text = "29"
                saturday_hm.text = "30"
                sunday_hm.text = "1"
            }

            if (dayOfMonth == 2 || dayOfMonth == 3 || dayOfMonth == 4 || dayOfMonth == 5
                || dayOfMonth == 6 || dayOfMonth == 7 || dayOfMonth == 8
            ) {
                monday_hm.text = "2"
                tuesday_hm.text = "3"
                wednesday_hm.text = "4"
                thursday_hm.text = "5"
                friday_hm.text = "6"
                saturday_hm.text = "7"
                sunday_hm.text = "8"
            }

            if (dayOfMonth == 9 || dayOfMonth == 10 || dayOfMonth == 11 || dayOfMonth == 12
                || dayOfMonth == 13 || dayOfMonth == 14 || dayOfMonth == 15
            ) {
                monday_hm.text = "9"
                tuesday_hm.text = "10"
                wednesday_hm.text = "11"
                thursday_hm.text = "12"
                friday_hm.text = "13"
                saturday_hm.text = "14"
                sunday_hm.text = "15"
            }

            if (dayOfMonth == 16 || dayOfMonth == 17 || dayOfMonth == 18 || dayOfMonth == 19
                || dayOfMonth == 20 || dayOfMonth == 21 || dayOfMonth == 22
            ) {
                monday_hm.text = "16"
                tuesday_hm.text = "17"
                wednesday_hm.text = "18"
                thursday_hm.text = "19"
                friday_hm.text = "20"
                saturday_hm.text = "21"
                sunday_hm.text = "22"
            }

            if (dayOfMonth == 23 || dayOfMonth == 24 || dayOfMonth == 25 || dayOfMonth == 26
                || dayOfMonth == 27 || dayOfMonth == 28 || dayOfMonth == 29
            ) {
                monday_hm.text = "23"
                tuesday_hm.text = "24"
                wednesday_hm.text = "25"
                thursday_hm.text = "26"
                friday_hm.text = "27"
                saturday_hm.text = "28"
                sunday_hm.text = "29"
            }

            if (dayOfMonth == 30 || dayOfMonth == 31
            ) {
                monday_hm.text = "30"
                tuesday_hm.text = "31"
                wednesday_hm.text = "1"
                thursday_hm.text = "2"
                friday_hm.text = "3"
                saturday_hm.text = "4"
                sunday_hm.text = "5"
            }
        }

        //July 2022
        if (month == 6) {

            if (dayOfMonth == 1 || dayOfMonth == 2 || dayOfMonth == 3
            ) {
                monday_hm.text = "27"
                tuesday_hm.text = "28"
                wednesday_hm.text = "29"
                thursday_hm.text = "30"
                friday_hm.text = "1"
                saturday_hm.text = "2"
                sunday_hm.text = "3"
            }

            if (dayOfMonth == 4 || dayOfMonth == 5 || dayOfMonth == 6 || dayOfMonth == 7
                || dayOfMonth == 8 || dayOfMonth == 9 || dayOfMonth == 10
            ) {
                monday_hm.text = "4"
                tuesday_hm.text = "5"
                wednesday_hm.text = "6"
                thursday_hm.text = "7"
                friday_hm.text = "8"
                saturday_hm.text = "9"
                sunday_hm.text = "10"
            }

            if (dayOfMonth == 11 || dayOfMonth == 12 || dayOfMonth == 13 || dayOfMonth == 14
                || dayOfMonth == 15 || dayOfMonth == 16 || dayOfMonth == 17
            ) {
                monday_hm.text = "11"
                tuesday_hm.text = "12"
                wednesday_hm.text = "13"
                thursday_hm.text = "14"
                friday_hm.text = "15"
                saturday_hm.text = "16"
                sunday_hm.text = "17"
            }

            if (dayOfMonth == 18 || dayOfMonth == 19 || dayOfMonth == 20 || dayOfMonth == 21
                || dayOfMonth == 22 || dayOfMonth == 23 || dayOfMonth == 24
            ) {
                monday_hm.text = "18"
                tuesday_hm.text = "19"
                wednesday_hm.text = "20"
                thursday_hm.text = "21"
                friday_hm.text = "22"
                saturday_hm.text = "23"
                sunday_hm.text = "24"
            }

            if (dayOfMonth == 25 || dayOfMonth == 26 || dayOfMonth == 27 || dayOfMonth == 28
                || dayOfMonth == 29 || dayOfMonth == 30 || dayOfMonth == 31
            ) {
                monday_hm.text = "25"
                tuesday_hm.text = "26"
                wednesday_hm.text = "27"
                thursday_hm.text = "28"
                friday_hm.text = "29"
                saturday_hm.text = "30"
                sunday_hm.text = "31"
            }
        }

        //August 2022
        if (month == 7) {

            if (dayOfMonth == 1 || dayOfMonth == 2 || dayOfMonth == 3 || dayOfMonth == 4
                || dayOfMonth == 5 || dayOfMonth == 6 || dayOfMonth == 7
            ) {
                monday_hm.text = "1"
                tuesday_hm.text = "2"
                wednesday_hm.text = "3"
                thursday_hm.text = "4"
                friday_hm.text = "5"
                saturday_hm.text = "6"
                sunday_hm.text = "7"
            }

            if (dayOfMonth == 8 || dayOfMonth == 9 || dayOfMonth == 10 || dayOfMonth == 11
                || dayOfMonth == 12 || dayOfMonth == 13 || dayOfMonth == 14
            ) {
                monday_hm.text = "8"
                tuesday_hm.text = "9"
                wednesday_hm.text = "10"
                thursday_hm.text = "11"
                friday_hm.text = "12"
                saturday_hm.text = "13"
                sunday_hm.text = "14"
            }

            if (dayOfMonth == 15 || dayOfMonth == 16 || dayOfMonth == 17 || dayOfMonth == 18
                || dayOfMonth == 19 || dayOfMonth == 20 || dayOfMonth == 21
            ) {
                monday_hm.text = "15"
                tuesday_hm.text = "16"
                wednesday_hm.text = "17"
                thursday_hm.text = "18"
                friday_hm.text = "19"
                saturday_hm.text = "20"
                sunday_hm.text = "21"
            }

            if (dayOfMonth == 22 || dayOfMonth == 23 || dayOfMonth == 24 || dayOfMonth == 25
                || dayOfMonth == 26 || dayOfMonth == 27 || dayOfMonth == 28
            ) {
                monday_hm.text = "22"
                tuesday_hm.text = "23"
                wednesday_hm.text = "24"
                thursday_hm.text = "25"
                friday_hm.text = "26"
                saturday_hm.text = "27"
                sunday_hm.text = "28"
            }

            if (dayOfMonth == 29 || dayOfMonth == 30 || dayOfMonth == 31
            ) {
                monday_hm.text = "29"
                tuesday_hm.text = "30"
                wednesday_hm.text = "31"
                thursday_hm.text = "1"
                friday_hm.text = "2"
                saturday_hm.text = "3"
                sunday_hm.text = "4"
            }
        }

        //October 2022
        if (month == 9 && year == 2022) {

            if (dayOfMonth == 1 || dayOfMonth == 2
            ) {
                monday_hm.text = "26"
                tuesday_hm.text = "27"
                wednesday_hm.text = "28"
                thursday_hm.text = "29"
                friday_hm.text = "30"
                saturday_hm.text = "1"
                sunday_hm.text = "2"
            }

            if (dayOfMonth == 3 || dayOfMonth == 4 || dayOfMonth == 5 || dayOfMonth == 6
                || dayOfMonth == 7 || dayOfMonth == 8 || dayOfMonth == 9
            ) {
                monday_hm.text = "3"
                tuesday_hm.text = "4"
                wednesday_hm.text = "5"
                thursday_hm.text = "6"
                friday_hm.text = "7"
                saturday_hm.text = "8"
                sunday_hm.text = "9"
            }

            if (dayOfMonth == 10 || dayOfMonth == 11 || dayOfMonth == 12 || dayOfMonth == 13
                || dayOfMonth == 14 || dayOfMonth == 15 || dayOfMonth == 16
            ) {
                monday_hm.text = "10"
                tuesday_hm.text = "11"
                wednesday_hm.text = "12"
                thursday_hm.text = "13"
                friday_hm.text = "14"
                saturday_hm.text = "15"
                sunday_hm.text = "16"
            }

            if (dayOfMonth == 17 || dayOfMonth == 18 || dayOfMonth == 19 || dayOfMonth == 20
                || dayOfMonth == 21 || dayOfMonth == 22 || dayOfMonth == 23
            ) {
                monday_hm.text = "17"
                tuesday_hm.text = "18"
                wednesday_hm.text = "19"
                thursday_hm.text = "20"
                friday_hm.text = "21"
                saturday_hm.text = "22"
                sunday_hm.text = "23"
            }

            if (dayOfMonth == 24 || dayOfMonth == 25 || dayOfMonth == 26 || dayOfMonth == 27
                || dayOfMonth == 28 || dayOfMonth == 29 || dayOfMonth == 30
            ) {
                monday_hm.text = "24"
                tuesday_hm.text = "25"
                wednesday_hm.text = "26"
                thursday_hm.text = "27"
                friday_hm.text = "28"
                saturday_hm.text = "29"
                sunday_hm.text = "30"
            }

            if (dayOfMonth == 31
            ) {
                monday_hm.text = "31"
                tuesday_hm.text = "1"
                wednesday_hm.text = "2"
                thursday_hm.text = "3"
                friday_hm.text = "4"
                saturday_hm.text = "5"
                sunday_hm.text = "6"
            }

        }

        //December 2022
        if (month == 11 && year == 2022) {

            if (dayOfMonth == 1 || dayOfMonth == 2 || dayOfMonth == 3 || dayOfMonth == 4
            ) {
                monday_hm.text = "28"
                tuesday_hm.text = "29"
                wednesday_hm.text = "30"
                thursday_hm.text = "1"
                friday_hm.text = "2"
                saturday_hm.text = "3"
                sunday_hm.text = "4"
            }

            if (dayOfMonth == 5 || dayOfMonth == 6 || dayOfMonth == 7 || dayOfMonth == 8
                || dayOfMonth == 9 || dayOfMonth == 10 || dayOfMonth == 11
            ) {
                monday_hm.text = "5"
                tuesday_hm.text = "6"
                wednesday_hm.text = "7"
                thursday_hm.text = "8"
                friday_hm.text = "9"
                saturday_hm.text = "10"
                sunday_hm.text = "11"
            }

            if (dayOfMonth == 12 || dayOfMonth == 13 || dayOfMonth == 14 || dayOfMonth == 15
                || dayOfMonth == 16 || dayOfMonth == 17 || dayOfMonth == 18
            ) {
                monday_hm.text = "12"
                tuesday_hm.text = "13"
                wednesday_hm.text = "14"
                thursday_hm.text = "15"
                friday_hm.text = "16"
                saturday_hm.text = "17"
                sunday_hm.text = "18"
            }

            if (dayOfMonth == 19 || dayOfMonth == 20 || dayOfMonth == 21 || dayOfMonth == 22
                || dayOfMonth == 23 || dayOfMonth == 24 || dayOfMonth == 25
            ) {
                monday_hm.text = "19"
                tuesday_hm.text = "20"
                wednesday_hm.text = "21"
                thursday_hm.text = "22"
                friday_hm.text = "23"
                saturday_hm.text = "24"
                sunday_hm.text = "25"
            }

            if (dayOfMonth == 26 || dayOfMonth == 27 || dayOfMonth == 28 || dayOfMonth == 29
                || dayOfMonth == 30 || dayOfMonth == 31
            ) {
                monday_hm.text = "26"
                tuesday_hm.text = "27"
                wednesday_hm.text = "28"
                thursday_hm.text = "29"
                friday_hm.text = "30"
                saturday_hm.text = "31"
                sunday_hm.text = "1"
            }

        }

        //January 2023
        if (month == 0 && year == 2023) {

            if (dayOfMonth == 1
            ) {
                monday_hm.text = "26"
                tuesday_hm.text = "27"
                wednesday_hm.text = "28"
                thursday_hm.text = "29"
                friday_hm.text = "30"
                saturday_hm.text = "31"
                sunday_hm.text = "1"
            }

            if (dayOfMonth == 2 || dayOfMonth == 3 || dayOfMonth == 4 || dayOfMonth == 5
                || dayOfMonth == 6 || dayOfMonth == 7 || dayOfMonth == 8
            ) {
                monday_hm.text = "2"
                tuesday_hm.text = "3"
                wednesday_hm.text = "4"
                thursday_hm.text = "5"
                friday_hm.text = "6"
                saturday_hm.text = "7"
                sunday_hm.text = "8"
            }

            if (dayOfMonth == 9 || dayOfMonth == 10 || dayOfMonth == 11 || dayOfMonth == 12
                || dayOfMonth == 13 || dayOfMonth == 14 || dayOfMonth == 15
            ) {
                monday_hm.text = "9"
                tuesday_hm.text = "10"
                wednesday_hm.text = "11"
                thursday_hm.text = "12"
                friday_hm.text = "13"
                saturday_hm.text = "14"
                sunday_hm.text = "15"
            }

            if (dayOfMonth == 16 || dayOfMonth == 17 || dayOfMonth == 18 || dayOfMonth == 19
                || dayOfMonth == 20 || dayOfMonth == 21 || dayOfMonth == 22
            ) {
                monday_hm.text = "16"
                tuesday_hm.text = "17"
                wednesday_hm.text = "18"
                thursday_hm.text = "19"
                friday_hm.text = "20"
                saturday_hm.text = "21"
                sunday_hm.text = "22"
            }

            if (dayOfMonth == 23 || dayOfMonth == 24 || dayOfMonth == 25 || dayOfMonth == 26
                || dayOfMonth == 27 || dayOfMonth == 28 || dayOfMonth == 29
            ) {
                monday_hm.text = "23"
                tuesday_hm.text = "24"
                wednesday_hm.text = "25"
                thursday_hm.text = "26"
                friday_hm.text = "27"
                saturday_hm.text = "28"
                sunday_hm.text = "29"
            }

            if (dayOfMonth == 30 || dayOfMonth == 31
            ) {
                monday_hm.text = "30"
                tuesday_hm.text = "31"
                wednesday_hm.text = "1"
                thursday_hm.text = "2"
                friday_hm.text = "3"
                saturday_hm.text = "4"
                sunday_hm.text = "5"
            }

        }


        /**Month with 30 days**/
        //  3 April       5 June        8 September   10 November

        //November 2021
        if (month == 10) {

            if (dayOfMonth == 1 || dayOfMonth == 2 || dayOfMonth == 3 || dayOfMonth == 4
                || dayOfMonth == 5 || dayOfMonth == 6 || dayOfMonth == 7
            ) {
                monday_hm.text = "1"
                tuesday_hm.text = "2"
                wednesday_hm.text = "3"
                thursday_hm.text = "4"
                friday_hm.text = "5"
                saturday_hm.text = "6"
                sunday_hm.text = "7"
            }

            if (dayOfMonth == 8 || dayOfMonth == 9 || dayOfMonth == 10 || dayOfMonth == 11
                || dayOfMonth == 12 || dayOfMonth == 13 || dayOfMonth == 14
            ) {
                monday_hm.text = "8"
                tuesday_hm.text = "9"
                wednesday_hm.text = "10"
                thursday_hm.text = "11"
                friday_hm.text = "12"
                saturday_hm.text = "13"
                sunday_hm.text = "14"
            }

            if (dayOfMonth == 15 || dayOfMonth == 16 || dayOfMonth == 17 || dayOfMonth == 18
                || dayOfMonth == 19 || dayOfMonth == 20 || dayOfMonth == 21
            ) {
                monday_hm.text = "15"
                tuesday_hm.text = "16"
                wednesday_hm.text = "17"
                thursday_hm.text = "18"
                friday_hm.text = "19"
                saturday_hm.text = "20"
                sunday_hm.text = "21"
            }

            if (dayOfMonth == 22 || dayOfMonth == 23 || dayOfMonth == 24 || dayOfMonth == 25
                || dayOfMonth == 26 || dayOfMonth == 27 || dayOfMonth == 28
            ) {
                monday_hm.text = "22"
                tuesday_hm.text = "23"
                wednesday_hm.text = "24"
                thursday_hm.text = "25"
                friday_hm.text = "26"
                saturday_hm.text = "27"
                sunday_hm.text = "28"
            }

            if (dayOfMonth == 29 || dayOfMonth == 30
            ) {
                monday_hm.text = "29"
                tuesday_hm.text = "30"
                wednesday_hm.text = "1"
                thursday_hm.text = "2"
                friday_hm.text = "3"
                saturday_hm.text = "4"
                sunday_hm.text = "5"
            }
        }

        /**Month with 28 days 2022 **/
        //1 February 2022
        if (month == 1) {

            if (dayOfMonth == 1 || dayOfMonth == 2 || dayOfMonth == 3 || dayOfMonth == 4
                || dayOfMonth == 5 || dayOfMonth == 6
            ) {
                monday_hm.text = "31"
                tuesday_hm.text = "1"
                wednesday_hm.text = "2"
                thursday_hm.text = "3"
                friday_hm.text = "4"
                saturday_hm.text = "5"
                sunday_hm.text = "6"
            }

            if (dayOfMonth == 7 || dayOfMonth == 8 || dayOfMonth == 9 || dayOfMonth == 10
                || dayOfMonth == 11 || dayOfMonth == 12 || dayOfMonth == 13
            ) {

                monday_hm.text = "7"
                tuesday_hm.text = "8"
                wednesday_hm.text = "9"
                thursday_hm.text = "10"
                friday_hm.text = "11"
                saturday_hm.text = "12"
                sunday_hm.text = "13"
            }

            if (dayOfMonth == 14 || dayOfMonth == 15 || dayOfMonth == 16 || dayOfMonth == 17
                || dayOfMonth == 18 || dayOfMonth == 19 || dayOfMonth == 20
            ) {
                monday_hm.text = "14"
                tuesday_hm.text = "15"
                wednesday_hm.text = "16"
                thursday_hm.text = "17"
                friday_hm.text = "18"
                saturday_hm.text = "19"
                sunday_hm.text = "20"
            }

            if (dayOfMonth == 21 || dayOfMonth == 22 || dayOfMonth == 23 || dayOfMonth == 24
                || dayOfMonth == 25 || dayOfMonth == 26 || dayOfMonth == 27
            ) {
                monday_hm.text = "21"
                tuesday_hm.text = "22"
                wednesday_hm.text = "23"
                thursday_hm.text = "24"
                friday_hm.text = "25"
                saturday_hm.text = "26"
                sunday_hm.text = "27"
            }

            if (dayOfMonth == 28
            ) {
                monday_hm.text = "28"
                tuesday_hm.text = "1"
                wednesday_hm.text = "2"
                thursday_hm.text = "3"
                friday_hm.text = "4"
                saturday_hm.text = "5"
                sunday_hm.text = "6"
            }
        }

        //April 2022
        if (month == 3) {

            if (dayOfMonth == 1 || dayOfMonth == 2 || dayOfMonth == 3
            ) {
                monday_hm.text = "28"
                tuesday_hm.text = "29"
                wednesday_hm.text = "30"
                thursday_hm.text = "31"
                friday_hm.text = "1"
                saturday_hm.text = "2"
                sunday_hm.text = "3"
            }

            if (dayOfMonth == 4 || dayOfMonth == 5 || dayOfMonth == 6 || dayOfMonth == 7
                || dayOfMonth == 8 || dayOfMonth == 9 || dayOfMonth == 10
            ) {
                monday_hm.text = "4"
                tuesday_hm.text = "5"
                wednesday_hm.text = "6"
                thursday_hm.text = "7"
                friday_hm.text = "8"
                saturday_hm.text = "9"
                sunday_hm.text = "10"
            }

            if (dayOfMonth == 11 || dayOfMonth == 12 || dayOfMonth == 13 || dayOfMonth == 14
                || dayOfMonth == 15 || dayOfMonth == 16 || dayOfMonth == 17
            ) {
                monday_hm.text = "11"
                tuesday_hm.text = "12"
                wednesday_hm.text = "13"
                thursday_hm.text = "14"
                friday_hm.text = "15"
                saturday_hm.text = "16"
                sunday_hm.text = "17"
            }

            if (dayOfMonth == 18 || dayOfMonth == 19 || dayOfMonth == 20 || dayOfMonth == 21
                || dayOfMonth == 22 || dayOfMonth == 23 || dayOfMonth == 24
            ) {
                monday_hm.text = "18"
                tuesday_hm.text = "19"
                wednesday_hm.text = "20"
                thursday_hm.text = "21"
                friday_hm.text = "22"
                saturday_hm.text = "23"
                sunday_hm.text = "24"
            }

            if (dayOfMonth == 25 || dayOfMonth == 26 || dayOfMonth == 27 || dayOfMonth == 28
                || dayOfMonth == 29 || dayOfMonth == 30
            ) {
                monday_hm.text = "25"
                tuesday_hm.text = "26"
                wednesday_hm.text = "27"
                thursday_hm.text = "28"
                friday_hm.text = "29"
                saturday_hm.text = "30"
                sunday_hm.text = "1"
            }
        }

        //June 2022
        if (month == 5) {

            if (dayOfMonth == 1 || dayOfMonth == 2 || dayOfMonth == 3 || dayOfMonth == 4
                || dayOfMonth == 5
            ) {
                monday_hm.text = "30"
                tuesday_hm.text = "31"
                wednesday_hm.text = "1"
                thursday_hm.text = "2"
                friday_hm.text = "3"
                saturday_hm.text = "4"
                sunday_hm.text = "5"
            }

            if (dayOfMonth == 6 || dayOfMonth == 7 || dayOfMonth == 8 || dayOfMonth == 9
                || dayOfMonth == 10 || dayOfMonth == 11 || dayOfMonth == 12
            ) {
                monday_hm.text = "6"
                tuesday_hm.text = "7"
                wednesday_hm.text = "8"
                thursday_hm.text = "9"
                friday_hm.text = "10"
                saturday_hm.text = "11"
                sunday_hm.text = "12"
            }

            if (dayOfMonth == 13 || dayOfMonth == 14 || dayOfMonth == 15 || dayOfMonth == 16
                || dayOfMonth == 17 || dayOfMonth == 18 || dayOfMonth == 19
            ) {
                monday_hm.text = "13"
                tuesday_hm.text = "14"
                wednesday_hm.text = "15"
                thursday_hm.text = "16"
                friday_hm.text = "17"
                saturday_hm.text = "18"
                sunday_hm.text = "19"
            }

            if (dayOfMonth == 20 || dayOfMonth == 21 || dayOfMonth == 22 || dayOfMonth == 23
                || dayOfMonth == 24 || dayOfMonth == 25 || dayOfMonth == 26
            ) {
                monday_hm.text = "20"
                tuesday_hm.text = "21"
                wednesday_hm.text = "22"
                thursday_hm.text = "23"
                friday_hm.text = "24"
                saturday_hm.text = "25"
                sunday_hm.text = "26"
            }

            if (dayOfMonth == 27 || dayOfMonth == 28 || dayOfMonth == 29 || dayOfMonth == 30
            ) {
                monday_hm.text = "27"
                tuesday_hm.text = "28"
                wednesday_hm.text = "29"
                thursday_hm.text = "30"
                friday_hm.text = "1"
                saturday_hm.text = "2"
                sunday_hm.text = "3"
            }
        }

        //September 2022
        if (month == 8) {

            if (dayOfMonth == 1 || dayOfMonth == 2 || dayOfMonth == 3 || dayOfMonth == 4
            ) {
                monday_hm.text = "29"
                tuesday_hm.text = "30"
                wednesday_hm.text = "31"
                thursday_hm.text = "1"
                friday_hm.text = "2"
                saturday_hm.text = "3"
                sunday_hm.text = "4"
            }

            if (dayOfMonth == 5 || dayOfMonth == 6 || dayOfMonth == 7 || dayOfMonth == 8
                || dayOfMonth == 9 || dayOfMonth == 10 || dayOfMonth == 11
            ) {
                monday_hm.text = "5"
                tuesday_hm.text = "6"
                wednesday_hm.text = "7"
                thursday_hm.text = "8"
                friday_hm.text = "9"
                saturday_hm.text = "10"
                sunday_hm.text = "11"
            }

            if (dayOfMonth == 12 || dayOfMonth == 13 || dayOfMonth == 14 || dayOfMonth == 15
                || dayOfMonth == 16 || dayOfMonth == 17 || dayOfMonth == 18
            ) {
                monday_hm.text = "12"
                tuesday_hm.text = "13"
                wednesday_hm.text = "14"
                thursday_hm.text = "15"
                friday_hm.text = "16"
                saturday_hm.text = "17"
                sunday_hm.text = "18"
            }

            if (dayOfMonth == 19 || dayOfMonth == 20 || dayOfMonth == 21 || dayOfMonth == 22
                || dayOfMonth == 23 || dayOfMonth == 24 || dayOfMonth == 25
            ) {
                monday_hm.text = "19"
                tuesday_hm.text = "20"
                wednesday_hm.text = "21"
                thursday_hm.text = "22"
                friday_hm.text = "23"
                saturday_hm.text = "24"
                sunday_hm.text = "25"
            }

            if (dayOfMonth == 26 || dayOfMonth == 27 || dayOfMonth == 28 || dayOfMonth == 29
                || dayOfMonth == 30
            ) {
                monday_hm.text = "26"
                tuesday_hm.text = "27"
                wednesday_hm.text = "28"
                thursday_hm.text = "29"
                friday_hm.text = "30"
                saturday_hm.text = "1"
                sunday_hm.text = "2"
            }
        }

        //November 2022
        if (month == 10 && year == 2022) {

            if (dayOfMonth == 1 || dayOfMonth == 2 || dayOfMonth == 3 || dayOfMonth == 4
                || dayOfMonth == 5 || dayOfMonth == 6
            ) {
                monday_hm.text = "31"
                tuesday_hm.text = "1"
                wednesday_hm.text = "2"
                thursday_hm.text = "3"
                friday_hm.text = "4"
                saturday_hm.text = "5"
                sunday_hm.text = "6"
            }

            if (dayOfMonth == 7 || dayOfMonth == 8 || dayOfMonth == 9 || dayOfMonth == 10
                || dayOfMonth == 11 || dayOfMonth == 12 || dayOfMonth == 13
            ) {
                monday_hm.text = "7"
                tuesday_hm.text = "8"
                wednesday_hm.text = "9"
                thursday_hm.text = "10"
                friday_hm.text = "11"
                saturday_hm.text = "12"
                sunday_hm.text = "13"
            }

            if (dayOfMonth == 14 || dayOfMonth == 15 || dayOfMonth == 16 || dayOfMonth == 17
                || dayOfMonth == 18 || dayOfMonth == 19 || dayOfMonth == 20
            ) {
                monday_hm.text = "14"
                tuesday_hm.text = "15"
                wednesday_hm.text = "16"
                thursday_hm.text = "17"
                friday_hm.text = "18"
                saturday_hm.text = "19"
                sunday_hm.text = "20"
            }

            if (dayOfMonth == 21 || dayOfMonth == 22 || dayOfMonth == 23 || dayOfMonth == 24
                || dayOfMonth == 25 || dayOfMonth == 26 || dayOfMonth == 27
            ) {
                monday_hm.text = "21"
                tuesday_hm.text = "22"
                wednesday_hm.text = "23"
                thursday_hm.text = "24"
                friday_hm.text = "25"
                saturday_hm.text = "26"
                sunday_hm.text = "27"
            }

            if (dayOfMonth == 28 || dayOfMonth == 29 || dayOfMonth == 30
            ) {
                monday_hm.text = "28"
                tuesday_hm.text = "29"
                wednesday_hm.text = "30"
                thursday_hm.text = "1"
                friday_hm.text = "2"
                saturday_hm.text = "3"
                sunday_hm.text = "4"
            }

        }
    }

    private var listener: HomeSwitchOnAddFragment? = null

    interface HomeSwitchOnAddFragment {
        fun homeSwitchOnAddFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as HomeSwitchOnAddFragment
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun updateTask(user: User) {
        userList = user
        EditTask(user)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}