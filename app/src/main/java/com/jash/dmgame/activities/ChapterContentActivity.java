package com.jash.dmgame.activities;

import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.webkit.WebView;

import com.jash.dmgame.R;
import com.jash.dmgame.dao.ChapterEntityDao;
import com.jash.dmgame.entities.ChapterEntity;
import com.jash.dmgame.tools.DaoUtils;
import com.jash.dmgame.tools.HttpUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class ChapterContentActivity extends AppCompatActivity implements Callback<ChapterEntity> {

    private ChapterEntityDao chapterDao;
    private WebView web;
    private static final String CSS = "<style>img{max-width:100%}</style>";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_content);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        long chapterId = getIntent().getLongExtra("chapterId", -1);
        chapterDao = DaoUtils.getSession().getChapterEntityDao();
        ChapterEntity chapter = chapterDao.queryBuilder().where(ChapterEntityDao.Properties.Id.eq(chapterId)).build().list().get(0);
        setTitle(chapter.getTitle());
        web = (WebView) findViewById(R.id.chapter_content_web);
        if (!TextUtils.isEmpty(chapter.getBody())) {
            loadWeb(chapter);
        } else {
            HttpUtils.getService().getChapterContent(chapter.getId(), chapter.getTypeId()).enqueue(this);
        }
    }

    private void loadWeb(ChapterEntity chapter) {
        try {
            String data = CSS + URLDecoder.decode(chapter.getBody(), "UTF-8");
            web.loadDataWithBaseURL("http://www.3dmgame.com", data, "text/html; charset=utf8", "UTF-8", null);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                ActivityCompat.finishAfterTransition(this);
                break;
        }
        return true;
    }

    @Override
    public void onResponse(Response<ChapterEntity> response, Retrofit retrofit) {
        loadWeb(response.body());
        chapterDao.insertOrReplace(response.body());
    }

    @Override
    public void onFailure(Throwable t) {
        t.printStackTrace();
    }
}
