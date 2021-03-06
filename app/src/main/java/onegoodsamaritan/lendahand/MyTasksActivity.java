package onegoodsamaritan.lendahand;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import onegoodsamaritan.lendahand.models.Task;

public class MyTasksActivity extends BaseActivity {
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.mytasks, null, false);
        mDrawer.addView(contentView, 0);

        getSupportActionBar().setTitle("My Activity");
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        navigationView.getMenu().getItem(1).setChecked(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            if (getArguments().getInt(ARG_SECTION_NUMBER) == 1) {
                RecyclerView mFeedRecyclerView;
                RecyclerView.Adapter mFeedAdapter;
                RecyclerView.LayoutManager mFeedLayoutManager;
                ProgressBar progressBar;
                List<Task> mInitialTaskList = new ArrayList<>();
                View rootView = inflater.inflate(R.layout.fragment_main, container, false);

                mFeedRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycle);
                progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
                mFeedLayoutManager = new LinearLayoutManager(getActivity());
                mFeedRecyclerView.setLayoutManager(mFeedLayoutManager);
                mFeedAdapter = new TaskAdapter(mInitialTaskList, progressBar, 0);
                mFeedRecyclerView.setAdapter(mFeedAdapter);
                return rootView;
            } else {
                RecyclerView mFeedRecyclerView;
                RecyclerView.Adapter mFeedAdapter;
                RecyclerView.LayoutManager mFeedLayoutManager;
                ProgressBar progressBar;
                List<Task> mInitialTaskList = new ArrayList<>();

                View rootView = inflater.inflate(R.layout.fragment_main2, container, false);

                progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
                mFeedRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycle2);
                mFeedLayoutManager = new LinearLayoutManager(getActivity());
                mFeedRecyclerView.setLayoutManager(mFeedLayoutManager);
                mFeedAdapter = new TaskAdapter(mInitialTaskList, progressBar, 1);
                mFeedRecyclerView.setAdapter(mFeedAdapter);
                return rootView;
            }
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Issued";
                case 1:
                    return "Acquired";
            }
            return null;
        }
    }
}