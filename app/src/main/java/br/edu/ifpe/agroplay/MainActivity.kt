package br.edu.ifpe.agroplay

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import br.edu.ifpe.agroplay.ui.InventoryViewModel
import br.edu.ifpe.agroplay.ui.navigation.InventoryNavHost
import br.edu.ifpe.agroplay.ui.theme.AgroplayTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val viewModel: InventoryViewModel by viewModels { InventoryViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AgroplayTheme {
                val navController = rememberNavController()
                val snackbarHostState = remember { SnackbarHostState() }
                val coroutineScope = rememberCoroutineScope()
                
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = { SnackbarHost(snackbarHostState) }
                ) { innerPadding ->
                    InventoryNavHost(
                        navController = navController,
                        viewModel = viewModel,
                        onShowSnackbar = { message ->
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar(message)
                            }
                        },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
