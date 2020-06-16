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

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.sebastianparedesmercadoesclavo.R;
import com.example.sebastianparedesmercadoesclavo.databinding.ActivityItemBinding;
import com.google.android.material.navigation.NavigationView;

import static com.example.sebastianparedesmercadoesclavo.view.ResultDetailFragment.KEY_ID;

public class ItemActivity extends AppCompatActivity {

    public static final String KEY_ID = "id";
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActivityItemBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityItemBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        toolbar = findViewById(R.id.toolbar);

        //listener de menu lateral
        binding.itemNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menuItemSobreNosotros:
                        SobreNosotrosFragment sobreNosotrosFragment = new SobreNosotrosFragment();
                        pegarFragment(sobreNosotrosFragment);
                        break;
                }
                binding.itemDrawerL.closeDrawers();
                return true;
            }
        });

        //toolbar + burger
        configureToolbar();

        String id = getIntent().getExtras().getString(KEY_ID);

        Bundle bundleFragment = new Bundle();
        bundleFragment.putString(KEY_ID, id);
        ResultDetailFragment resultDetailFragment = new ResultDetailFragment();
        resultDetailFragment.setArguments(bundleFragment);

        pegarFragment(resultDetailFragment);


    }

    public void pegarFragment(Fragment fragment) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.containeritem, fragment);
        fragmentTransaction.commit();
    }

    private void configureToolbar(){
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, binding.itemDrawerL, toolbar, R.string.open_drawers, R.string.close_drawers);
        binding.itemDrawerL.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

}