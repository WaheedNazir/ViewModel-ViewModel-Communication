package waheed.kotlinmvvm.tabs.viewmodeldatasharing.ui

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import waheed.kotlinmvvm.tabs.viewmodeldatasharing.R
import waheed.kotlinmvvm.tabs.viewmodeldatasharing.ui.basicInfo.FragmentBasicInfo
import waheed.kotlinmvvm.tabs.viewmodeldatasharing.ui.completeInfo.FragmentCompleteInfo
import waheed.kotlinmvvm.tabs.viewmodeldatasharing.ui.contactInfo.FragmentContactInfo
import waheed.kotlinmvvm.tabs.viewmodeldatasharing.ui.qualification.FragmentQualification

/**
 * Created by Waheed on 26,November,2019
 */

private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2,
    R.string.tab_text_3,
    R.string.tab_text_4
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
@Suppress("DEPRECATION")
class FragPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                FragmentBasicInfo()
            }
            1 -> {
                FragmentContactInfo()
            }
            2 -> {
                FragmentQualification()
            }
            else -> {
                FragmentCompleteInfo()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 4 total pages.
        return 4
    }
}