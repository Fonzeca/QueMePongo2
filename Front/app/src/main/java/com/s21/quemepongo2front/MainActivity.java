package com.s21.quemepongo2front;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.s21.quemepongo2front.creadoresDeFragments.Preferencias.PreferenciasFragment;
import com.s21.quemepongo2front.creadoresDeFragments.Sesion.LoginActivity;
import com.s21.quemepongo2front.creadoresDeFragments.home.HomeFragment;
import com.s21.quemepongo2front.creadoresDeFragments.ubicaciones.UbicacionesFragment;
import com.s21.quemepongo2front.objetosDeLaApi.ObjetosRS.CiudadRs;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity implements HostNavigation, NavigationView.OnNavigationItemSelectedListener {

	private AppBarConfiguration mAppBarConfiguration;
	public static String token;
	public static CiudadRs ciudadPredeterminada = new CiudadRs();
	public static ArrayList<CiudadRs> ciudadesRsRecibe;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ciudadesRsRecibe = new ArrayList<CiudadRs>();

		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		DrawerLayout drawer = findViewById(R.id.drawer_layout);

		NavigationView navigationView = findViewById(R.id.nav_view);
		navigationView.setItemIconTintList(null);
		navigationView.setNavigationItemSelectedListener(this);

		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		drawer.addDrawerListener(toggle);
		toggle.syncState();

		if(savedInstanceState == null){
			navigationView.setCheckedItem(R.id.nav_home);
		}
	}

	public boolean onNavigationItemSelected(MenuItem item) {
		switch (item.getItemId()){
			case R.id.nav_home:
				changeFragment(new HomeFragment(), false);
				break;
			case R.id.nav_ubicaciones:
				changeFragment(new UbicacionesFragment(), false);
				break;
			case R.id.nav_objetos_personales:
				changeFragment(new PreferenciasFragment(), false);
				break;
			case R.id.nav_cerrar_sesion:
				MainActivity.token = null;
				Intent intent = new Intent(this, LoginActivity.class);
				startActivity(intent);
				break;
		}

		DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawerLayout.closeDrawer(GravityCompat.START);
		return true;
	}

	public int changeFragment(@NotNull Fragment fragment, boolean addToBackStack) {
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, fragment);
		if(addToBackStack){
			transaction.addToBackStack(null);
		}
		return transaction.commit();
	}

}

