package waheed.kotlinmvvm.tabs.viewmodeldatasharing.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mobileconnect.com.kotlin.utils.StringUtils
import waheed.kotlinmvvm.tabs.viewmodeldatasharing.model.ContactInfoModel
import waheed.kotlinmvvm.tabs.viewmodeldatasharing.utils.SingleLiveEvent

/**
 * Created by Waheed on 26,November,2019
 */

class ViewModelContactInfo : ViewModel() {

    private var contactInformation: ContactInfoModel =
        ContactInfoModel("", "")

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
    private val _submitButtonContactInfoClick = MutableLiveData<SingleLiveEvent<Boolean>>()

    val submitButtonContactInfoClick: LiveData<SingleLiveEvent<Boolean>> =
        _submitButtonContactInfoClick


    /**
     * UserName
     */
    private var _email: MutableLiveData<String> = MutableLiveData()

    private var _phone: MutableLiveData<String> = MutableLiveData()

    private var _isEmailAndPhoneValid: MutableLiveData<Boolean> = MutableLiveData()

    fun isEmailAndPhoneValid(): LiveData<Boolean> = _isEmailAndPhoneValid


    fun updateEmailAndPhone(email: String, phone: String) {
        this._email.value = email
        this._phone.value = phone

        contactInformation =
            ContactInfoModel(email, phone)

        validateEmailAndPhoneOnSubmitClick()
    }

    private fun validateEmailAndPhoneOnSubmitClick() {
        _isEmailAndPhoneValid.value = (_email.value!!.isNotEmpty() && _phone.value!!.isNotEmpty())

        if (_email.value!!.isEmpty() && _phone.value!!.isEmpty()) {
            notifyValidationError("Please enter email and phone")
            return
        } else if (_email.value!!.isEmpty()) {
            notifyValidationError("Please enter email")
            return
        } else if (!StringUtils.isValidEmail(_email.value!!)) {
            notifyValidationError("Please enter valid email")
            return
        } else if (_phone.value!!.isEmpty()) {
            notifyValidationError("Please enter phone number")
            return
        }
        //Update basic info POJO so that this data can be observed at last/complete info page
        _contactInformation.value = contactInformation
        // All validations passed proceed the submit button click
        _submitButtonContactInfoClick.value = SingleLiveEvent(true)
    }


    /**
     * Hold information in instance of Model class ContactInformation
     */
    private var _contactInformation: MutableLiveData<ContactInfoModel> = MutableLiveData()

    fun contactInformation(): LiveData<ContactInfoModel> = _contactInformation

    /**
     * Init Block execute first, whenever you create instance
     */
    init {
        initData()
    }


    /**
     * Initialize Data / Clear Data
     */
    private fun initData() {
        _isEmailAndPhoneValid.value = false
        _email.value = ""
        _phone.value = ""
    }

    /**
     * Single Live Event Clear Data
     */
    private val _clearData = MutableLiveData<SingleLiveEvent<Boolean>>()

    val clearData: LiveData<SingleLiveEvent<Boolean>> = _clearData

    fun clearData() {
        initData()
        _contactInformation.value =
            ContactInfoModel("", "")
        _clearData.value = SingleLiveEvent(true)
    }
}