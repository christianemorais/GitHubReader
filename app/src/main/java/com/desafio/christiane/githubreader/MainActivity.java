package com.desafio.christiane.githubreader;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.desafio.christiane.githubreader.databinding.ActivityMainBinding;
import com.desafio.christiane.githubreader.fragment.RepositoryListFragment;
import com.desafio.christiane.githubreader.util.Util;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityMainBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_main);

        initToolbar(binding);
        Util.replaceFragment(this.getSupportFragmentManager(), new RepositoryListFragment());
    }

    private void initToolbar(ActivityMainBinding binding) {
        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.app_name);
        }
    }
}