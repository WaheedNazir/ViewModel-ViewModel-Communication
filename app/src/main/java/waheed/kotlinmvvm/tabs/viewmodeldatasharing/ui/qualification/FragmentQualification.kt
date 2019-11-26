package waheed.kotlinmvvm.tabs.viewmodeldatasharing.ui.qualification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_qualification.view.*
import waheed.kotlinmvvm.tabs.viewmodeldatasharing.R
import waheed.kotlinmvvm.tabs.viewmodeldatasharing.utils.ToastUtil
import waheed.kotlinmvvm.tabs.viewmodeldatasharing.viewModels.ViewModelQualification

/**
 * Created by Waheed on 26,November,2019
 *
 * Fragment Qualification Information
 */

class FragmentQualification : Fragment() {

    /**
     * Late Initialization of ViewModel and variable
     */
    private lateinit var viewModelQualification: ViewModelQualification
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

        rootView = inflater.inflate(R.layout.fragment_qualification, container, false)

        observeViewModels()
        submitClick()


        return rootView
    }


    /**
     * Inside this method updateEducationAndUniversity fun will update data into ViewModel and
     * after validation either viewModel generate SubmitClick event or Any validation error
     */
    private fun submitClick() {
        rootView.btnSubmitQualification.setOnClickListener {
            viewModelQualification.updateEducationAndUniversity(
                rootView.etEducation.text.toString(),
                rootView.etUniversity.text.toString()
            )
        }
    }

    /**
     * Initialize View Models
     */
    private fun initViewModels() {
        activity?.let {
            viewModelQualification =
                ViewModelProviders.of(it).get(ViewModelQualification::class.java)
        }
    }

    /**
     * Observe View Models Live Data Fields
     * getContentIfNotHandled is only work once if not handled already
     */
    private fun observeViewModels() {
        //Error message on validation
        viewModelQualification.showValidationMessage.observe(this, Observer {
            it.getContentIfNotHandled().let {
                ToastUtil.showToast(context, it)
            }
        })

        //Clear fields observer
        viewModelQualification.clearData.observe(this, Observer {
            it.getContentIfNotHandled().let { clear ->
                if (clear == true) {
                    rootView.etEducation.setText("")
                    rootView.etUniversity.setText("")
                }
            }
        })
    }
}