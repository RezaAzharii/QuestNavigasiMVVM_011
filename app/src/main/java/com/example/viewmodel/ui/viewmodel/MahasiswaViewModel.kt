package com.example.viewmodel.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.viewmodel.model.Mahasiswa
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MahasiswaViewModel : ViewModel() {
    //Cuma bisa dipake dengan internal class yang digunakan
    private val _dataModel = MutableStateFlow(Mahasiswa())
    //acces for public
    val dataModel : StateFlow<Mahasiswa> = _dataModel.asStateFlow()

    fun SaveDataMhs(
        lisData : MutableList<String>
    ){
        _dataModel.update { mhs ->
            mhs.copy(
                nama = lisData[0],
                gender = lisData[1],
                alamat = lisData[2]
            )
        }
    }
}