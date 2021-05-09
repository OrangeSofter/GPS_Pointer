package ru.upsoft.gpspointer.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.upsoft.gpspointer.R
import ru.upsoft.gpspointer.databinding.FragmentMainFunctionalityBinding

class MainFunctionalityFragment : Fragment(R.layout.fragment_main_functionality) {

    private val viewBinding: FragmentMainFunctionalityBinding by viewBinding(FragmentMainFunctionalityBinding::bind)

    override fun onStart() {
        super.onStart()
        viewBinding.tw.text = "Изменен"
    }

}