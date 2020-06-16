package com.example.sebastianparedesmercadoesclavo.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;


import com.example.sebastianparedesmercadoesclavo.R;
import com.example.sebastianparedesmercadoesclavo.databinding.ActivitySearchBinding;
import com.google.android.material.navigation.NavigationView;

import static com.example.sebastianparedesmercadoesclavo.view.ResultDetailFragment.KEY_ID;


public class SearchActivity extends AppCompatActivity implements SearchFragment.SearchFragmentListener{


    private Toolbar toolbar;
    private LinearLayout linearLayout;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActivitySearchBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        toolbar = findViewById(R.id.toolbar);
        linearLayout = findViewById(R.id.fragmentcontainer);
        navigationView = findViewById(R.id.searchNavView);
        drawerLayout = findViewById(R.id.searchDrawerL);


        //listener de menu lateral
        binding.searchNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menuItemSobreNosotros:
                        SobreNosotrosFragment sobreNosotrosFragment = new SobreNosotrosFragment();
                        pegarFragment(sobreNosotrosFragment);
                        break;
                }
                binding.searchDrawerL.closeDrawers();
                return true;
            }
        });

        //toolbar + burger
        configureToolbar();

        SearchFragment searchFragment = new SearchFragment(this);
        pegarFragment(searchFragment);


    }

    private void pegarFragment(Fragment fragment) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentcontainer,fragment);
        fragmentTransaction.commit();
    }

    private void pegarFragmentAddATBS(Fragment fragment){
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragmentcontainer,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void configureToolbar() {
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, binding.searchDrawerL, toolbar, R.string.open_drawers, R.string.close_drawers);
        binding.searchDrawerL.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }


    //Desde SearchFragment. onClick item. Intent a ITEMACTIVITY
    @Override
    public void onClickSearchFResult(String id) {
        Intent intent = new Intent(SearchActivity.this, ItemActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(KEY_ID, id);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}