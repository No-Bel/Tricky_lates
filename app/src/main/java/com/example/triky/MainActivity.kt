package com.example.triky

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.triky.fragment.addNewTask.AddNewTaskFragment
import com.example.triky.fragment.emptyHomeScreen.NoTaskHomeScreenFragment
import com.example.triky.fragment.homeScreen.HomeScreenFragment
import com.example.triky.fragment.onboarding.OnboardingFragment
import com.example.triky.fragment.updateTask.UpdateTaskFragment
import com.example.triky.SharedPreferences.Preference
import com.example.triky.data.User
import com.example.triky.viewmodel.UserViewModel

class MainActivity : AppCompatActivity(), OnboardingFragment.ButtonClicked,
    NoTaskHomeScreenFragment.SwitchOnAddFragment, HomeScreenFragment.HomeSwitchOnAddFragment,
    AddNewTaskFragment.SwitchHomeScreenFragment, UpdateTaskFragment.BackToHomeScreen {

    private lateinit var sharePref: Preference
    private lateinit var mUserViewModel: UserViewModel
    private lateinit var homeScreenFragment: HomeScreenFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharePref = Preference(this)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        homeScreenFragment = HomeScreenFragment(EditTask = {user -> updateTask(user)})

        // onBoarding || firstScreen
        appStart()

    }

    //onBoarding || noTaskHomeScreen || homeScreen
    private fun appStart() {
        if (sharePref.getWhenAppFirstTimeOpen()) {
            mUserViewModel.readAllData.observe(this, Observer {
                if (it.isEmpty()) {
                    noTaskHomeScreen()
                }
            })
            mUserViewModel.readAllData.observe(this, Observer {
                if (it.isNotEmpty()) {
                    homeScreen()
                }
            })
        } else {
            onboarding()
        }
    }



    private fun homeScreen() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, homeScreenFragment)
            .commit()
    }

    private fun noTaskHomeScreen() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, NoTaskHomeScreenFragment())
            .commit()
    }

    private fun onboarding() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, OnboardingFragment())
            .commit()
    }

    override fun buttonClicked() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, homeScreenFragment)
            .commit()
        sharePref.saveWhenAppFirstTimeOpen(true)
    }


    override fun switchOnAddFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, AddNewTaskFragment())
            .commit()
    }

    override fun homeSwitchOnAddFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, AddNewTaskFragment())
            .commit()
    }

    override fun switchHomeScreenFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, homeScreenFragment)
            .commit()
    }

    private fun updateTask(user: User) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, UpdateTaskFragment(user))
            .commit()
    }


    override fun backToHomeScreen() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, homeScreenFragment)
            .commit()
    }
}