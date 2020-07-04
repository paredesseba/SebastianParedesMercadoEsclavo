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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.sebastianparedesmercadoesclavo.R;
import com.example.sebastianparedesmercadoesclavo.controller.HistorialController;
import com.example.sebastianparedesmercadoesclavo.controller.ItemController;
import com.example.sebastianparedesmercadoesclavo.databinding.ActivityMainBinding;
import com.example.sebastianparedesmercadoesclavo.model.Item;
import com.example.sebastianparedesmercadoesclavo.model.Query;
import com.example.sebastianparedesmercadoesclavo.model.Result;
import com.example.sebastianparedesmercadoesclavo.util.ResultListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import static com.example.sebastianparedesmercadoesclavo.view.ResultDetailFragment.KEY_ID;


public class MainActivity extends AppCompatActivity implements ResultListFragment.ResultListFragmentListener,
        SearchFragment.SearchFragmentListener, ResultDetailFragment.ItemFragmentListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActivityMainBinding binding;
    public static final String KEY_ID = "id";
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private TextView tvheadernombre;
    private TextView tvheadermail;
    private ImageView ivheader;


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
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

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
                        onClickBuscar();
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

        View headerView = binding.mainNavView.getHeaderView(0);
        ivheader = headerView.findViewById(R.id.ivheader);
        tvheadermail = headerView.findViewById(R.id.headermail);
        tvheadernombre = headerView.findViewById(R.id.headerusername);

        tvheadermail.setText(currentUser.getEmail());
        if (currentUser.getDisplayName() != null){
            tvheadernombre.setText(currentUser.getDisplayName());
        }
        else {
            tvheadernombre.setText("Hola!!");
        }
        if (currentUser.getPhotoUrl() != null){
            Glide.with(this).load(currentUser.getPhotoUrl()).into(ivheader);
        }

        //fragment con todos los recyclers del home
        ResultListFragment resultListFragment = new ResultListFragment();
        //lo pego sin metodo (sin addtobackstack)
        pegarFragment(resultListFragment);

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

    //ResultListFragment. Pega ResultDetailFragment
    @Override
    public void onClickResult(String id) {
        Bundle bundleFragment = new Bundle();
        bundleFragment.putString(KEY_ID, id);
        ResultDetailFragment resultDetailFragment = new ResultDetailFragment();
        resultDetailFragment.setArguments(bundleFragment);
        pegarFragment(resultDetailFragment);
    }

    //Listener FAB. Intent a SearchActivity
    public void onClickBuscar(){
        SearchFragment searchFragment = new SearchFragment();
        pegarFragment(searchFragment);
    }

    @Override
    public void onClickSearchFResult(String id) {
        Bundle bundleFragment = new Bundle();
        bundleFragment.putString(KEY_ID, id);
        ResultDetailFragment resultDetailFragment = new ResultDetailFragment();
        resultDetailFragment.setArguments(bundleFragment);
        pegarFragment(resultDetailFragment);
    }

    @Override
    public void agregarHistorial(Query query) {
        HistorialController historialController = new HistorialController();
        if (currentUser != null){
            historialController.agregarHistorial(query, currentUser, new ResultListener<Query>() {
                @Override
                public void onFinish(Query query) {
                    //se agrega la busqueda al historial, no hago nada
                }
            });
        }
    }

    @Override
    public void onClickUbicacion(Bundle bundle1) {
        Intent intent = new Intent(MainActivity.this, LocationSellerActivity.class);
        intent.putExtras(bundle1);
        startActivity(intent);
    }

    @Override
    public void onClickAddFav(Item item) {
        ItemController itemController = new ItemController();
        if (currentUser != null){
            itemController.agregarItemAFavoritosFirestore(item, currentUser, new ResultListener<Item>() {
                @Override
                public void onFinish(Item result) {
                    Toast.makeText(MainActivity.this, "Articulo agregado a favoritos", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}