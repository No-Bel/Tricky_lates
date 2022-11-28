package com.example.triky.fragment.emptyHomeScreen

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.triky.R
import com.example.triky.databinding.FragmentNoTaskHomeScreenBinding
import kotlinx.android.synthetic.main.fragment_no_task_home_screen.*
import kotlinx.android.synthetic.main.fragment_no_task_home_screen.view.*
import java.util.*

class NoTaskHomeScreenFragment : Fragment() {

    private var _binding: FragmentNoTaskHomeScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNoTaskHomeScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
        setDate()
    }

    private fun setListener() {
        add_task_button.setOnClickListener {
            listener?.switchOnAddFragment()
        }
    }

    private fun setDate() {
        val c = Calendar.getInstance()
        val month = c.get(Calendar.MONTH)
        val year = c.get(Calendar.YEAR)
        val dayOfTheWeek = c.get(Calendar.DAY_OF_WEEK)
        val dayOfMonth = c.get(Calendar.DAY_OF_MONTH)

        //Current Month and Year
        when (month) {
            0 -> { date_time_no_home_screen.text = ("January, $year") }
            1 -> { date_time_no_home_screen.text = ("February, $year") }
            2 -> { date_time_no_home_screen.text = ("March, $year") }
            3 -> { date_time_no_home_screen.text = ("April, $year") }
            4 -> { date_time_no_home_screen.text = ("May, $year") }
            5 -> { date_time_no_home_screen.text = ("June, $year") }
            6 -> { date_time_no_home_screen.text = ("July, $year") }
            7 -> { date_time_no_home_screen.text = ("August, $year") }
            8 -> { date_time_no_home_screen.text = ("September, $year") }
            9 -> { date_time_no_home_screen.text = ("October, $year") }
            10 -> { date_time_no_home_screen.text = ("November, $year") }
            11 -> { date_time_no_home_screen.text = ("December, $year") }
        }

        //ImgV VISIBLE || GONE
        when (dayOfTheWeek) {

            1 -> { //Sunday          კვირა
                Sunday_no_task.setTextColor(ContextCompat.getColor(requireContext(), R.color.what_day_we_got))
                Saturday_no_task.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey))
                Saturday_ImgV.isVisible = false
                Sunday_ImgV.isVisible = true
            }

            2 -> { //Monday          ორშაბათი
                Monday_no_task.setTextColor(ContextCompat.getColor(requireContext(), R.color.what_day_we_got))
                Sunday_no_task.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey))
                Sunday_ImgV.isVisible = false
                Monday_ImgV.isVisible = true
            }

            3 -> {//Tuesday         სამშაბათი
                Tuesday_no_task.setTextColor(ContextCompat.getColor(requireContext(), R.color.what_day_we_got))
                Monday_no_task.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey))
                Monday_ImgV.isVisible = false
                Tuesday_ImgV.isVisible = true
            }

            4 -> { //Wednesday       ოთხშაბათი
                Wednesday_no_task.setTextColor(ContextCompat.getColor(requireContext(), R.color.what_day_we_got))
                Tuesday_no_task.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey))
                Tuesday_ImgV.isVisible = false
                Wednesday_ImgV.isVisible = true
            }

            5 -> {//Thursday        ხუთშაბათი
                Thursday_no_task.setTextColor(ContextCompat.getColor(requireContext(), R.color.what_day_we_got))
                Wednesday_no_task.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey))
                Wednesday_ImgV.isVisible = false
                Thursday_ImgV.isVisible = true
            }

            6 -> { //Friday          პარასკევი
                Friday_no_task.setTextColor(ContextCompat.getColor(requireContext(), R.color.what_day_we_got))
                Thursday_no_task.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey))
                Thursday_ImgV.isVisible = false
                Friday_ImgV.isVisible = true
            }

            7 -> { //Saturday        შაბათი
                Saturday_no_task.setTextColor(ContextCompat.getColor(requireContext(), R.color.what_day_we_got))
                Friday_no_task.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey))
                Friday_ImgV.isVisible = false
                Saturday_ImgV.isVisible = true
            }
        }

//        Wednesday_ImgV.visibility = View.GONE
//        Thursday_ImgV.visibility = View.VISIBLE

