package ru.upsoft.gpspointer.presentation.ui.features.mainFunctionality

import android.location.Location
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import ru.upsoft.gpspointer.R
import ru.upsoft.gpspointer.databinding.FragmentMainFunctionalityBinding

@AndroidEntryPoint
class MainFunctionalityFragment : Fragment(R.layout.fragment_main_functionality) {

    private val viewBinding: FragmentMainFunctionalityBinding by viewBinding(FragmentMainFunctionalityBinding::bind)
    private val viewModel: MainFunctionalityViewModel by viewModels()
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.tw.text = viewModel.i.toString()

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location : Location? ->
                viewBinding.tw.text = "lon: ${location?.longitude}\nlat: ${location?.longitude}"
            }

       /* fusedLocationClient.requestLocationUpdates(locationRequest,
            locationCallback,
            Looper.getMainLooper())*/
    }

}