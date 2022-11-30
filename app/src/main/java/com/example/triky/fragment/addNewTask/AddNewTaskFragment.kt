package com.example.triky.fragment.addNewTask

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.DatePicker
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.triky.R
import com.example.triky.data.User
import com.example.triky.databinding.AddNewTaskBinding
import com.example.triky.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.add_new_task.*
import java.util.*

class AddNewTaskFragment : Fragment(), DatePickerDialog.OnDateSetListener {

    private var _binding: AddNewTaskBinding? = null
    private val binding get() = _binding!!
    private lateinit var mUserViewModel: UserViewModel

    private var colorNumber = 10


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AddNewTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        setListener()
        openKeyboard()
        backToHomeScreen()
        cancelBtn()
        chooseColor()
        pickDate()
        btnColorIfNotEmpty()
    }

    private fun setListener() {
        save_Btn.setOnClickListener {
            insertDataToDatabase()
        }
    }

    private fun init() {
        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]
    }


    private fun openKeyboard() {
        /** კლავიატურის ავტომატურად ამოწევა  **/
        val i = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        i.showSoftInput(Task_input,0)
    }

    /** Choose Color **/
    private fun chooseColor() {

        //Red
        oval_red.setOnClickListener {
            colorNumber = 0
            red_circle.isVisible = true
            orange_circle.isVisible = false
            yellow_circle.isVisible = false
            green_circle.isVisible = false
            lightBlue_circle.isVisible = false
            blue_circle.isVisible = false
            purple_circle.isVisible = false
            light_purple_circle.isVisible = false
            pink_circle.isVisible = false
        }


        //Orange
        oval_orange.setOnClickListener {
            colorNumber = 1
            red_circle.isVisible = false
            orange_circle.isVisible = true
            yellow_circle.isVisible = false
            green_circle.isVisible = false
            lightBlue_circle.isVisible = false
            blue_circle.isVisible = false
            purple_circle.isVisible = false
            light_purple_circle.isVisible = false
            pink_circle.isVisible = false
        }


        //Yellow
        oval_yellow.setOnClickListener {
            colorNumber = 2
            red_circle.isVisible = false
            orange_circle.isVisible = false
            yellow_circle.isVisible = true
            green_circle.isVisible = false
            lightBlue_circle.isVisible = false
            blue_circle.isVisible = false
            purple_circle.isVisible = false
            light_purple_circle.isVisible = false
            pink_circle.isVisible = false
        }


        //Green
        oval_green.setOnClickListener {
            colorNumber = 3
            red_circle.isVisible = false
            orange_circle.isVisible = false
            yellow_circle.isVisible = false
            green_circle.isVisible = true
            lightBlue_circle.isVisible = false
            blue_circle.isVisible = false
            purple_circle.isVisible = false
            light_purple_circle.isVisible = false
            pink_circle.isVisible = false
        }


        //Light_Blue
        oval_light_Blue.setOnClickListener {
            colorNumber = 4
            red_circle.isVisible = false
            orange_circle.isVisible = false
            yellow_circle.isVisible = false
            green_circle.isVisible = false
            lightBlue_circle.isVisible = true
            blue_circle.isVisible = false
            purple_circle.isVisible = false
            light_purple_circle.isVisible = false
            pink_circle.isVisible = false
        }


        //Blue
        oval_blue.setOnClickListener {
            colorNumber = 5

            red_circle.isVisible = false
            orange_circle.isVisible = false
            yellow_circle.isVisible = false
            green_circle.isVisible = false
            lightBlue_circle.isVisible = false
            blue_circle.isVisible = true
            purple_circle.isVisible = false
            light_purple_circle.isVisible = false
            pink_circle.isVisible = false
        }


        //Purple
        oval_purple.setOnClickListener {
            colorNumber = 6
            red_circle.isVisible = false
            orange_circle.isVisible = false
            yellow_circle.isVisible = false
            green_circle.isVisible = false
            lightBlue_circle.isVisible = false
            blue_circle.isVisible = false
            purple_circle.isVisible = true
            light_purple_circle.isVisible = false
            pink_circle.isVisible = false
        }


        //Light_Purple
        oval_light_purple.setOnClickListener {
            colorNumber = 7
            red_circle.isVisible = false
            orange_circle.isVisible = false
            yellow_circle.isVisible = false
            green_circle.isVisible = false
            lightBlue_circle.isVisible = false
            blue_circle.isVisible = false
            purple_circle.isVisible = false
            light_purple_circle.isVisible = true
            pink_circle.isVisible = false
        }


        //Pink
        oval_pink.setOnClickListener {
            colorNumber = 8
            red_circle.isVisible = false
            orange_circle.isVisible = false
            yellow_circle.isVisible = false
            green_circle.isVisible = false
            lightBlue_circle.isVisible = false
            blue_circle.isVisible = false
            purple_circle.isVisible = false
            light_purple_circle.isVisible = false
            pink_circle.isVisible = true
        }

    }


    private fun insertDataToDatabase() {
        val taskText = Task_input.text.toString()
        val dataText = Tv_some.text.toString()
        if (inputCheck(taskText)) {
            val user = User(0, taskText, checked = false, selected = false,colorNumber , dataText)
            /** add Data to Database **/
            mUserViewModel.addUserVm(user)
            /** Back to HomeScreenFragment **/
            listener?.switchHomeScreenFragment()
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }
    }
    private fun inputCheck(text: String): Boolean {
        return !(TextUtils.isEmpty(text))
    }

    /**  Back to HomeScreenFragment with BACK button **/
    private fun backToHomeScreen() {
        back.setOnClickListener {
            listener?.switchHomeScreenFragment()
        }
    }
    /**  Back to HomeScreenFragment with Cancel button **/
    private fun cancelBtn() {
        cancel_tv.setOnClickListener {
            listener?.switchHomeScreenFragment()
        }
    }



    /** Save Button Color **/
    private fun btnColorIfNotEmpty() {

        Task_input.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.trim().isNotEmpty()) {
                    save_Btn.setBackgroundResource(R.drawable.disabled_save_button)
                } else {
                    save_Btn.setBackgroundResource(R.drawable.save_grey_btn)
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                save_Btn.setBackgroundResource(R.drawable.save_grey_btn)
            }

            override fun afterTextChanged(s: Editable) {}
        })
    }


    /** Below Calendar Dialog stuff **/
    var day = 0
    var month = 0
    var year = 0
