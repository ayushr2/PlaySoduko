package com.example.playsoduko.playsoduko;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(actionBar.NAVIGATION_MODE_TABS);

        Tab solveTab = actionBar.newTab().setText("Solve");
        Tab playTab = actionBar.newTab().setText("Play");

        Fragment solveFragment = new FragmentSolve();
        Fragment playFragment = new FragmentPlay();

        solveTab.setTabListener(new MyTabListener(solveFragment));
        playTab.setTabListener(new MyTabListener(playFragment));

        actionBar.addTab(solveTab);
        actionBar.addTab(playTab);
    }
}

class MyTabListener implements ActionBar.TabListener {
    Fragment fragment;

    public MyTabListener(Fragment fragment) {
        this.fragment = fragment;
    }

    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        ft.replace(R.id.container, fragment);
    }

    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
        ft.remove(fragment);
    }

    public void onTabReselected(Tab tab, FragmentTransaction ft) {
        // nothing done here
    }
}


class FragmentSolve extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.solve_layout, container, false);
        return view;
    }
}

class FragmentPlay extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.play_layout, container, false);
        return view;
    }
}

