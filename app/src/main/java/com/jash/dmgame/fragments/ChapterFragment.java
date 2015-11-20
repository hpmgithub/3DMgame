package com.jash.dmgame.fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jash.dmgame.R;
import com.jash.dmgame.adapters.TypeAdapter;
import com.jash.dmgame.entities.TypeEntity;
import com.jash.dmgame.tools.DaoUtils;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChapterFragment extends Fragment {


    public ChapterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chapter, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("文章");
        }
        TabLayout tab = (TabLayout) view.findViewById(R.id.chapter_tab);
        ViewPager pager = (ViewPager) view.findViewById(R.id.chapter_pager);
        List<TypeEntity> list = DaoUtils.getSession().getTypeEntityDao().queryBuilder().build().list();
        pager.setAdapter(new TypeAdapter(getChildFragmentManager(), list));
        tab.setupWithViewPager(pager);
    }
}
