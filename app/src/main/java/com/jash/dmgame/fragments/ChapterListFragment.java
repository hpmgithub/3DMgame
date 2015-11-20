package com.jash.dmgame.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jash.dmgame.BR;
import com.jash.dmgame.R;
import com.jash.dmgame.adapters.BindingAdapter;
import com.jash.dmgame.dao.ChapterEntityDao;
import com.jash.dmgame.dao.DaoSession;
import com.jash.dmgame.dao.TypeEntityDao;
import com.jash.dmgame.entities.ChapterEntity;
import com.jash.dmgame.entities.TypeEntity;
import com.jash.dmgame.tools.ChapterResult;
import com.jash.dmgame.tools.DaoUtils;
import com.jash.dmgame.tools.HttpUtils;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class ChapterListFragment extends Fragment implements Callback<ChapterResult> {


    private static final String TYPE_ID = "TYPE_ID";
    private static final String TAG = ChapterFragment.class.getSimpleName();
    private TypeEntity type;
    private BindingAdapter<ChapterEntity> adapter;
    private SwipeRefreshLayout refresh;
    private ChapterEntityDao chapterDao;

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        long typeId = getArguments().getLong(TYPE_ID);
        DaoSession session = DaoUtils.getSession();
        TypeEntityDao typeDao = session.getTypeEntityDao();
        chapterDao = session.getChapterEntityDao();
        type = typeDao.queryBuilder().where(TypeEntityDao.Properties.Id.eq(typeId)).build().list().get(0);
        adapter = new BindingAdapter<>(new ArrayList<ChapterEntity>(), R.layout.chapter_item, BR.chapter);
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
        RecyclerView recycler = (RecyclerView) view.findViewById(R.id.chapter_list_recycler);
        recycler.setAdapter(adapter);
        refresh = ((SwipeRefreshLayout) view);
        refresh.setOnRefreshListener(() -> {
            adapter.clear();
            HttpUtils.getService().getChapterList(20, 1, type.getId()).enqueue(this);
        });
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && adapter.getItemCount() == 0) {
            QueryBuilder<ChapterEntity> queryBuilder = chapterDao.queryBuilder();
            if (type.getId() != 1) {
                queryBuilder.where(ChapterEntityDao.Properties.TypeId.eq(type.getId()));
            }
            Query<ChapterEntity> query = queryBuilder.limit(20).orderDesc(ChapterEntityDao.Properties.SendDate).build();
            adapter.addAll(query.list());
        }
    }

    @Override
    public void onResponse(Response<ChapterResult> response, Retrofit retrofit) {
        refresh.setRefreshing(false);
        List<ChapterEntity> chapterList = response.body().getChapterList();
        adapter.addAll(chapterList);
        chapterDao.insertOrReplaceInTx(chapterList);
    }

    @Override
    public void onFailure(Throwable t) {
        refresh.setRefreshing(false);
        t.printStackTrace();
    }
}
