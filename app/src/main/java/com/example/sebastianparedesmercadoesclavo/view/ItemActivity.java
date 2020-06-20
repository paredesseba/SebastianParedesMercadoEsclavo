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
import android.widget.Toast;

import com.example.sebastianparedesmercadoesclavo.R;
import com.example.sebastianparedesmercadoesclavo.controller.ItemController;
import com.example.sebastianparedesmercadoesclavo.databinding.ActivityItemBinding;
import com.example.sebastianparedesmercadoesclavo.model.Item;
import com.example.sebastianparedesmercadoesclavo.util.ResultListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import static com.example.sebastianparedesmercadoesclavo.view.ResultDetailFragment.KEY_ID;

public class ItemActivity extends AppCompatActivity implements ResultDetailFragment.ItemFragmentListener {

    public static final String KEY_ID = "id";
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActivityItemBinding binding;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;



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
        ResultDetailFragment resultDetailFragment = new ResultDetailFragment(this);
        resultDetailFragment.setArguments(bundleFragment);

        pegarFragment(resultDetailFragment);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();


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

    @Override
    public void onClickUbicacion(Bundle bundle1) {
        Intent intent = new Intent(ItemActivity.this, LocationSellerActivity.class);
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
                    Toast.makeText(ItemActivity.this, "Articulo agregado a favoritos", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}