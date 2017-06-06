package swampsumo.com.adapters;

/**
 * Created by babatundedennis on 6/18/15.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import swampsumo.com.fragments.FeedbackFragment;
import swampsumo.com.fragments.SettingsFragment;
import swampsumo.com.utils.u.helpers.Constants;

public class DashboardPagerAdapter extends FragmentPagerAdapter {

    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created

    // Build a Constructor and assign the passed Values to appropriate values in the class
    public DashboardPagerAdapter(FragmentManager fm, CharSequence mTitles[],
                                 int mNumbOfTabsumb
    ) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;
    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {

        if(position == 0) // if the position is 0 we are returning the First tab
        {
            Log.e(Constants.TAG, "settings clicked");
            SettingsFragment tab1 = new SettingsFragment();
            return tab1;
        }
        else             // As we are having 2 tabs if the position is now 0 it must be 1 so we are returning second tab
        {
            Log.e(Constants.TAG, "feedback clicked");
            FeedbackFragment tab2 = new FeedbackFragment();
            return tab2;
        }

    }

    // This method return the titles for the Tabs in the Tab Strip

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    // This method return the Number of tabs for the tabs Strip

    @Override
    public int getCount() {
        return NumbOfTabs;
    }
}