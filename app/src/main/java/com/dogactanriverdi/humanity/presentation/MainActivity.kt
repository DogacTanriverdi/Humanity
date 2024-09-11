package com.dogactanriverdi.humanity.presentation

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.size
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import com.dogactanriverdi.humanity.R
import com.dogactanriverdi.humanity.common.viewBinding
import com.dogactanriverdi.humanity.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    private val viewModel: SharedViewModel by viewModels()

    private lateinit var bottomNavView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment

        val navController = navHostFragment.navController

        val navOptions = NavOptions.Builder()
            .setLaunchSingleTop(true)
            .setPopUpTo(
                navController.graph.findStartDestination().id,
                false,
                saveState = true
            )
            .setRestoreState(true)
            .build()

        with(binding) {

            bottomNavigationView.setOnItemSelectedListener {
                navController.navigate(it.itemId, null, navOptions)
                true
            }

            navController.addOnDestinationChangedListener { _, destination, _ ->
                bottomNavigationView.menu.findItem(destination.id)?.isChecked = true
            }

            bottomNavView = bottomNavigationView

            bottomNavView.menu.add(
                Menu.NONE,
                R.id.homeFragment,
                Menu.NONE,
                "Home"
            ).setIcon(R.drawable.ic_home)

            with(viewModel) {

                lifecycleScope.launch {
                    egoSwitch.collect { egoSwitchState ->
                        if (egoSwitchState.isChecked) {
                            bottomNavigationView.visibility = View.GONE
                        } else {
                            bottomNavigationView.visibility = View.VISIBLE
                        }
                    }
                }

                lifecycleScope.launch {
                    otherSwitchesStates.collect { otherSwitchesStates ->
                        updateBottomNavigation(otherSwitchesStates.isOtherSwitchesChecked)
                    }
                }
            }
        }
    }

    private fun updateBottomNavigation(switchStates: List<Boolean>) {
        val menu = bottomNavView.menu

        updateMenuItem(
            menu,
            R.id.givingFragment,
            switchStates[0],
            "Giving",
            R.drawable.ic_favorite
        )

        updateMenuItem(
            menu,
            R.id.happinessFragment,
            switchStates[1],
            "Happiness",
            R.drawable.ic_happy_face
        )
        updateMenuItem(
            menu,
            R.id.kindnessFragment,
            switchStates[2],
            "Kindness",
            R.drawable.ic_flower
        )
        updateMenuItem(
            menu,
            R.id.optimismFragment,
            switchStates[3],
            "Optimism",
            R.drawable.ic_brightness
        )
        updateMenuItem(
            menu,
            R.id.respectFragment,
            switchStates[4],
            "Respect",
            R.drawable.ic_handshake
        )
    }

    private fun updateMenuItem(
        menu: Menu,
        itemId: Int,
        isChecked: Boolean,
        title: String,
        iconId: Int
    ) {
        val menuItem = menu.findItem(itemId)
        if (isChecked) {
            if (menuItem == null) {
                if (menu.size < 5) {
                    menu.add(Menu.NONE, itemId, Menu.NONE, title).setIcon(iconId)
                } else {
                    viewModel.toggleOtherSwitches(getSwitchIndexById(itemId), false)
                }
            }
        } else {
            menuItem?.let {
                menu.removeItem(itemId)
            }
        }
    }

    private fun getSwitchIndexById(itemId: Int): Int {
        return when (itemId) {
            R.id.givingFragment -> 0
            R.id.happinessFragment -> 1
            R.id.kindnessFragment -> 2
            R.id.optimismFragment -> 3
            R.id.respectFragment -> 4
            else -> -1
        }
    }
}