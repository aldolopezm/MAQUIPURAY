package com.maquipuray.maquipuray_apk.ui.main;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.maquipuray.maquipuray_apk.R;
import com.maquipuray.maquipuray_apk.ui.menucategorias.BusquedasFragment;
import com.maquipuray.maquipuray_apk.ui.menucuenta.CuentaFragment;
import com.maquipuray.maquipuray_apk.ui.menudestacados.DestacadosFragment;
import com.maquipuray.maquipuray_apk.ui.menumispedidos.MisPedidosFragment;

public class MainActivity extends AppCompatActivity {

    Fragment destacadosFragment = new DestacadosFragment();
    Fragment busquedasFragment = new BusquedasFragment();

    Fragment activeFragment = destacadosFragment;

    FragmentManager fragmentManager = getSupportFragmentManager();
    private BottomNavigationView bottomNavigationView;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Intent intentGetStarted;



            Fragment fragmentoGenerico = null;


            switch (item.getItemId()) {
                case R.id.navigationMyProfile:

                    fragmentoGenerico = new CuentaFragment();
                    break;

                case R.id.navigationMisPedidos:

                    fragmentoGenerico = new MisPedidosFragment();
                    break;

                case R.id.navigationHome:
                    fragmentoGenerico = new DestacadosFragment();
                    break;
                case R.id.navigationSearch:
//                    intentGetStarted = new Intent(MainActivity.this, GamePlayActivity.class);
//                    startActivity(intentGetStarted);
                    fragmentoGenerico = new BusquedasFragment();
                    break;
//                case R.id.navigationMenu:
//                    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//                    drawer.openDrawer(GravityCompat.START);
//                    return true;
            }


            if (fragmentoGenerico != null) {
                fragmentManager

                        .beginTransaction()
                        .replace(R.id.container, fragmentoGenerico)
                        .commit();

                loadFragment(fragmentoGenerico);
            }

            // Setear título actual
            setTitle(item.getTitle());

            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) bottomNavigationView.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehavior());


        Intent myIntent = getIntent(); // gets the previously created intent

        Drawable circleDrawable = getResources().getDrawable(R.drawable.ic_taxi);

        int menuMisPedidosOMenuDestacados=0;
        if (myIntent != null) {
            menuMisPedidosOMenuDestacados = myIntent.getIntExtra("pedido_confirmado",0);

        }

        if (menuMisPedidosOMenuDestacados==0)
            bottomNavigationView.setSelectedItemId(R.id.navigationHome);
        else
            bottomNavigationView.setSelectedItemId(R.id.navigationMisPedidos);

    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}