//    var hour = 0
//    var minute = 0

    var saveDay = 0
    var saveMonth = 0
    var saveYear = 0


    private fun getDateTimeCalendar() {
        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
//        hour = cal.get(Calendar.HOUR)
//        minute = cal.get(Calendar.MINUTE)
    }

    private fun pickDate() {
        calendar.setOnClickListener {
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
            0 -> { Tv_some.text = ("$saveDay Jan") }
            1 -> { Tv_some.text = ("$saveDay Feb") }
            2 -> { Tv_some.text = ("$saveDay Mar") }
            3 -> { Tv_some.text = ("$saveDay Apr") }
            4 -> { Tv_some.text = ("$saveDay May") }
            5 -> { Tv_some.text = ("$saveDay Jun") }
            6 -> { Tv_some.text = ("$saveDay Jul") }
            7 -> { Tv_some.text = ("$saveDay Aug") }
            8 -> { Tv_some.text = ("$saveDay Sep") }
            9 -> { Tv_some.text = ("$saveDay Oct") }
            10 -> { Tv_some.text = ("$saveDay Nov") }
            11 -> { Tv_some.text = ("$saveDay Dec") }
        }
    }

    private var listener : SwitchHomeScreenFragment? = null
    interface SwitchHomeScreenFragment {
        fun switchHomeScreenFragment ()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as SwitchHomeScreenFragment
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