package ru.upsoft.gpspointer.presentation.ui.features.mainFunctionality

import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import android.transition.TransitionManager
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import ru.upsoft.gpspointer.R
import ru.upsoft.gpspointer.core.model.LocationFailure
import ru.upsoft.gpspointer.core.model.LocationState
import ru.upsoft.gpspointer.databinding.FragmentMainFunctionalityBinding
import ru.upsoft.gpspointer.presentation.common.showErrorPermissionMessage

@AndroidEntryPoint
class MainFunctionalityFragment : Fragment(R.layout.fragment_main_functionality) {

    private val viewBinding: FragmentMainFunctionalityBinding by viewBinding(FragmentMainFunctionalityBinding::bind)
    private val viewModel: MainFunctionalityViewModel by viewModels()

    @SuppressLint("MissingPermission")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenResumed {
            viewModel.locationStateFlow.collect {
                TransitionManager.beginDelayedTransition(viewBinding.root)
                when (it) {
                    is LocationState.Loading -> setLocationLoadingState()
                    is LocationState.LocationRetrieved -> setLocationRetrievedState(it.location)
                    is LocationState.Failed -> setLocationFailedState(it.failure)
                }
            }
        }
    }

    private fun setLocationLoadingState() = with(viewBinding) {
        pb.visibility = View.VISIBLE
    }

    private fun setLocationRetrievedState(location: Location) = with(viewBinding) {
        pb.visibility = View.GONE
        tw.text = "lon: ${location.longitude}\nlat: ${location.latitude}"
        tw.visibility = View.VISIBLE
    }

    private fun setLocationFailedState(failure: LocationFailure) {
        when (failure) {
            LocationFailure.PERMISSION_DENIED -> requireActivity().showErrorPermissionMessage(
                getString(R.string.attention),
                getString(R.string.fine_location_error_permission_message)
            )
            LocationFailure.GMS_UNAVAILABLE -> requireActivity().showErrorPermissionMessage(
                getString(R.string.attention),
                getString(R.string.google_play_services_error)
            )
        }

    }

    override fun onResume() {
        super.onResume()
        viewModel.startLocationMonitoring()
    }

    override fun onPause() {
        super.onPause()
        viewModel.stopLocationMonitoring()
    }

}