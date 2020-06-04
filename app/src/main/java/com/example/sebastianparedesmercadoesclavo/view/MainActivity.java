package com.example.sebastianparedesmercadoesclavo.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.sebastianparedesmercadoesclavo.R;
import com.example.sebastianparedesmercadoesclavo.model.Result;
import com.google.android.material.navigation.NavigationView;

import static com.example.sebastianparedesmercadoesclavo.view.ResultDetailFragment.KEY_RESULT;


public class MainActivity extends AppCompatActivity implements ResultListFragment.ResultListFragmentListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inicializo la UI
        navigationView = findViewById(R.id.mainNavView);
        drawerLayout = findViewById(R.id.mainDrawerL);

        //listener de menu lateral
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menuItemSobreNosotros:
                        SobreNosotrosFragment sobreNosotrosFragment = new SobreNosotrosFragment();
                        pegarFragment(sobreNosotrosFragment);
                        break;
                }
                drawerLayout.closeDrawers();
                return true;
            }
        });

        //fragment con todos los recyclers del home
        ResultListFragment resultListFragment = new ResultListFragment();
        //lo pego sin metodo (sin addtobackstack)
        pegarFragment(resultListFragment);
    }

    private void pegarFragment(Fragment fragment) {

        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mainlayoutcontenedor,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onClickResult(Result result) {
        ResultDetailFragment resultDetailFragment = new ResultDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_RESULT,result);
        resultDetailFragment.setArguments(bundle);
        pegarFragment(resultDetailFragment);

    }
}