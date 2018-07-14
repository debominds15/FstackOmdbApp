package com.demo.fstack.fstackapp.views;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.demo.fstack.fstackapp.IAlienContract;
import com.demo.fstack.fstackapp.R;
import com.demo.fstack.fstackapp.util.AppConstants;

public class MainActivity extends AppCompatActivity implements IAlienContract {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        switchFragment(SearchAlienFragment.getInstance()
                , AppConstants.FRAGMENT_TAG_TAB_SEARCH, false);

    }

    private void switchFragment(Fragment fragment, String fragmentTAG, boolean addToBackStack) {
        FragmentTransaction fragTransaction = getFragmentManager().beginTransaction();

        if (addToBackStack) {
            fragTransaction.addToBackStack(fragmentTAG);
        }

        fragTransaction.add(R.id.frame_home_container, fragment, fragmentTAG);
        fragTransaction.commit();
    }

    @Override
    public void switchToAllAliensFragment(String query, String year, String type) {
        Bundle bundle = new Bundle();
        bundle.putString(AppConstants.SEARCH_QUERY, query);
        bundle.putString(AppConstants.SEARCH_YEAR, year);
        bundle.putString(AppConstants.SEARCH_TYPE, type);
        Fragment fr = AllAliensFragment.getInstance();
        fr.setArguments(bundle);
        switchFragment(fr
                , AppConstants.FRAGMENT_TAG_TAB_ALL_ALIENS, true);
    }

    @Override
    public void switchToAllCustomAliensFragment() {
        switchFragment(CustomSearchFragment.getInstance()
                , AppConstants.FRAGMENT_TAG_TAB_CUSTOM_SEARCH, true);
    }

    @Override
    public void switchToAlienDetailFragment(String imdbId) {
        Bundle bundle = new Bundle();
        bundle.putString(AppConstants.IMDB_ID, imdbId);
        Fragment fr = AlienDetailFragment.getInstance();
        fr.setArguments(bundle);
        switchFragment(fr
                , AppConstants.FRAGMENT_TAG_TAB_ALIEN_DETAIL, true);
    }
}
