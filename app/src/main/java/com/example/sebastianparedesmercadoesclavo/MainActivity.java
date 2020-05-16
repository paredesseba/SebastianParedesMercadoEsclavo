package com.example.sebastianparedesmercadoesclavo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

import static com.example.sebastianparedesmercadoesclavo.DetalleProductoFragment.CLAVE_PRODUCTO;


public class MainActivity extends AppCompatActivity implements ListaProductosFragment.ListaProductosFragmentListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationView = findViewById(R.id.mainNavView);
        drawerLayout = findViewById(R.id.mainDrawerL);

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

        ListaProductosFragment listaProductosFragment = new ListaProductosFragment();


        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.mainlayoutcontenedor,listaProductosFragment)
                .commit();
    }

    private void pegarFragment(Fragment fragment) {

        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mainlayoutcontenedor,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onClickProductoFragment(Producto producto) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(CLAVE_PRODUCTO,producto);
        DetalleProductoFragment detalleProductoFragment = new DetalleProductoFragment();
        detalleProductoFragment.setArguments(bundle);
        pegarFragment(detalleProductoFragment);
    }
}