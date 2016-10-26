package com.ahmadnemati.eightpuzzle.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ahmadnemati.eightpuzzle.DFSearch;
import com.ahmadnemati.eightpuzzle.R;
import com.ahmadnemati.eightpuzzle.model.DoneModel;
import com.ahmadnemati.eightpuzzle.model.PosModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Ahmad Nemati on 10/26/2016.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        DFSearch.search(true);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(PosModel event) {
        Log.e("eeee", event.getGoat());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDoneSearch(DoneModel event) {
        Log.e("Cost", event.getCost());
    }

}
