package waheed.kotlinmvvm.tabs.viewmodeldatasharing.ui.completeInfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_complete_information.*
import kotlinx.android.synthetic.main.fragment_complete_information.view.*
import waheed.kotlinmvvm.tabs.viewmodeldatasharing.R
import waheed.kotlinmvvm.tabs.viewmodeldatasharing.viewModels.ViewModelBasicInfo
import waheed.kotlinmvvm.tabs.viewmodeldatasharing.viewModels.ViewModelCompleteInfo
import waheed.kotlinmvvm.tabs.viewmodeldatasharing.viewModels.ViewModelContactInfo
import waheed.kotlinmvvm.tabs.viewmodeldatasharing.viewModels.ViewModelQualification

/**
 * Created by Waheed on 26,November,2019
 *
 * Fragment Complete Information
 */

class FragmentCompleteInfo : Fragment() {

    /**
     * Late Initialization of ViewModels and variable
     */
    private lateinit var vmBasicInformation: ViewModelBasicInfo
    private lateinit var vMContactInformation: ViewModelContactInfo
    private lateinit var vMQualification: ViewModelQualification
    private lateinit var vMCompleteInformation: ViewModelCompleteInfo
    private lateinit var root: View

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

        root = inflater.inflate(R.layout.fragment_complete_information, container, false)

        observeViewModels()
        submitClick()

        return root
    }


    /**
     * Inside this method updateName fun will update data into ViewModel and
     * after validation either viewModel generate SubmitClick event or Any validation error
     */
    private fun submitClick() {
        root.btnClearAll.setOnClickListener {
            vMCompleteInformation.clearAllData()

        }
    }

    /**
     * Initialize View Models
     */
    private fun initViewModels() {
        activity?.let {
            vmBasicInformation =
                ViewModelProviders.of(it).get(ViewModelBasicInfo::class.java)
        }
        activity?.let {
            vMContactInformation =
                ViewModelProviders.of(it).get(ViewModelContactInfo::class.java)
        }
        activity?.let {
            vMQualification =
                ViewModelProviders.of(it).get(ViewModelQualification::class.java)
        }
        activity?.let {
            vMCompleteInformation =
                ViewModelProviders.of(it).get(ViewModelCompleteInfo::class.java)
        }
    }

    /**
     * Observe View Models Live Data Fields
     * getContentIfNotHandled is only work once if not handled already
     */
    private fun observeViewModels() {

        vmBasicInformation.basicInformation().observe(this, Observer {

            name.text = it.firstName + " " + it.lastName
        })

        vMContactInformation.contactInformation().observe(this, Observer {
            email.text = it.email
            phone.text = it.phone
        })

        vMQualification.qualification().observe(this, Observer {
            education.text = it.education
            university.text = it.university
        })

    }
}