//         Sunday_no_task.text = "S"       //Sunday          კვირა
//         Monday_no_task.text = "M"       //Monday          ორშაბათი
//         Tuesday_no_task.text = "T"      //Tuesday         სამშაბათი
//         Wednesday_no_task.text = "W"    //Wednesday       ოთხშაბათი
//         Thursday_no_task.text = "Th"     //Thursday        ხუთშაბათი
//         Friday_no_task.text = "F"        //Friday          პარასკევი
//         Saturday_no_task.text = "St"     //Saturday        შაბათი


        /**Month with 31 days**/
        // 0 January      2 March       4 May         6 July        7 August      9 October     11 December

        //October 2021
        if (month == 9) {

            if (dayOfMonth == 1 || dayOfMonth == 2 || dayOfMonth == 3
            ) {
                monday_no_task.text = "27"
                tuesday_no_task.text = "28"
                wednesday_no_task.text = "29"
                thursday_no_task.text = "30"
                friday_no_task.text = "1"
                saturday_no_task.text = "2"
                sunday_no_task.text = "3"
            }

            if (dayOfMonth == 4 || dayOfMonth == 5 || dayOfMonth == 6 || dayOfMonth == 7
                || dayOfMonth == 8 || dayOfMonth == 9 || dayOfMonth == 10
            ) {
                monday_no_task.text = "4"
                tuesday_no_task.text = "5"
                wednesday_no_task.text = "6"
                thursday_no_task.text = "7"
                friday_no_task.text = "8"
                saturday_no_task.text = "9"
                sunday_no_task.text = "10"
            }

            if (dayOfMonth == 11 || dayOfMonth == 12 || dayOfMonth == 13 || dayOfMonth == 14
                || dayOfMonth == 15 || dayOfMonth == 16 || dayOfMonth == 17
            ) {
                monday_no_task.text = "11"
                tuesday_no_task.text = "12"
                wednesday_no_task.text = "13"
                thursday_no_task.text = "14"
                friday_no_task.text = "15"
                saturday_no_task.text = "16"
                sunday_no_task.text = "17"
            }

            if (dayOfMonth == 18 || dayOfMonth == 19 || dayOfMonth == 20 || dayOfMonth == 21
                || dayOfMonth == 22 || dayOfMonth == 23 || dayOfMonth == 24
            ) {
                monday_no_task.text = "18"
                tuesday_no_task.text = "19"
                wednesday_no_task.text = "20"
                thursday_no_task.text = "21"
                friday_no_task.text = "22"
                saturday_no_task.text = "23"
                sunday_no_task.text = "24"
            }

            if (dayOfMonth == 25 || dayOfMonth == 26 || dayOfMonth == 27 || dayOfMonth == 28
                || dayOfMonth == 29 || dayOfMonth == 30 || dayOfMonth == 31
            ) {
                monday_no_task.text = "25"
                tuesday_no_task.text = "26"
                wednesday_no_task.text = "27"
                thursday_no_task.text = "28"
                friday_no_task.text = "29"
                saturday_no_task.text = "30"
                sunday_no_task.text = "31"
            }
        }

        //December 2021
        if (month == 11) {

            if (dayOfMonth == 1 || dayOfMonth == 2 || dayOfMonth == 3 || dayOfMonth == 4
                || dayOfMonth == 5
            ) {
                monday_no_task.text = "29"
                tuesday_no_task.text = "30"
                wednesday_no_task.text = "1"
                thursday_no_task.text = "2"
                friday_no_task.text = "3"
                saturday_no_task.text = "4"
                sunday_no_task.text = "5"
            }

            if (dayOfMonth == 6 || dayOfMonth == 7 || dayOfMonth == 8 || dayOfMonth == 9
                || dayOfMonth == 10 || dayOfMonth == 11 || dayOfMonth == 12
            ) {
                monday_no_task.text = "6"
                tuesday_no_task.text = "7"
                wednesday_no_task.text = "8"
                thursday_no_task.text = "9"
                friday_no_task.text = "10"
                saturday_no_task.text = "11"
                sunday_no_task.text = "12"
            }

            if (dayOfMonth == 13 || dayOfMonth == 14 || dayOfMonth == 15 || dayOfMonth == 16
                || dayOfMonth == 17 || dayOfMonth == 18 || dayOfMonth == 19
            ) {
                monday_no_task.text = "13"
                tuesday_no_task.text = "14"
                wednesday_no_task.text = "15"
                thursday_no_task.text = "16"
                friday_no_task.text = "17"
                saturday_no_task.text = "18"
                sunday_no_task.text = "19"
            }

            if (dayOfMonth == 20 || dayOfMonth == 21 || dayOfMonth == 22 || dayOfMonth == 23
                || dayOfMonth == 24 || dayOfMonth == 25 || dayOfMonth == 26
            ) {
                monday_no_task.text = "20"
                tuesday_no_task.text = "21"
                wednesday_no_task.text = "22"
                thursday_no_task.text = "23"
                friday_no_task.text = "24"
                saturday_no_task.text = "25"
                sunday_no_task.text = "26"
            }

            if (dayOfMonth == 27 || dayOfMonth == 28 || dayOfMonth == 29 || dayOfMonth == 30
                || dayOfMonth == 31
            ) {
                monday_no_task.text = "27"
                tuesday_no_task.text = "28"
                wednesday_no_task.text = "29"
                thursday_no_task.text = "30"
                friday_no_task.text = "31"
                saturday_no_task.text = "1"
                sunday_no_task.text = "2"
            }
        }


        //January 2022
        if (month == 0) {

            if (dayOfMonth == 1 || dayOfMonth == 2
            ) {
                monday_no_task.text = "27"
                tuesday_no_task.text = "28"
                wednesday_no_task.text = "29"
                thursday_no_task.text = "30"
                friday_no_task.text = "31"
                saturday_no_task.text = "1"
                sunday_no_task.text = "2"
            }

            if (dayOfMonth == 3 || dayOfMonth == 4 || dayOfMonth == 5 || dayOfMonth == 6
                || dayOfMonth == 7 || dayOfMonth == 8 || dayOfMonth == 9
            ) {
                monday_no_task.text = "3"
                tuesday_no_task.text = "4"
                wednesday_no_task.text = "5"
                thursday_no_task.text = "6"
                friday_no_task.text = "7"
                saturday_no_task.text = "8"
                sunday_no_task.text = "9"
            }

            if (dayOfMonth == 10 || dayOfMonth == 11 || dayOfMonth == 12 || dayOfMonth == 13
                || dayOfMonth == 14 || dayOfMonth == 15 || dayOfMonth == 16
            ) {
                monday_no_task.text = "10"
                tuesday_no_task.text = "11"
                wednesday_no_task.text = "12"
                thursday_no_task.text = "13"
                friday_no_task.text = "14"
                saturday_no_task.text = "15"
                sunday_no_task.text = "16"
            }

            if (dayOfMonth == 17 || dayOfMonth == 18 || dayOfMonth == 19 || dayOfMonth == 20
                || dayOfMonth == 21 || dayOfMonth == 22 || dayOfMonth == 23
            ) {
                monday_no_task.text = "17"
                tuesday_no_task.text = "18"
                wednesday_no_task.text = "19"
                thursday_no_task.text = "20"
                friday_no_task.text = "21"
                saturday_no_task.text = "22"
                sunday_no_task.text = "23"
            }

            if (dayOfMonth == 24 || dayOfMonth == 25 || dayOfMonth == 26 || dayOfMonth == 27
                || dayOfMonth == 28 || dayOfMonth == 29 || dayOfMonth == 30
            ) {
                monday_no_task.text = "24"
                tuesday_no_task.text = "25"
                wednesday_no_task.text = "26"
                thursday_no_task.text = "27"
                friday_no_task.text = "28"
                saturday_no_task.text = "29"
                sunday_no_task.text = "30"
            }

            if (dayOfMonth == 31
            ) {
                monday_no_task.text = "31"
                tuesday_no_task.text = "1"
                wednesday_no_task.text = "2"
                thursday_no_task.text = "3"
                friday_no_task.text = "4"
                saturday_no_task.text = "5"
                sunday_no_task.text = "6"
            }
        }

        //March
        if (month == 2) {

            if (dayOfMonth == 1 || dayOfMonth == 2 || dayOfMonth == 3 || dayOfMonth == 4
                || dayOfMonth == 5 || dayOfMonth == 6
            ) {
                monday_no_task.text = "28"
                tuesday_no_task.text = "1"
                wednesday_no_task.text = "2"
                thursday_no_task.text = "3"
                friday_no_task.text = "4"
                saturday_no_task.text = "5"
                sunday_no_task.text = "6"
            }

            if (dayOfMonth == 7 || dayOfMonth == 8 || dayOfMonth == 9 || dayOfMonth == 10
                || dayOfMonth == 11 || dayOfMonth == 12 || dayOfMonth == 13
            ) {
                monday_no_task.text = "7"
                tuesday_no_task.text = "8"
                wednesday_no_task.text = "9"
                thursday_no_task.text = "10"
                friday_no_task.text = "11"
                saturday_no_task.text = "12"
                sunday_no_task.text = "13"
            }

            if (dayOfMonth == 14 || dayOfMonth == 15 || dayOfMonth == 16 || dayOfMonth == 17
                || dayOfMonth == 18 || dayOfMonth == 19 || dayOfMonth == 20
            ) {
                monday_no_task.text = "14"
                tuesday_no_task.text = "15"
                wednesday_no_task.text = "16"
                thursday_no_task.text = "17"
                friday_no_task.text = "18"
                saturday_no_task.text = "19"
                sunday_no_task.text = "20"
            }

            if (dayOfMonth == 21 || dayOfMonth == 22 || dayOfMonth == 23 || dayOfMonth == 24
                || dayOfMonth == 25 || dayOfMonth == 26 || dayOfMonth == 27
            ) {
                monday_no_task.text = "21"
                tuesday_no_task.text = "22"
                wednesday_no_task.text = "23"
                thursday_no_task.text = "24"
                friday_no_task.text = "25"
                saturday_no_task.text = "26"
                sunday_no_task.text = "27"
            }

            if (dayOfMonth == 28 || dayOfMonth == 29 || dayOfMonth == 30 || dayOfMonth == 31
            ) {
                monday_no_task.text = "28"
                tuesday_no_task.text = "29"
                wednesday_no_task.text = "30"
                thursday_no_task.text = "31"
                friday_no_task.text = "1"
                saturday_no_task.text = "2"
                sunday_no_task.text = "3"
            }
        }

        //May 2022
        if (month == 4) {

            if (dayOfMonth == 1
            ) {
                monday_no_task.text = "25"
                tuesday_no_task.text = "26"
                wednesday_no_task.text = "27"
                thursday_no_task.text = "28"
                friday_no_task.text = "29"
                saturday_no_task.text = "30"
                sunday_no_task.text = "1"
            }

            if (dayOfMonth == 2 || dayOfMonth == 3 || dayOfMonth == 4 || dayOfMonth == 5
                || dayOfMonth == 6 || dayOfMonth == 7 || dayOfMonth == 8
            ) {
                monday_no_task.text = "2"
                tuesday_no_task.text = "3"
                wednesday_no_task.text = "4"
                thursday_no_task.text = "5"
                friday_no_task.text = "6"
                saturday_no_task.text = "7"
                sunday_no_task.text = "8"
            }

            if (dayOfMonth == 9 || dayOfMonth == 10 || dayOfMonth == 11 || dayOfMonth == 12
                || dayOfMonth == 13 || dayOfMonth == 14 || dayOfMonth == 15
            ) {
                monday_no_task.text = "9"
                tuesday_no_task.text = "10"
                wednesday_no_task.text = "11"
                thursday_no_task.text = "12"
                friday_no_task.text = "13"
                saturday_no_task.text = "14"
                sunday_no_task.text = "15"
            }

            if (dayOfMonth == 16 || dayOfMonth == 17 || dayOfMonth == 18 || dayOfMonth == 19
                || dayOfMonth == 20 || dayOfMonth == 21 || dayOfMonth == 22
            ) {
                monday_no_task.text = "16"
                tuesday_no_task.text = "17"
                wednesday_no_task.text = "18"
                thursday_no_task.text = "19"
                friday_no_task.text = "20"
                saturday_no_task.text = "21"
                sunday_no_task.text = "22"
            }

            if (dayOfMonth == 23 || dayOfMonth == 24 || dayOfMonth == 25 || dayOfMonth == 26
                || dayOfMonth == 27 || dayOfMonth == 28 || dayOfMonth == 29
            ) {
                monday_no_task.text = "23"
                tuesday_no_task.text = "24"
                wednesday_no_task.text = "25"
                thursday_no_task.text = "26"
                friday_no_task.text = "27"
                saturday_no_task.text = "28"
                sunday_no_task.text = "29"
            }

            if (dayOfMonth == 30 || dayOfMonth == 31
            ) {
                monday_no_task.text = "30"
                tuesday_no_task.text = "31"
                wednesday_no_task.text = "1"
                thursday_no_task.text = "2"
                friday_no_task.text = "3"
                saturday_no_task.text = "4"
                sunday_no_task.text = "5"
            }
        }

        //July 2022
        if (month == 6) {

            if (dayOfMonth == 1 || dayOfMonth == 2 || dayOfMonth == 3
            ) {
                monday_no_task.text = "27"
                tuesday_no_task.text = "28"
                wednesday_no_task.text = "29"
                thursday_no_task.text = "30"
                friday_no_task.text = "1"
                saturday_no_task.text = "2"
                sunday_no_task.text = "3"
            }

            if (dayOfMonth == 4 || dayOfMonth == 5 || dayOfMonth == 6 || dayOfMonth == 7
                || dayOfMonth == 8 || dayOfMonth == 9 || dayOfMonth == 10
            ) {
                monday_no_task.text = "4"
                tuesday_no_task.text = "5"
                wednesday_no_task.text = "6"
                thursday_no_task.text = "7"
                friday_no_task.text = "8"
                saturday_no_task.text = "9"
                sunday_no_task.text = "10"
            }

            if (dayOfMonth == 11 || dayOfMonth == 12 || dayOfMonth == 13 || dayOfMonth == 14
                || dayOfMonth == 15 || dayOfMonth == 16 || dayOfMonth == 17
            ) {
                monday_no_task.text = "11"
                tuesday_no_task.text = "12"
                wednesday_no_task.text = "13"
                thursday_no_task.text = "14"
                friday_no_task.text = "15"
                saturday_no_task.text = "16"
                sunday_no_task.text = "17"
            }

            if (dayOfMonth == 18 || dayOfMonth == 19 || dayOfMonth == 20 || dayOfMonth == 21
                || dayOfMonth == 22 || dayOfMonth == 23 || dayOfMonth == 24
            ) {
                monday_no_task.text = "18"
                tuesday_no_task.text = "19"
                wednesday_no_task.text = "20"
                thursday_no_task.text = "21"
                friday_no_task.text = "22"
                saturday_no_task.text = "23"
                sunday_no_task.text = "24"
            }

            if (dayOfMonth == 25 || dayOfMonth == 26 || dayOfMonth == 27 || dayOfMonth == 28
                || dayOfMonth == 29 || dayOfMonth == 30 || dayOfMonth == 31
            ) {
                monday_no_task.text = "25"
                tuesday_no_task.text = "26"
                wednesday_no_task.text = "27"
                thursday_no_task.text = "28"
                friday_no_task.text = "29"
                saturday_no_task.text = "30"
                sunday_no_task.text = "31"
            }
        }

        //August 2022
        if (month == 7) {

            if (dayOfMonth == 1 || dayOfMonth == 2 || dayOfMonth == 3 || dayOfMonth == 4
                || dayOfMonth == 5 || dayOfMonth == 6 || dayOfMonth == 7
            ) {
                monday_no_task.text = "1"
                tuesday_no_task.text = "2"
                wednesday_no_task.text = "3"
                thursday_no_task.text = "4"
                friday_no_task.text = "5"
                saturday_no_task.text = "6"
                sunday_no_task.text = "7"
            }

            if (dayOfMonth == 8 || dayOfMonth == 9 || dayOfMonth == 10 || dayOfMonth == 11
                || dayOfMonth == 12 || dayOfMonth == 13 || dayOfMonth == 14
            ) {
                monday_no_task.text = "8"
                tuesday_no_task.text = "9"
                wednesday_no_task.text = "10"
                thursday_no_task.text = "11"
                friday_no_task.text = "12"
                saturday_no_task.text = "13"
                sunday_no_task.text = "14"
            }

            if (dayOfMonth == 15 || dayOfMonth == 16 || dayOfMonth == 17 || dayOfMonth == 18
                || dayOfMonth == 19 || dayOfMonth == 20 || dayOfMonth == 21
            ) {
                monday_no_task.text = "15"
                tuesday_no_task.text = "16"
                wednesday_no_task.text = "17"
                thursday_no_task.text = "18"
                friday_no_task.text = "19"
                saturday_no_task.text = "20"
                sunday_no_task.text = "21"
            }

            if (dayOfMonth == 22 || dayOfMonth == 23 || dayOfMonth == 24 || dayOfMonth == 25
                || dayOfMonth == 26 || dayOfMonth == 27 || dayOfMonth == 28
            ) {
                monday_no_task.text = "22"
                tuesday_no_task.text = "23"
                wednesday_no_task.text = "24"
                thursday_no_task.text = "25"
                friday_no_task.text = "26"
                saturday_no_task.text = "27"
                sunday_no_task.text = "28"
            }

            if (dayOfMonth == 29 || dayOfMonth == 30 || dayOfMonth == 31
            ) {
                monday_no_task.text = "29"
                tuesday_no_task.text = "30"
                wednesday_no_task.text = "31"
                thursday_no_task.text = "1"
                friday_no_task.text = "2"
                saturday_no_task.text = "3"
                sunday_no_task.text = "4"
            }
        }

        //October 2022
        if (month == 9 && year == 2022) {

            if (dayOfMonth == 1 || dayOfMonth == 2
            ) {
                monday_no_task.text = "26"
                tuesday_no_task.text = "27"
                wednesday_no_task.text = "28"
                thursday_no_task.text = "29"
                friday_no_task.text = "30"
                saturday_no_task.text = "1"
                sunday_no_task.text = "2"
            }

            if (dayOfMonth == 3 || dayOfMonth == 4 || dayOfMonth == 5 || dayOfMonth == 6
                || dayOfMonth == 7 || dayOfMonth == 8 || dayOfMonth == 9
            ) {
                monday_no_task.text = "3"
                tuesday_no_task.text = "4"
                wednesday_no_task.text = "5"
                thursday_no_task.text = "6"
                friday_no_task.text = "7"
                saturday_no_task.text = "8"
                sunday_no_task.text = "9"
            }

            if (dayOfMonth == 10 || dayOfMonth == 11 || dayOfMonth == 12 || dayOfMonth == 13
                || dayOfMonth == 14 || dayOfMonth == 15 || dayOfMonth == 16
            ) {
                monday_no_task.text = "10"
                tuesday_no_task.text = "11"
                wednesday_no_task.text = "12"
                thursday_no_task.text = "13"
                friday_no_task.text = "14"
                saturday_no_task.text = "15"
                sunday_no_task.text = "16"
            }

            if (dayOfMonth == 17 || dayOfMonth == 18 || dayOfMonth == 19 || dayOfMonth == 20
                || dayOfMonth == 21 || dayOfMonth == 22 || dayOfMonth == 23
            ) {
                monday_no_task.text = "17"
                tuesday_no_task.text = "18"
                wednesday_no_task.text = "19"
                thursday_no_task.text = "20"
                friday_no_task.text = "21"
                saturday_no_task.text = "22"
                sunday_no_task.text = "23"
            }

            if (dayOfMonth == 24 || dayOfMonth == 25 || dayOfMonth == 26 || dayOfMonth == 27
                || dayOfMonth == 28 || dayOfMonth == 29 || dayOfMonth == 30
            ) {
                monday_no_task.text = "24"
                tuesday_no_task.text = "25"
                wednesday_no_task.text = "26"
                thursday_no_task.text = "27"
                friday_no_task.text = "28"
                saturday_no_task.text = "29"
                sunday_no_task.text = "30"
            }

            if (dayOfMonth == 31
            ) {
                monday_no_task.text = "31"
                tuesday_no_task.text = "1"
                wednesday_no_task.text = "2"
                thursday_no_task.text = "3"
                friday_no_task.text = "4"
                saturday_no_task.text = "5"
                sunday_no_task.text = "6"
            }

        }

        //December 2022
        if (month == 11 && year == 2022) {

            if (dayOfMonth == 1 || dayOfMonth == 2 || dayOfMonth == 3 || dayOfMonth == 4
            ) {
                monday_no_task.text = "28"
                tuesday_no_task.text = "29"
                wednesday_no_task.text = "30"
                thursday_no_task.text = "1"
                friday_no_task.text = "2"
                saturday_no_task.text = "3"
                sunday_no_task.text = "4"
            }

            if (dayOfMonth == 5 || dayOfMonth == 6 || dayOfMonth == 7 || dayOfMonth == 8
                || dayOfMonth == 9 || dayOfMonth == 10 || dayOfMonth == 11
            ) {
                monday_no_task.text = "5"
                tuesday_no_task.text = "6"
                wednesday_no_task.text = "7"
                thursday_no_task.text = "8"
                friday_no_task.text = "9"
                saturday_no_task.text = "10"
                sunday_no_task.text = "11"
            }

            if (dayOfMonth == 12 || dayOfMonth == 13 || dayOfMonth == 14 || dayOfMonth == 15
                || dayOfMonth == 16 || dayOfMonth == 17 || dayOfMonth == 18
            ) {
                monday_no_task.text = "12"
                tuesday_no_task.text = "13"
                wednesday_no_task.text = "14"
                thursday_no_task.text = "15"
                friday_no_task.text = "16"
                saturday_no_task.text = "17"
                sunday_no_task.text = "18"
            }

            if (dayOfMonth == 19 || dayOfMonth == 20 || dayOfMonth == 21 || dayOfMonth == 22
                || dayOfMonth == 23 || dayOfMonth == 24 || dayOfMonth == 25
            ) {
                monday_no_task.text = "19"
                tuesday_no_task.text = "20"
                wednesday_no_task.text = "21"
                thursday_no_task.text = "22"
                friday_no_task.text = "23"
                saturday_no_task.text = "24"
                sunday_no_task.text = "25"
            }

            if (dayOfMonth == 26 || dayOfMonth == 27 || dayOfMonth == 28 || dayOfMonth == 29
                || dayOfMonth == 30 || dayOfMonth == 31
            ) {
                monday_no_task.text = "26"
                tuesday_no_task.text = "27"
                wednesday_no_task.text = "28"
                thursday_no_task.text = "29"
                friday_no_task.text = "30"
                saturday_no_task.text = "31"
                sunday_no_task.text = "1"
            }

        }

        //January 2023
        if (month == 0 && year == 2023) {

            if (dayOfMonth == 1
            ) {
                monday_no_task.text = "26"
                tuesday_no_task.text = "27"
                wednesday_no_task.text = "28"
                thursday_no_task.text = "29"
                friday_no_task.text = "30"
                saturday_no_task.text = "31"
                sunday_no_task.text = "1"
            }

            if (dayOfMonth == 2 || dayOfMonth == 3 || dayOfMonth == 4 || dayOfMonth == 5
                || dayOfMonth == 6 || dayOfMonth == 7 || dayOfMonth == 8
            ) {
                monday_no_task.text = "2"
                tuesday_no_task.text = "3"
                wednesday_no_task.text = "4"
                thursday_no_task.text = "5"
                friday_no_task.text = "6"
                saturday_no_task.text = "7"
                sunday_no_task.text = "8"
            }

            if (dayOfMonth == 9 || dayOfMonth == 10 || dayOfMonth == 11 || dayOfMonth == 12
                || dayOfMonth == 13 || dayOfMonth == 14 || dayOfMonth == 15
            ) {
                monday_no_task.text = "9"
                tuesday_no_task.text = "10"
                wednesday_no_task.text = "11"
                thursday_no_task.text = "12"
                friday_no_task.text = "13"
                saturday_no_task.text = "14"
                sunday_no_task.text = "15"
            }

            if (dayOfMonth == 16 || dayOfMonth == 17 || dayOfMonth == 18 || dayOfMonth == 19
                || dayOfMonth == 20 || dayOfMonth == 21 || dayOfMonth == 22
            ) {
                monday_no_task.text = "16"
                tuesday_no_task.text = "17"
                wednesday_no_task.text = "18"
                thursday_no_task.text = "19"
                friday_no_task.text = "20"
                saturday_no_task.text = "21"
                sunday_no_task.text = "22"
            }

            if (dayOfMonth == 23 || dayOfMonth == 24 || dayOfMonth == 25 || dayOfMonth == 26
                || dayOfMonth == 27 || dayOfMonth == 28 || dayOfMonth == 29
            ) {
                monday_no_task.text = "23"
                tuesday_no_task.text = "24"
                wednesday_no_task.text = "25"
                thursday_no_task.text = "26"
                friday_no_task.text = "27"
                saturday_no_task.text = "28"
                sunday_no_task.text = "29"
            }

            if (dayOfMonth == 30 || dayOfMonth == 31
            ) {
                monday_no_task.text = "30"
                tuesday_no_task.text = "31"
                wednesday_no_task.text = "1"
                thursday_no_task.text = "2"
                friday_no_task.text = "3"
                saturday_no_task.text = "4"
                sunday_no_task.text = "5"
            }

        }


        /**Month with 30 days**/
        //  3 April       5 June        8 September   10 November

        //November 2021
        if (month == 10) {

            if (dayOfMonth == 1 || dayOfMonth == 2 || dayOfMonth == 3 || dayOfMonth == 4
                || dayOfMonth == 5 || dayOfMonth == 6 || dayOfMonth == 7
            ) {
                monday_no_task.text = "1"
                tuesday_no_task.text = "2"
                wednesday_no_task.text = "3"
                thursday_no_task.text = "4"
                friday_no_task.text = "5"
                saturday_no_task.text = "6"
                sunday_no_task.text = "7"
            }

            if (dayOfMonth == 8 || dayOfMonth == 9 || dayOfMonth == 10 || dayOfMonth == 11
                || dayOfMonth == 12 || dayOfMonth == 13 || dayOfMonth == 14
            ) {
                monday_no_task.text = "8"
                tuesday_no_task.text = "9"
                wednesday_no_task.text = "10"
                thursday_no_task.text = "11"
                friday_no_task.text = "12"
                saturday_no_task.text = "13"
                sunday_no_task.text = "14"
            }

            if (dayOfMonth == 15 || dayOfMonth == 16 || dayOfMonth == 17 || dayOfMonth == 18
                || dayOfMonth == 19 || dayOfMonth == 20 || dayOfMonth == 21
            ) {
                monday_no_task.text = "15"
                tuesday_no_task.text = "16"
                wednesday_no_task.text = "17"
                thursday_no_task.text = "18"
                friday_no_task.text = "19"
                saturday_no_task.text = "20"
                sunday_no_task.text = "21"
            }

            if (dayOfMonth == 22 || dayOfMonth == 23 || dayOfMonth == 24 || dayOfMonth == 25
                || dayOfMonth == 26 || dayOfMonth == 27 || dayOfMonth == 28
            ) {
                monday_no_task.text = "22"
                tuesday_no_task.text = "23"
                wednesday_no_task.text = "24"
                thursday_no_task.text = "25"
                friday_no_task.text = "26"
                saturday_no_task.text = "27"
                sunday_no_task.text = "28"
            }

            if (dayOfMonth == 29 || dayOfMonth == 30
            ) {
                monday_no_task.text = "29"
                tuesday_no_task.text = "30"
                wednesday_no_task.text = "1"
                thursday_no_task.text = "2"
                friday_no_task.text = "3"
                saturday_no_task.text = "4"
                sunday_no_task.text = "5"
            }
        }

        /**Month with 28 days 2022 **/
        //1 February 2022
        if (month == 1) {

            if (dayOfMonth == 1 || dayOfMonth == 2 || dayOfMonth == 3 || dayOfMonth == 4
                || dayOfMonth == 5 || dayOfMonth == 6
            ) {
                monday_no_task.text = "31"
                tuesday_no_task.text = "1"
                wednesday_no_task.text = "2"
                thursday_no_task.text = "3"
                friday_no_task.text = "4"
                saturday_no_task.text = "5"
                sunday_no_task.text = "6"
            }

            if (dayOfMonth == 7 || dayOfMonth == 8 || dayOfMonth == 9 || dayOfMonth == 10
                || dayOfMonth == 11 || dayOfMonth == 12 || dayOfMonth == 13
            ) {

                monday_no_task.text = "7"
                tuesday_no_task.text = "8"
                wednesday_no_task.text = "9"
                thursday_no_task.text = "10"
                friday_no_task.text = "11"
                saturday_no_task.text = "12"
                sunday_no_task.text = "13"
            }

            if (dayOfMonth == 14 || dayOfMonth == 15 || dayOfMonth == 16 || dayOfMonth == 17
                || dayOfMonth == 18 || dayOfMonth == 19 || dayOfMonth == 20
            ) {
                monday_no_task.text = "14"
                tuesday_no_task.text = "15"
                wednesday_no_task.text = "16"
                thursday_no_task.text = "17"
                friday_no_task.text = "18"
                saturday_no_task.text = "19"
                sunday_no_task.text = "20"
            }

            if (dayOfMonth == 21 || dayOfMonth == 22 || dayOfMonth == 23 || dayOfMonth == 24
                || dayOfMonth == 25 || dayOfMonth == 26 || dayOfMonth == 27
            ) {
                monday_no_task.text = "21"
                tuesday_no_task.text = "22"
                wednesday_no_task.text = "23"
                thursday_no_task.text = "24"
                friday_no_task.text = "25"
                saturday_no_task.text = "26"
                sunday_no_task.text = "27"
            }

            if (dayOfMonth == 28
            ) {
                monday_no_task.text = "28"
                tuesday_no_task.text = "1"
                wednesday_no_task.text = "2"
                thursday_no_task.text = "3"
                friday_no_task.text = "4"
                saturday_no_task.text = "5"
                sunday_no_task.text = "6"
            }
        }

        //April 2022
        if (month == 3) {

            if (dayOfMonth == 1 || dayOfMonth == 2 || dayOfMonth == 3
            ) {
                monday_no_task.text = "28"
                tuesday_no_task.text = "29"
                wednesday_no_task.text = "30"
                thursday_no_task.text = "31"
                friday_no_task.text = "1"
                saturday_no_task.text = "2"
                sunday_no_task.text = "3"
            }

            if (dayOfMonth == 4 || dayOfMonth == 5 || dayOfMonth == 6 || dayOfMonth == 7
                || dayOfMonth == 8 || dayOfMonth == 9 || dayOfMonth == 10
            ) {
                monday_no_task.text = "4"
                tuesday_no_task.text = "5"
                wednesday_no_task.text = "6"
                thursday_no_task.text = "7"
                friday_no_task.text = "8"
                saturday_no_task.text = "9"
                sunday_no_task.text = "10"
            }

            if (dayOfMonth == 11 || dayOfMonth == 12 || dayOfMonth == 13 || dayOfMonth == 14
                || dayOfMonth == 15 || dayOfMonth == 16 || dayOfMonth == 17
            ) {
                monday_no_task.text = "11"
                tuesday_no_task.text = "12"
                wednesday_no_task.text = "13"
                thursday_no_task.text = "14"
                friday_no_task.text = "15"
                saturday_no_task.text = "16"
                sunday_no_task.text = "17"
            }

            if (dayOfMonth == 18 || dayOfMonth == 19 || dayOfMonth == 20 || dayOfMonth == 21
                || dayOfMonth == 22 || dayOfMonth == 23 || dayOfMonth == 24
            ) {
                monday_no_task.text = "18"
                tuesday_no_task.text = "19"
                wednesday_no_task.text = "20"
                thursday_no_task.text = "21"
                friday_no_task.text = "22"
                saturday_no_task.text = "23"
                sunday_no_task.text = "24"
            }

            if (dayOfMonth == 25 || dayOfMonth == 26 || dayOfMonth == 27 || dayOfMonth == 28
                || dayOfMonth == 29 || dayOfMonth == 30
            ) {
                monday_no_task.text = "25"
                tuesday_no_task.text = "26"
                wednesday_no_task.text = "27"
                thursday_no_task.text = "28"
                friday_no_task.text = "29"
                saturday_no_task.text = "30"
                sunday_no_task.text = "1"
            }
        }

        //June 2022
        if (month == 5) {

            if (dayOfMonth == 1 || dayOfMonth == 2 || dayOfMonth == 3 || dayOfMonth == 4
                || dayOfMonth == 5
            ) {
                monday_no_task.text = "30"
                tuesday_no_task.text = "31"
                wednesday_no_task.text = "1"
                thursday_no_task.text = "2"
                friday_no_task.text = "3"
                saturday_no_task.text = "4"
                sunday_no_task.text = "5"
            }

            if (dayOfMonth == 6 || dayOfMonth == 7 || dayOfMonth == 8 || dayOfMonth == 9
                || dayOfMonth == 10 || dayOfMonth == 11 || dayOfMonth == 12
            ) {
                monday_no_task.text = "6"
                tuesday_no_task.text = "7"
                wednesday_no_task.text = "8"
                thursday_no_task.text = "9"
                friday_no_task.text = "10"
                saturday_no_task.text = "11"
                sunday_no_task.text = "12"
            }

            if (dayOfMonth == 13 || dayOfMonth == 14 || dayOfMonth == 15 || dayOfMonth == 16
                || dayOfMonth == 17 || dayOfMonth == 18 || dayOfMonth == 19
            ) {
                monday_no_task.text = "13"
                tuesday_no_task.text = "14"
                wednesday_no_task.text = "15"
                thursday_no_task.text = "16"
                friday_no_task.text = "17"
                saturday_no_task.text = "18"
                sunday_no_task.text = "19"
            }

            if (dayOfMonth == 20 || dayOfMonth == 21 || dayOfMonth == 22 || dayOfMonth == 23
                || dayOfMonth == 24 || dayOfMonth == 25 || dayOfMonth == 26
            ) {
                monday_no_task.text = "20"
                tuesday_no_task.text = "21"
                wednesday_no_task.text = "22"
                thursday_no_task.text = "23"
                friday_no_task.text = "24"
                saturday_no_task.text = "25"
                sunday_no_task.text = "26"
            }

            if (dayOfMonth == 27 || dayOfMonth == 28 || dayOfMonth == 29 || dayOfMonth == 30
            ) {
                monday_no_task.text = "27"
                tuesday_no_task.text = "28"
                wednesday_no_task.text = "29"
                thursday_no_task.text = "30"
                friday_no_task.text = "1"
                saturday_no_task.text = "2"
                sunday_no_task.text = "3"
            }
        }

        //September 2022
        if (month == 8) {

            if (dayOfMonth == 1 || dayOfMonth == 2 || dayOfMonth == 3 || dayOfMonth == 4
            ) {
                monday_no_task.text = "29"
                tuesday_no_task.text = "30"
                wednesday_no_task.text = "31"
                thursday_no_task.text = "1"
                friday_no_task.text = "2"
                saturday_no_task.text = "3"
                sunday_no_task.text = "4"
            }

            if (dayOfMonth == 5 || dayOfMonth == 6 || dayOfMonth == 7 || dayOfMonth == 8
                || dayOfMonth == 9 || dayOfMonth == 10 || dayOfMonth == 11
            ) {
                monday_no_task.text = "5"
                tuesday_no_task.text = "6"
                wednesday_no_task.text = "7"
                thursday_no_task.text = "8"
                friday_no_task.text = "9"
                saturday_no_task.text = "10"
                sunday_no_task.text = "11"
            }

            if (dayOfMonth == 12 || dayOfMonth == 13 || dayOfMonth == 14 || dayOfMonth == 15
                || dayOfMonth == 16 || dayOfMonth == 17 || dayOfMonth == 18
            ) {
                monday_no_task.text = "12"
                tuesday_no_task.text = "13"
                wednesday_no_task.text = "14"
                thursday_no_task.text = "15"
                friday_no_task.text = "16"
                saturday_no_task.text = "17"
                sunday_no_task.text = "18"
            }

            if (dayOfMonth == 19 || dayOfMonth == 20 || dayOfMonth == 21 || dayOfMonth == 22
                || dayOfMonth == 23 || dayOfMonth == 24 || dayOfMonth == 25
            ) {
                monday_no_task.text = "19"
                tuesday_no_task.text = "20"
                wednesday_no_task.text = "21"
                thursday_no_task.text = "22"
                friday_no_task.text = "23"
                saturday_no_task.text = "24"
                sunday_no_task.text = "25"
            }

            if (dayOfMonth == 26 || dayOfMonth == 27 || dayOfMonth == 28 || dayOfMonth == 29
                || dayOfMonth == 30
            ) {
                monday_no_task.text = "26"
                tuesday_no_task.text = "27"
                wednesday_no_task.text = "28"
                thursday_no_task.text = "29"
                friday_no_task.text = "30"
                saturday_no_task.text = "1"
                sunday_no_task.text = "2"
            }
        }

        //November 2022
        if (month == 10 && year == 2022) {

            if (dayOfMonth == 1 || dayOfMonth == 2 || dayOfMonth == 3 || dayOfMonth == 4
                || dayOfMonth == 5 || dayOfMonth == 6
            ) {
                monday_no_task.text = "31"
                tuesday_no_task.text = "1"
                wednesday_no_task.text = "2"
                thursday_no_task.text = "3"
                friday_no_task.text = "4"
                saturday_no_task.text = "5"
                sunday_no_task.text = "6"
            }

            if (dayOfMonth == 7 || dayOfMonth == 8 || dayOfMonth == 9 || dayOfMonth == 10
                || dayOfMonth == 11 || dayOfMonth == 12 || dayOfMonth == 13
            ) {
                monday_no_task.text = "7"
                tuesday_no_task.text = "8"
                wednesday_no_task.text = "9"
                thursday_no_task.text = "10"
                friday_no_task.text = "11"
                saturday_no_task.text = "12"
                sunday_no_task.text = "13"
            }

            if (dayOfMonth == 14 || dayOfMonth == 15 || dayOfMonth == 16 || dayOfMonth == 17
                || dayOfMonth == 18 || dayOfMonth == 19 || dayOfMonth == 20
            ) {
                monday_no_task.text = "14"
                tuesday_no_task.text = "15"
                wednesday_no_task.text = "16"
                thursday_no_task.text = "17"
                friday_no_task.text = "18"
                saturday_no_task.text = "19"
                sunday_no_task.text = "20"
            }

            if (dayOfMonth == 21 || dayOfMonth == 22 || dayOfMonth == 23 || dayOfMonth == 24
                || dayOfMonth == 25 || dayOfMonth == 26 || dayOfMonth == 27
            ) {
                monday_no_task.text = "21"
                tuesday_no_task.text = "22"
                wednesday_no_task.text = "23"
                thursday_no_task.text = "24"
                friday_no_task.text = "25"
                saturday_no_task.text = "26"
                sunday_no_task.text = "27"
            }

            if (dayOfMonth == 28 || dayOfMonth == 29 || dayOfMonth == 30
            ) {
                monday_no_task.text = "28"
                tuesday_no_task.text = "29"
                wednesday_no_task.text = "30"
                thursday_no_task.text = "1"
                friday_no_task.text = "2"
                saturday_no_task.text = "3"
                sunday_no_task.text = "4"
            }

        }

    }

    private var listener: SwitchOnAddFragment? = null
    interface SwitchOnAddFragment {
        fun switchOnAddFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as SwitchOnAddFragment
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}