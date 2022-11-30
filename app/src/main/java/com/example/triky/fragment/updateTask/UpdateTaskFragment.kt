package com.example.triky.fragment.updateTask

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.DatePicker
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.triky.R
import com.example.triky.data.User
import com.example.triky.data.UserDao
import com.example.triky.data.UserDatabase
import com.example.triky.databinding.FragmentUpdateTaskBinding
import com.example.triky.repository.UserRepository
import com.example.triky.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.add_new_task.*
import kotlinx.android.synthetic.main.fragment_update_task.*
import kotlinx.android.synthetic.main.fragment_update_task.view.*
import java.util.*

class UpdateTaskFragment(user: User) : Fragment(), DatePickerDialog.OnDateSetListener {

    private var _binding: FragmentUpdateTaskBinding? = null
    private val binding get() = _binding!!

    private var updateText = user.textInfo
    private var colorNumber = user.color
    private var updateDate = user.date

    private var userList: User = user
    private lateinit var mUserViewModel: UserViewModel
    private lateinit var userDao: UserDao
    private lateinit var userDatabase: UserDatabase
    private lateinit var userRepository: UserRepository


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        setListener()
        btnColorIfNotEmpty()
        openKeyboard()
        chooseColor()
        circleColor()
        backToHomeScreen()
        cancelBtn()
        pickDate()

    }

    private fun setListener() {
        update_Btn.setOnClickListener {
            updateTask()
        }
    }

    private fun init() {
        userDatabase = UserDatabase.getDatabase(requireContext())
        userDao = userDatabase.userDao()
        userRepository = UserRepository(userDao)
        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        update_task_input.setText(updateText)
        update_Tv_some.text = updateDate
    }

    private fun openKeyboard() {
        /** კლავიატურის ავტომატურად ამოწევა  **/
        val i = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        i.showSoftInput(update_task_input,0)
    }

    private fun updateTask() {
        val title = update_task_input.text.toString()
        val checked = false
        val selected = false
        val color = colorNumber
        val date = update_Tv_some.text.toString()


        if (inputCheck(title)) {
            val newTask = User(userList.id, title, checked, selected , color, date)
            mUserViewModel.updateCurrentItemVm(newTask)

            Log.d("nn", "$newTask")
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }

//        val editTask = User(userList.id, title, checked, selected, color, date)
//        mUserViewModel.updateUserVm(editTask)
    }

    private fun inputCheck(text: String): Boolean {
        return !(TextUtils.isEmpty(text))
    }

    private fun circleColor() {
        when(colorNumber) {
            0 -> {update_red_circle.isVisible = true}
            1 -> {update_orange_circle.isVisible = true}
            2 -> {update_yellow_circle.isVisible = true}
            3 -> {update_green_circle.isVisible = true}
            4 -> {update_lightBlue_circle.isVisible = true}
            5 -> {update_blue_circle.isVisible = true}
            6 -> {update_purple_circle.isVisible = true}
            7 -> {update_light_purple_circle.isVisible = true}
            8 -> {update_pink_circle.isVisible = true}
        }
    }

    /**  Back to HomeScreenFragment with BACK button **/
    private fun backToHomeScreen() {
        update_back.setOnClickListener {
            listenerBackHomeScreen?.backToHomeScreen()
        }
    }

    /**  Back to HomeScreenFragment with Cancel button **/
    private fun cancelBtn() {
        update_cancel_tv.setOnClickListener {
            listenerBackHomeScreen?.backToHomeScreen()
        }
    }


    var listenerBackHomeScreen: BackToHomeScreen? = null
    interface BackToHomeScreen {
        fun backToHomeScreen()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listenerBackHomeScreen = context as BackToHomeScreen
    }

    override fun onDestroy() {
        super.onDestroy()
        listenerBackHomeScreen = null
    }


    /** Save Button Color
     *  თუ ველი ცარიელია update_Btn ფერი არის save_grey_btn
     *  თუ ველი შევსებულია update_Btn ფერი არის disabled_save_button **/
    private fun btnColorIfNotEmpty() {

        update_task_input.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.trim().isNotEmpty()) {
                    update_Btn.setBackgroundResource(R.drawable.disabled_save_button)
                } else {
                    update_Btn.setBackgroundResource(R.drawable.save_grey_btn)
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                update_Btn.setBackgroundResource(R.drawable.save_grey_btn)
            }

            override fun afterTextChanged(s: Editable) {}
        })
    }

    /** Below Calendar Dialog stuff **/
    var day = 0
    var month = 0
    var year = 0

    var saveDay = 0
    var saveMonth = 0
    var saveYear = 0


    private fun getDateTimeCalendar() {
        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
    }

    private fun pickDate() {
        update_calendar.setOnClickListener {
            getDateTimeCalendar()
            DatePickerDialog(requireContext(), this, year, month, day).show()
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        saveDay = dayOfMonth
        saveMonth = month
        saveYear = year
        getDateTimeCalendar()
        when (saveMonth) {
            0 ->  { update_Tv_some.text = ("$saveDay Jan") }
            1 ->  { update_Tv_some.text = ("$saveDay Feb") }
            2 ->  { update_Tv_some.text = ("$saveDay Mar") }
            3 ->  { update_Tv_some.text = ("$saveDay Apr") }
            4 ->  { update_Tv_some.text = ("$saveDay May") }
            5 ->  { update_Tv_some.text = ("$saveDay Jun") }
            6 ->  { update_Tv_some.text = ("$saveDay Jul") }
            7 ->  { update_Tv_some.text = ("$saveDay Aug") }
            8 ->  { update_Tv_some.text = ("$saveDay Sep") }
            9 ->  { update_Tv_some.text = ("$saveDay Oct") }
            10 -> { update_Tv_some.text = ("$saveDay Nov") }
            11 -> { update_Tv_some.text = ("$saveDay Dec") }
        }
    }

    /** Choose Color **/
    private fun chooseColor() {

        //Red
        update_oval_red.setOnClickListener {
            colorNumber = 0
            update_red_circle.isVisible = true
            update_orange_circle.isVisible = false
            update_yellow_circle.isVisible = false
            update_green_circle.isVisible = false
            update_lightBlue_circle.isVisible = false
            update_blue_circle.isVisible = false
            update_purple_circle.isVisible = false
            update_light_purple_circle.isVisible = false
            update_pink_circle.isVisible = false
        }


        //Orange
        update_oval_orange.setOnClickListener {
            colorNumber = 1
            update_red_circle.isVisible = false
            update_orange_circle.isVisible = true
            update_yellow_circle.isVisible = false
            update_green_circle.isVisible = false
            update_lightBlue_circle.isVisible = false
            update_blue_circle.isVisible = false
            update_purple_circle.isVisible = false
            update_light_purple_circle.isVisible = false
            update_pink_circle.isVisible = false
        }


        //Yellow
        update_oval_yellow.setOnClickListener {
            colorNumber = 2
            update_red_circle.isVisible = false
            update_orange_circle.isVisible = false
            update_yellow_circle.isVisible = true
            update_green_circle.isVisible = false
            update_lightBlue_circle.isVisible = false
            update_blue_circle.isVisible = false
            update_purple_circle.isVisible = false
            update_light_purple_circle.isVisible = false
            update_pink_circle.isVisible = false
        }


        //Green
        update_oval_green.setOnClickListener {
            colorNumber = 3
            update_red_circle.isVisible = false
            update_orange_circle.isVisible = false
            update_yellow_circle.isVisible = false
            update_green_circle.isVisible = true
            update_lightBlue_circle.isVisible = false
            update_blue_circle.isVisible = false
            update_purple_circle.isVisible = false
            update_light_purple_circle.isVisible = false
            update_pink_circle.isVisible = false
        }


        //Light_Blue
        update_oval_light_Blue.setOnClickListener {
            colorNumber = 4
            update_red_circle.isVisible = false
            update_orange_circle.isVisible = false
            update_yellow_circle.isVisible = false
            update_green_circle.isVisible = false
            update_lightBlue_circle.isVisible = true
            update_blue_circle.isVisible = false
            update_purple_circle.isVisible = false
            update_light_purple_circle.isVisible = false
            update_pink_circle.isVisible = false
        }


        //Blue
        update_oval_blue.setOnClickListener {
            colorNumber = 5

            update_red_circle.isVisible = false
            update_orange_circle.isVisible = false
            update_yellow_circle.isVisible = false
            update_green_circle.isVisible = false
            update_lightBlue_circle.isVisible = false
            update_blue_circle.isVisible = true
            update_purple_circle.isVisible = false
            update_light_purple_circle.isVisible = false
            update_pink_circle.isVisible = false
        }


        //Purple
        update_oval_purple.setOnClickListener {
            colorNumber = 6
            update_red_circle.isVisible = false
            update_orange_circle.isVisible = false
            update_yellow_circle.isVisible = false
            update_green_circle.isVisible = false
            update_lightBlue_circle.isVisible = false
            update_blue_circle.isVisible = false
            update_purple_circle.isVisible = true
            update_light_purple_circle.isVisible = false
            update_pink_circle.isVisible = false
        }


        //Light_Purple
        update_oval_light_purple.setOnClickListener {
            colorNumber = 7
            update_red_circle.isVisible = false
            update_orange_circle.isVisible = false
            update_yellow_circle.isVisible = false
            update_green_circle.isVisible = false
            update_lightBlue_circle.isVisible = false
            update_blue_circle.isVisible = false
            update_purple_circle.isVisible = false
            update_light_purple_circle.isVisible = true
            update_pink_circle.isVisible = false
        }


        //Pink
        update_oval_pink.setOnClickListener {
            colorNumber = 8
            update_red_circle.isVisible = false
            update_orange_circle.isVisible = false
            update_yellow_circle.isVisible = false
            update_green_circle.isVisible = false
            update_lightBlue_circle.isVisible = false
            update_blue_circle.isVisible = false
            update_purple_circle.isVisible = false
            update_light_purple_circle.isVisible = false
            update_pink_circle.isVisible = true
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}