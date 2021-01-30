package com.example.aaachat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.aaachat.databinding.ActivityMainBinding;
import com.example.aaachat.menu.AllItemsFragment;
import com.example.aaachat.menu.Category1Fragment;
import com.example.aaachat.menu.Category2Fragment;
import com.example.aaachat.menu.Category3Fragment;
import com.example.aaachat.menu.Category4Fragment;
import com.example.aaachat.menu.Category5Fragment;
import com.example.aaachat.menu.Category6Fragment;
import com.example.aaachat.startup.HomeActivity;
import com.example.aaachat.startup.PrivacyPolicyActivity;
import com.example.aaachat.startup.ProfileActivity;
import com.example.aaachat.startup.TermsAndConditionsActivity;

import java.util.ArrayList;
import java.util.List;

public class buyerMainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_main);

        setUpViewPager(binding.viewPager);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
        setSupportActionBar(binding.toolbar);

        binding.profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(buyerMainActivity.this, ProfileActivity.class));
            }
        });

        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                changeFabIcon(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void changeFabIcon(final int index) {
        binding.fabAction.hide();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                switch (index)
                {
                    case 0:
                        binding.fabAction.setImageDrawable(getDrawable(R.drawable.ic_baseline_star_rate_24));
                        break;

                    case 1:
                        binding.fabAction.setImageDrawable(getDrawable(R.drawable.ic_baseline_star_rate_24));
                        break;

                    case 2:
                        binding.fabAction.setImageDrawable(getDrawable(R.drawable.ic_baseline_star_rate_24));
                        break;

                    case 3:
                        binding.fabAction.setImageDrawable(getDrawable(R.drawable.ic_baseline_star_rate_24));
                        break;

                    case 4:
                        binding.fabAction.setImageDrawable(getDrawable(R.drawable.ic_baseline_star_rate_24));
                        break;

                    case 5:
                        binding.fabAction.setImageDrawable(getDrawable(R.drawable.ic_baseline_star_rate_24));
                        break;

                    case 6:
                        binding.fabAction.setImageDrawable(getDrawable(R.drawable.ic_baseline_star_rate_24));
                        break;

                }
                binding.fabAction.show();
            }
        }, 400);
    }


    private void setUpViewPager(ViewPager viewPager)
    {
        SectionsPagerAdapter adapter= new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new AllItemsFragment(), "All Items");
        adapter.addFragment(new Category1Fragment(), "Vegetables");
        adapter.addFragment(new Category2Fragment(), "Cereals");
        adapter.addFragment(new Category3Fragment(), "Fruits");
        adapter.addFragment(new Category4Fragment(), "Category 4");
        adapter.addFragment(new Category5Fragment(), "Category 5");
        adapter.addFragment(new Category6Fragment(), "Category 6");

        viewPager.setAdapter(adapter);
    }


    private static class SectionsPagerAdapter extends FragmentPagerAdapter
    {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public SectionsPagerAdapter(FragmentManager manager)
        {
            super(manager);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title){
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id)
        {
            case R.id.menu_search:
                Toast.makeText(this,"Search",Toast.LENGTH_SHORT).show();
                break;

            case R.id.menu_home:
                startActivity(new Intent(buyerMainActivity.this, HomeActivity.class));
                break;

            case R.id.menu_bookmarks:
                Toast.makeText(this,"Bookmarks",Toast.LENGTH_SHORT).show();
                break;

            case R.id.menu_terms:
                startActivity(new Intent(buyerMainActivity.this, TermsAndConditionsActivity.class));
                break;

            case R.id.menu_privacy:
                startActivity(new Intent(buyerMainActivity.this, PrivacyPolicyActivity.class));
                break;

            case R.id.menu_aboutUs:
                startActivity(new Intent(buyerMainActivity.this, AboutUsActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }



}