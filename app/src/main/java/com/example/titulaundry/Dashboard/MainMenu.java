package com.example.titulaundry.Dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.titulaundry.R;
import com.google.android.material.tabs.TabLayout;

public class MainMenu extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private home_fragment home_fragment;
    private Service_fragment service_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        switchMenu();

    }



    public void switchMenu(){
        tabLayout = findViewById(R.id.tab);
        viewPager = findViewById(R.id.viewPager);

        tabLayout.setupWithViewPager(viewPager);

        fragmentSwitch swtch = new fragmentSwitch(getSupportFragmentManager(), 0);
        swtch.addFragment(new home_fragment(),"Home");
        swtch.addFragment(new Service_fragment(),"SERVICE");
        swtch.addFragment(new order_fragment(),"ORDER");
        swtch.addFragment(new account_fragment(),"ACCOUNT");
        viewPager.setAdapter(swtch);

        tabLayout.getTabAt(0).setIcon(R.drawable.home);
        tabLayout.getTabAt(1).setIcon(R.drawable.menu);
        tabLayout.getTabAt(2).setIcon(R.drawable.shop);
        tabLayout.getTabAt(3).setIcon(R.drawable.account);

    }
}