package waheed.kotlinmvvm.tabs.viewmodeldatasharing.ui.basicInfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_basic_info.view.*
import waheed.kotlinmvvm.tabs.viewmodeldatasharing.R
import waheed.kotlinmvvm.tabs.viewmodeldatasharing.utils.ToastUtil
import waheed.kotlinmvvm.tabs.viewmodeldatasharing.viewModels.ViewModelBasicInfo

/**
 * Created by Waheed on 26,November,2019
 *
 * Fragment Basic Information
 */

class FragmentBasicInfo : Fragment() {


    /**
     * Late Initialization of ViewModel and variable
     */
    private lateinit var viewModelBasicInformation: ViewModelBasicInfo
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

        rootView = inflater.inflate(R.layout.fragment_basic_info, container, false)

        observeViewModels()
        submitClick()


        return rootView
    }


    /**
     * Inside this method updateName fun will update data into ViewModel and
     * after validation either viewModel generate SubmitClick event or Any validation error
     */
    private fun submitClick() {
        rootView.btnSubmitBasicInfo.setOnClickListener {
            viewModelBasicInformation.updateName(
                rootView.etFirstName.text.toString(),
                rootView.etLastName.text.toString()
            )
        }
    }

    /**
     * Initialize View Models
     */
    private fun initViewModels() {
        activity?.let {
            viewModelBasicInformation =
                ViewModelProviders.of(it).get(ViewModelBasicInfo::class.java)
        }
    }

    /**
     * Observe View Models Live Data Fields
     * getContentIfNotHandled is only work once if not handled already
     */
    private fun observeViewModels() {

        //Error message on validation
        viewModelBasicInformation.showValidationMessage.observe(this, Observer {
            it.getContentIfNotHandled().let {
                ToastUtil.showToast(context, it)
            }
        })

        //Clear fields observer
        viewModelBasicInformation.clearData.observe(this, Observer {
            it.getContentIfNotHandled().let { clear ->
                if (clear == true) {
                    rootView.etFirstName.setText("")
                    rootView.etLastName.setText("")
                }
            }
        })

    }
}