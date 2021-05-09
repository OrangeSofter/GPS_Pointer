package ru.upsoft.gpspointer.presentation

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.gms.common.GooglePlayServicesUtil.isGooglePlayServicesAvailable
import dagger.hilt.android.AndroidEntryPoint
import ru.upsoft.gpspointer.R
import ru.upsoft.gpspointer.databinding.ActivityMainBinding
import ru.upsoft.gpspointer.presentation.common.isGooglePlayServicesAvailable
import ru.upsoft.gpspointer.presentation.common.showAlertMessageWithoutNegativeButton
import ru.upsoft.gpspointer.presentation.common.showErrorPermissionMessage
import ru.upsoft.gpspointer.presentation.ui.features.mainFunctionality.MainFunctionalityFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewBinding: ActivityMainBinding by viewBinding(ActivityMainBinding::bind)
    private lateinit var requestLocation: ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            requestLocation = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
                if (!it) {
                    showErrorPermissionMessage(getString(R.string.attention), getString(R.string.fine_location_error_permission_message))
                    return@registerForActivityResult
                }
                if(isGooglePlayServicesAvailable()) commitMainFunctionalityFragment()
                else showAlertMessageWithoutNegativeButton(
                    getString(R.string.attention),
                    getString(R.string.google_play_services_error),
                    false,
                    ::finish
                )
            }
            requestLocation.launch(Manifest.permission.ACCESS_FINE_LOCATION)//Todo: Если не дать разрешение после перехода в настройки - то ничего не происходит
        }
    }

    private fun commitMainFunctionalityFragment(){
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add(R.id.fragmentContainer, MainFunctionalityFragment(), "mainFuncFrag")
        }
    }

}