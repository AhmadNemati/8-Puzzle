package com.ahmadnemati.eightpuzzle.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ahmadnemati.eightpuzzle.BFSearch;
import com.ahmadnemati.eightpuzzle.Iterative;
import com.ahmadnemati.eightpuzzle.R;
import com.ahmadnemati.eightpuzzle.model.DoneModel;
import com.ahmadnemati.eightpuzzle.model.PosModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Ahmad Nemati on 10/26/2016.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView resualt;
    private Button bfs;
    private Button iterative;
    private StringBuilder stringBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        resualt = (TextView) findViewById(R.id.resualt);
        bfs = (Button) findViewById(R.id.bfs);
        iterative = (Button) findViewById(R.id.iterative);
        bfs.setOnClickListener(this);
        iterative.setOnClickListener(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(PosModel event) {
        stringBuilder.append("Farmer: " + event.getFarmer());
        stringBuilder.append("Wolf: " + event.getWolf()).append("\n");
        stringBuilder.append("Goat: " + event.getGoat()).append("\n");
        stringBuilder.append("Cabbage: " + event.getCabbage()).append("\n");
        injectIntoTextView();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDoneSearch(DoneModel event) {
        stringBuilder.append("The cost was: " + event.getCost()).append("\n");
        stringBuilder.append("The number of nodes examined: " + event.getNodes()).append("\n");
        injectIntoTextView();
    }

    private void clearResualt() {
        resualt.setText(null);
        stringBuilder = new StringBuilder();
    }

    private void injectIntoTextView() {
        resualt.setText(stringBuilder.toString());
    }

    @Override
    public void onClick(View v) {
        clearResualt();
        switch (v.getId())
        {
            case R.id.bfs:
                BFSearch.search(true);
                break;
            case R.id.iterative:
                Iterative.search(true);
                break;
        }
    }
}
