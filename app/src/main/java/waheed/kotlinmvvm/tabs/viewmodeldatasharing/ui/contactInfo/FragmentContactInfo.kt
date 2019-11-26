package waheed.kotlinmvvm.tabs.viewmodeldatasharing.ui.contactInfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_contact_info.view.*
import waheed.kotlinmvvm.tabs.viewmodeldatasharing.R
import waheed.kotlinmvvm.tabs.viewmodeldatasharing.utils.ToastUtil
import waheed.kotlinmvvm.tabs.viewmodeldatasharing.viewModels.ViewModelContactInfo

/**
 * Created by Waheed on 26,November,2019
 *
 * Fragment Contact Information
 */

class FragmentContactInfo : Fragment() {

    /**
     * Late Initialization of ViewModel and variable
     */
    private lateinit var viewModelContactInformation: ViewModelContactInfo
    private lateinit var rootView: View

    /**
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModels()

    }

    /**
     *
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        rootView = inflater.inflate(R.layout.fragment_contact_info, container, false)

        observeViewModels()
        submitClick()


        return rootView
    }


    /**
     * Inside this method updateEmailAndPhone fun will update data into ViewModel and
     * after validation either viewModel generate SubmitClick event or Any validation error
     */
    private fun submitClick() {
        rootView.btnSubmitContact.setOnClickListener {
            viewModelContactInformation.updateEmailAndPhone(
                rootView.etEmail.text.toString(),
                rootView.etPhone.text.toString()
            )
        }
    }

    /**
     * Initialize View Models
     */
    private fun initViewModels() {
        activity?.let {
            viewModelContactInformation =
                ViewModelProviders.of(it).get(ViewModelContactInfo::class.java)
        }
    }

    /**
     * Observe View Models Live Data Fields
     */
    private fun observeViewModels() {
        //Error message on validation
        viewModelContactInformation.showValidationMessage.observe(this, Observer {
            it.getContentIfNotHandled().let {
                ToastUtil.showToast(context, it)
            }
        })

        //Clear fields observer
        viewModelContactInformation.clearData.observe(this, Observer {
            it.getContentIfNotHandled().let { clear ->
                if (clear == true) {
                    rootView.etEmail.setText("")
                    rootView.etPhone.setText("")
                }
            }
        })
    }
}