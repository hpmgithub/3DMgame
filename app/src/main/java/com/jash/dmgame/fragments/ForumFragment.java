package com.jash.dmgame.fragments;


import android.app.AlarmManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jash.dmgame.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ForumFragment extends Fragment {


    public ForumFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forum, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("论坛");
        }
        WebView web = (WebView) view.findViewById(R.id.forum_web);
        web.loadUrl("http://bbs.3dmgame.com/forum.php");
        web.setWebViewClient(new WebViewClient());
        AlarmManager service = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);

    }
}
