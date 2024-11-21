package com.example.viewmodel

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.viewmodel.model.ListJK
import com.example.viewmodel.ui.view.DetailMahasiswaView
import com.example.viewmodel.ui.view.FormMahasiswaView
import com.example.viewmodel.ui.viewmodel.MahasiswaViewModel

enum class Halaman{
    Form,
    Data
}

@Composable
fun Navigations(
    modifier: Modifier = Modifier,
    viewModel: MahasiswaViewModel = viewModel(),
    navHost: NavHostController = rememberNavController()
){
    Scaffold { innerPadding ->
        val uiState by viewModel.dataModel.collectAsState()
        NavHost(
            modifier = modifier.padding(innerPadding),
            navController = navHost, startDestination = Halaman.Form.name
        ){
            composable(route = Halaman.Form.name) {
                val konteks = LocalContext.current
                FormMahasiswaView(
                    listJk = ListJK.listJk.map{
                            id -> konteks.resources.getString(id)
                    },
                    onSubmitClick = {
                        viewModel.SaveDataMhs(it)
                        navHost.navigate(Halaman.Data.name)
                    }
                )
            }
            composable(route = Halaman.Data.name) {
                DetailMahasiswaView(
                    dataMhs = uiState,
                    modifier = Modifier,
                    onBackButton = {
                        navHost.popBackStack()
                    }
                )
            }
        }
    }
}