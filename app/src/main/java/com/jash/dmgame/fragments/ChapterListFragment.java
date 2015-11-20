package com.jash.dmgame.fragments;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jash.dmgame.R;
import com.jash.dmgame.dao.TypeEntityDao;
import com.jash.dmgame.entities.TypeEntity;
import com.jash.dmgame.tools.DaoUtils;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class ChapterListFragment extends Fragment {


    private static final String TYPE_ID = "TYPE_ID";

    public ChapterListFragment() {
        // Required empty public constructor
    }

    public static ChapterListFragment newInstance(long typeId) {
        Bundle args = new Bundle();
        args.putLong(TYPE_ID, typeId);

        ChapterListFragment fragment = new ChapterListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chapter_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        long typeId = getArguments().getLong(TYPE_ID);
        TypeEntityDao typeDao = DaoUtils.getSession().getTypeEntityDao();
        TypeEntity typeEntity = typeDao.queryBuilder().where(TypeEntityDao.Properties.Id.eq(typeId)).build().list().get(0);
        ((TextView) view.findViewById(R.id.chapter_text)).setText(typeEntity.getTypeName());
    }
}
