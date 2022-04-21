package com.appexample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


interface textInterface {
    fun getReminderList(reminderTable: String): String
}

class Repo @Inject constructor(val dataManager: DataManager) : textInterface {
    override fun getReminderList(reminderTable: String): String {
        return dataManager.getListReminder()
    }
}

class DataManager @Inject constructor() {
    fun getListReminder(): String = "reza"
}

@HiltViewModel
class viewmode @Inject constructor(val repo: textInterface) : ViewModel() {
    var sdsdc = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()


}