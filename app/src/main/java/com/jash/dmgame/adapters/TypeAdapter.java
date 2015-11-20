package com.jash.dmgame.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jash.dmgame.entities.TypeEntity;
import com.jash.dmgame.fragments.ChapterListFragment;

import java.util.List;

/**
 * Created by jash
 * Date: 15-11-20
 * Time: 下午2:08
 */
public class TypeAdapter extends FragmentPagerAdapter {
    private List<TypeEntity> types;

    public TypeAdapter(FragmentManager fm, List<TypeEntity> types) {
        super(fm);
        this.types = types;
    }

    @Override
    public Fragment getItem(int position) {
        return ChapterListFragment.newInstance(types.get(position).getId());
    }

    @Override
    public int getCount() {
        return types.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return types.get(position).getTypeName();
    }
}
