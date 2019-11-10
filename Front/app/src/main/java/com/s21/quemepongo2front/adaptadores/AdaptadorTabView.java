package com.s21.quemepongo2front.adaptadores;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class AdaptadorTabView extends FragmentStatePagerAdapter {
    private final List<Fragment> mFragmentList= new ArrayList<>();
    private final List<String> mFragmentTitleList= new ArrayList<>();

    public AdaptadorTabView(@NonNull FragmentManager fm) {
        super(fm);
    }


    public CharSequence getPageTitle(int position){
        return mFragmentTitleList.get(position);
    }

    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    public int getCount() {
        return mFragmentList.size();
    }

    public void addFragment(Fragment fragment,String title){
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }
}
