package ru.upsoft.gpspointer.presentation.ui.features.mainFunctionality

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.upsoft.gpspointer.R
import ru.upsoft.gpspointer.databinding.FragmentMainFunctionalityBinding

class MainFunctionalityFragment : Fragment(R.layout.fragment_main_functionality) {

    private val viewBinding: FragmentMainFunctionalityBinding by viewBinding(FragmentMainFunctionalityBinding::bind)

    private val viewModel: MainFunctionalityViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}