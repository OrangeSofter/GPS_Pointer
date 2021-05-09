package ru.upsoft.gpspointer.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.upsoft.gpspointer.R
import ru.upsoft.gpspointer.databinding.ActivityMainBinding
import ru.upsoft.gpspointer.presentation.ui.features.mainFunctionality.MainFunctionalityFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewBinding: ActivityMainBinding by viewBinding(ActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add(R.id.fragmentContainer, MainFunctionalityFragment(), "mainFuncFrag")
            }
        }
    }

}