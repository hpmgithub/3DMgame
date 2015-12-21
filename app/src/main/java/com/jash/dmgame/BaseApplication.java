package com.jash.dmgame;

import android.app.Application;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.design.widget.NavigationView;
import android.view.LayoutInflater;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.jash.dmgame.databinding.NavHeaderBinding;
import com.jash.dmgame.tools.DaoUtils;

/**
 * Created by jash
 * Date: 15-11-19
 * Time: 下午4:34
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        DaoUtils.initialize(this);
        DataBindingUtil.setDefaultComponent(new MyComponent());
    }
    public static class MyComponent implements android.databinding.DataBindingComponent {
        @BindingAdapter("bind:header")
        public static void loadHeader(NavigationView view, int id){
            NavHeaderBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(view.getContext()), id, view, false);
            view.addHeaderView(inflate.getRoot());
        }
    }
}
