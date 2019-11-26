package waheed.kotlinmvvm.tabs.viewmodeldatasharing.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import waheed.kotlinmvvm.tabs.viewmodeldatasharing.model.BasicInfoModel
import waheed.kotlinmvvm.tabs.viewmodeldatasharing.utils.SingleLiveEvent

/**
 * Created by Waheed on 26,November,2019
 */

class ViewModelBasicInfo : ViewModel() {

    private var basicInformation: BasicInfoModel =
        BasicInfoModel("", "")

    /**
     * Single Live Event
     * A wrapper to handle Single LiveData to
     */
    private val _showValidationMessage = MutableLiveData<SingleLiveEvent<String>>()

    val showValidationMessage: LiveData<SingleLiveEvent<String>> = _showValidationMessage

    private fun notifyValidationError(errorMessage: String) {
        _showValidationMessage.value =
            SingleLiveEvent(errorMessage)
    }

    /**
     * Single Live Event For Submit Button Click
     * A wrapper to handle Single LiveData to
     */
    private val _submitButtonBasicInfoClick = MutableLiveData<SingleLiveEvent<Boolean>>()

    val submitButtonBasicInfoClick: LiveData<SingleLiveEvent<Boolean>> = _submitButtonBasicInfoClick


    /**
     * UserName
     */
    private var _firstName: MutableLiveData<String> = MutableLiveData()

    private var _lastName: MutableLiveData<String> = MutableLiveData()

    private var _isNameValid: MutableLiveData<Boolean> = MutableLiveData()

    fun isNameValid(): LiveData<Boolean> = _isNameValid


    fun updateName(firsName: String, lastName: String) {
        this._firstName.value = firsName
        this._lastName.value = lastName

        basicInformation.firstName = firsName
        basicInformation.lastName = lastName

        updateLastName()
    }

    private fun updateLastName() {
        _isNameValid.value = (_firstName.value!!.isNotEmpty() && _lastName.value!!.isNotEmpty())

        if (_firstName.value!!.isEmpty() && _lastName.value!!.isEmpty()) {
            notifyValidationError("Please provide first and last name")
            return
        } else if (_firstName.value!!.isEmpty()) {
            notifyValidationError("Please enter first name")
            return
        } else if (_lastName.value!!.isEmpty()) {
            notifyValidationError("Please enter last name")
            return
        }
        //Update basic info POJO so that this data can be observed at last/complete info page
        _basicInformation.value = basicInformation
        // All validations passed proceed the submit button click
        _submitButtonBasicInfoClick.value = SingleLiveEvent(true)
    }


    /**
     * Hold information in instance of Model class BasicInformation
     */
    private var _basicInformation: MutableLiveData<BasicInfoModel> = MutableLiveData()

    fun basicInformation(): LiveData<BasicInfoModel> = _basicInformation


    /**
     * Init Block execute first, whenever you create instance
     */
    init {
        initData()
    }


    /**
     * Initialize Data
     */
    private fun initData() {
        _isNameValid.value = false
        _firstName.value = ""
        _lastName.value = ""
    }


    /**
     * Single Live Event Clear Data
     */
    private val _clearData = MutableLiveData<SingleLiveEvent<Boolean>>()

    val clearData: LiveData<SingleLiveEvent<Boolean>> = _clearData

    fun clearData() {
        initData()
        _basicInformation.value =
            BasicInfoModel("", "")
        _clearData.value = SingleLiveEvent(true)
    }
}