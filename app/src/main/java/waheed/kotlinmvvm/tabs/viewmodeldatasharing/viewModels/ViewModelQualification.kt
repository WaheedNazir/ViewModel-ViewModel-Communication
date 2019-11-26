package waheed.kotlinmvvm.tabs.viewmodeldatasharing.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import waheed.kotlinmvvm.tabs.viewmodeldatasharing.model.QualificationModel
import waheed.kotlinmvvm.tabs.viewmodeldatasharing.utils.SingleLiveEvent

/**
 * Created by Waheed on 26,November,2019
 */

class ViewModelQualification : ViewModel() {

    private var qualification: QualificationModel =
        QualificationModel("", "")

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
    private val _submitButtonQualificationClick = MutableLiveData<SingleLiveEvent<Boolean>>()

    val submitButtonQualificationClick: LiveData<SingleLiveEvent<Boolean>> =
        _submitButtonQualificationClick


    /**
     * UserName
     */
    private var _education: MutableLiveData<String> = MutableLiveData()

    private var _university: MutableLiveData<String> = MutableLiveData()

    private var _isEducationAndUniversityValid: MutableLiveData<Boolean> = MutableLiveData()

    fun isEducationAndUniversityValid(): LiveData<Boolean> = _isEducationAndUniversityValid


    fun updateEducationAndUniversity(education: String, university: String) {
        this._education.value = education
        this._university.value = university

        qualification =
            QualificationModel(education, university)

        validateEducationAndUniversityOnSubmitClick()
    }

    private fun validateEducationAndUniversityOnSubmitClick() {
        _isEducationAndUniversityValid.value =
            (_education.value!!.isNotEmpty() && _university.value!!.isNotEmpty())

        if (_education.value!!.isEmpty() && _university.value!!.isEmpty()) {
            notifyValidationError("Please provide Education and University name")
            return
        } else if (_education.value!!.isEmpty()) {
            notifyValidationError("Please provide Education")
            return
        } else if (_university.value!!.isEmpty()) {
            notifyValidationError("Please provide University name")
            return
        }
        //Update basic info POJO so that this data can be observed at last/complete info page
        _qualification.value = qualification
        // All validations passed proceed the submit button click
        _submitButtonQualificationClick.value = SingleLiveEvent(true)
    }


    /**
     * Hold information in instance of Model class Qualification
     */
    private var _qualification: MutableLiveData<QualificationModel> = MutableLiveData()

    fun qualification(): LiveData<QualificationModel> = _qualification


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
        _isEducationAndUniversityValid.value = false
        _education.value = ""
        _university.value = ""
    }

    /**
     * Single Live Event Clear Data
     */
    private val _clearData = MutableLiveData<SingleLiveEvent<Boolean>>()

    val clearData: LiveData<SingleLiveEvent<Boolean>> = _clearData

    fun clearData() {
        initData()
        _qualification.value =
            QualificationModel("", "")
        _clearData.value = SingleLiveEvent(true)
    }
}