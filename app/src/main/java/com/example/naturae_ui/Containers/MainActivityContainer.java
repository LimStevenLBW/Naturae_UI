package com.example.naturae_ui.Containers;



import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.naturae_ui.Fragments.CreateAccountFragment;
import com.example.naturae_ui.Fragments.LoginFragment;
import com.example.naturae_ui.Fragments.MapFragment;
import com.example.naturae_ui.Fragments.PostFragment;
import com.example.naturae_ui.Fragments.ProfileFragment;
import com.example.naturae_ui.R;


public class MainActivityContainer extends AppCompatActivity implements CreateAccountFragment.OnFragmentInteractionListener,
        LoginFragment.OnFragmentInteractionListener {

	/**
	 * Enable navigation on bottom bar.
	 */
	private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
			= new BottomNavigationView.OnNavigationItemSelectedListener() {

		@Override
		public boolean onNavigationItemSelected(@NonNull MenuItem item) {
			Fragment selectedFragment = null;
			switch (item.getItemId()) {
				case R.id.navigation_map:
					selectedFragment = new MapFragment();
					break;
				case R.id.navigation_post:
					selectedFragment = new PostFragment();
					break;
				case R.id.navigation_profile:
					selectedFragment = new ProfileFragment();
					break;
			}
			getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
			return true;
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Load bottom navigation bar
		BottomNavigationView navigation = findViewById(R.id.navigation);
		navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

		// Map Fragment will open first
		MapFragment mapFragment = new MapFragment();
		getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, mapFragment).commit();

	}

    @Override
    public void onFragmentInteraction() {

    }

    @Override
	public void hideKeyboard() {
		InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
		inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
	}
}
