package waheed.kotlinmvvm.tabs.viewmodeldatasharing

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.waheed.mvvmpoc.utils.KeyboardUtils
import kotlinx.android.synthetic.main.activity_main.*
import waheed.kotlinmvvm.tabs.viewmodeldatasharing.ui.FragPagerAdapter
import waheed.kotlinmvvm.tabs.viewmodeldatasharing.viewModels.ViewModelBasicInfo
import waheed.kotlinmvvm.tabs.viewmodeldatasharing.viewModels.ViewModelCompleteInfo
import waheed.kotlinmvvm.tabs.viewmodeldatasharing.viewModels.ViewModelContactInfo
import waheed.kotlinmvvm.tabs.viewmodeldatasharing.viewModels.ViewModelQualification

/**
 * Created by Waheed on 26,November,2019
 */

class MainActivity : AppCompatActivity() {

    /**
     * Late Initialization of ViewModels
     */
    private lateinit var vmBasicInformation: ViewModelBasicInfo
    private lateinit var vMContactInformation: ViewModelContactInfo
    private lateinit var vMQualification: ViewModelQualification
    private lateinit var vMCompleteInformation: ViewModelCompleteInfo


    /**
     * onCreate
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initPagerAdapter()
        initViewModels()
        observeViewModels()
    }

    /**
     * Initialize Fragment Pager Adapter
     */
    private fun initPagerAdapter() {
        val sectionsPagerAdapter = FragPagerAdapter(
            this,
            supportFragmentManager
        )
        view_pager.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(view_pager)

        // Hiding Keyboard for last fragment in view pager / detail page for this case
        view_pager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 3) KeyboardUtils.hideKeyboard(this@MainActivity)
            }
        })
    }

    /**
     * Initialize View Models
     */
    private fun initViewModels() {
        vmBasicInformation =
            ViewModelProviders.of(this).get(ViewModelBasicInfo::class.java)

        vMContactInformation =
            ViewModelProviders.of(this).get(ViewModelContactInfo::class.java)
        vMQualification =
            ViewModelProviders.of(this).get(ViewModelQualification::class.java)
        vMCompleteInformation =
            ViewModelProviders.of(this).get(ViewModelCompleteInfo::class.java)
    }

    /**
     * Observe View Models Live Data Fields
     */
    private fun observeViewModels() {
        // Getting Clicks Update Of Submit Button Basic Info
        vmBasicInformation.submitButtonBasicInfoClick.observe(this, Observer {
            it.getContentIfNotHandled().let {
                if (it!!) this.view_pager.setCurrentItem(1, true)
            }
        })

        // Getting Clicks Update Of Submit Button Contact Info
        vMContactInformation.submitButtonContactInfoClick.observe(this, Observer {
            it.getContentIfNotHandled().let {
                if (it!!) this.view_pager.setCurrentItem(2, true)
            }
        })

        // Getting Clicks Update Of Submit Button Qualification Info
        vMQualification.submitButtonQualificationClick.observe(this, Observer {
            it.getContentIfNotHandled().let {
                if (it!!) this.view_pager.setCurrentItem(3, true)
            }
        })

        // Getting Clicks Update Of Clear Data Button And Generating Events To ViewModels To Clear Data
        vMCompleteInformation.clearAllData.observe(this, Observer {
            it.getContentIfNotHandled().let { clear ->
                if (clear!!) {
                    vmBasicInformation.clearData()
                    vMContactInformation.clearData()
                    vMQualification.clearData()
                    this.view_pager.setCurrentItem(0, true)
                }
            }
        })
    }
}
