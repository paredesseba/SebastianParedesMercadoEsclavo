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

import com.example.sebastianparedesmercadoesclavo.R;
import com.example.sebastianparedesmercadoesclavo.databinding.ActivityMainBinding;
import com.example.sebastianparedesmercadoesclavo.model.Result;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.FirebaseFirestore;

import static com.example.sebastianparedesmercadoesclavo.view.ResultDetailFragment.KEY_ID;


public class MainActivity extends AppCompatActivity implements ResultListFragment.ResultListFragmentListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActivityMainBinding binding;
    // Access a Cloud Firestore instance from your Activity
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //inicializo la UI
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.mainNavView);
        drawerLayout = findViewById(R.id.mainDrawerL);
        db = FirebaseFirestore.getInstance();

        //listener de menu lateral
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menuItemSobreNosotros:
                        SobreNosotrosFragment sobreNosotrosFragment = new SobreNosotrosFragment();
                        pegarFragment(sobreNosotrosFragment);
                        break;

                    case R.id.menuItemFavoritos:
                        FavoritosFragment favoritosFragment = new FavoritosFragment();
                        pegarFragment(favoritosFragment);
                        break;

                    case R.id.menuItemBuscar:
                        onClickFAB();
                        break;

                    case R.id.menuItemHistorial:
                        HistorialFragment historialFragment = new HistorialFragment();
                        pegarFragment(historialFragment);
                        break;
                }
                drawerLayout.closeDrawers();
                return true;
            }
        });

        //toolbar + burger king
        configureToolbar();

        //fragment con todos los recyclers del home
        ResultListFragment resultListFragment = new ResultListFragment();
        //lo pego sin metodo (sin addtobackstack)
        pegarFragment(resultListFragment);

        //FAB
        binding.FABsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickFAB();
            }
        });

    }

    private void configureToolbar() {
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawers, R.string.close_drawers);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    private void pegarFragment(Fragment fragment) {

        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mainlayoutcontenedor, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    //ResultListFragment. Intent a ItemActivity
    @Override
    public void onClickResult(String id) {
        Intent intent = new Intent(MainActivity.this, ItemActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(KEY_ID, id);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    //Listener FAB. Intent a SearchActivity
    public void onClickFAB(){
        Intent intent = new Intent(MainActivity.this, SearchActivity.class);
        startActivity(intent);
    }
}