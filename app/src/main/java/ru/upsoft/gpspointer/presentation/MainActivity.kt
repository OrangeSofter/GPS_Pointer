package ru.upsoft.gpspointer.presentation

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import ru.upsoft.gpspointer.R
import ru.upsoft.gpspointer.presentation.common.showErrorPermissionMessage
import ru.upsoft.gpspointer.presentation.screens.root.RootScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var requestLocation: ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            requestLocation = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
                if (!it) {
                    showErrorPermissionMessage(
                        getString(R.string.attention),
                        getString(R.string.fine_location_error_permission_message)
                    )
                    return@registerForActivityResult
                }
                setContent {
                    MaterialTheme {
                        RootScreen()
                    }
                }
            }
            requestLocation.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

}