//package ru.upsoft.gpspointer.presentation.screens.root
//
//import android.annotation.SuppressLint
//import android.location.Location
//import android.os.Bundle
//import android.transition.TransitionManager
//import android.view.View
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.viewModels
//import by.kirich1409.viewbindingdelegate.viewBinding
//import dagger.hilt.android.AndroidEntryPoint
//import ru.upsoft.gpspointer.R
//import ru.upsoft.gpspointer.core.model.LocationFailure
//import ru.upsoft.gpspointer.core.model.LocationState
//import ru.upsoft.gpspointer.core.model.WeatherState
//import ru.upsoft.gpspointer.databinding.FragmentMainFunctionalityBinding
//import ru.upsoft.gpspointer.presentation.common.observeWhenResumed
//import ru.upsoft.gpspointer.presentation.common.showErrorPermissionMessage
//
//@AndroidEntryPoint
//class MainFunctionalityFragment : Fragment(R.layout.fragment_main_functionality) {
//
//    private val viewBinding: FragmentMainFunctionalityBinding by viewBinding(FragmentMainFunctionalityBinding::bind)
//    private val viewModel: RootViewModel by viewModels()
//
//    @SuppressLint("MissingPermission")
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        observeWhenResumed(viewModel.locationStateFlow) {
//            TransitionManager.beginDelayedTransition(viewBinding.root)
//            when (it) {
//                is LocationState.Loading -> setLocationLoadingState()
//                is LocationState.LocationRetrieved -> setLocationRetrievedState(it.location)
//                is LocationState.Failed -> setLocationFailedState(it.failure)
//            }
//        }
//        observeWhenResumed(viewModel.weatherStateFlow) {
//            when (it) {
//                is WeatherState.Success -> {
//                    TransitionManager.beginDelayedTransition(viewBinding.root)
//                    viewBinding.tvWeather.text =
//                        "${it.data.weather.last().description}\nскорость ветра ${it.data.wind.speed} м/с"
//                    viewBinding.tvWeather.visibility = View.VISIBLE
//                }
//                else -> Unit
//            }
//        }
//    }
//
//    private fun setLocationLoadingState() = with(viewBinding) {
//        pb.visibility = View.VISIBLE
//    }
//
//    private fun setLocationRetrievedState(location: Location) = with(viewBinding) {
//        pb.visibility = View.GONE
//        tvLocation.text = "долгота: ${location.longitude}\nширота: ${location.latitude}"
//        tvLocation.visibility = View.VISIBLE
//
//        viewModel.startWeatherMonitoring(location)
//    }
//
//    private fun setLocationFailedState(failure: LocationFailure) {
//        when (failure) {
//            LocationFailure.PERMISSION_DENIED -> requireActivity().showErrorPermissionMessage(
//                getString(R.string.attention),
//                getString(R.string.fine_location_error_permission_message)
//            )
//            LocationFailure.GMS_UNAVAILABLE -> requireActivity().showErrorPermissionMessage(
//                getString(R.string.attention),
//                getString(R.string.google_play_services_error)
//            )
//        }
//
//    }
//
//    override fun onResume() {
//        super.onResume()
//        viewModel.startLocationMonitoring()
//    }
//
//    override fun onPause() {
//        super.onPause()
//        viewModel.stopLocationMonitoring()
//    }
//
//}