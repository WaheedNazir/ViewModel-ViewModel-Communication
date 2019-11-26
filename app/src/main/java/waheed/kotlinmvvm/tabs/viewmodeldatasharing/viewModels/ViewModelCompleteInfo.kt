package waheed.kotlinmvvm.tabs.viewmodeldatasharing.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import waheed.kotlinmvvm.tabs.viewmodeldatasharing.utils.SingleLiveEvent

/**
 * Created by Waheed on 26,November,2019
 */

class ViewModelCompleteInfo : ViewModel() {

    private val _clearAllData = MutableLiveData<SingleLiveEvent<Boolean>>()
    val clearAllData: LiveData<SingleLiveEvent<Boolean>> = _clearAllData

    fun clearAllData() {
        _clearAllData.value = SingleLiveEvent(true)
    }
}