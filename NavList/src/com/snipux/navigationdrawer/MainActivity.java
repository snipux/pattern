package com.snipux.navigationdrawer;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.snipux.navigationdrawer.adapter.MenuAdapter;
import com.snipux.navigationdrawer.adapter.MenuModel;

public class MainActivity extends Activity {

	private ListView listView;
	private DrawerLayout drawerLayout;
	private MyActionBarDrawerToggle drawerToggle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		listView = (ListView) findViewById(R.id.drawer);
		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);

		MenuAdapter adapter = new MenuAdapter(this);
		adapter.add(new MenuModel("Main Menu", 0, 0, true));
		adapter.add(new MenuModel("Profile",
				R.drawable.social_person, 0, false));
		adapter.add(new MenuModel("Social",
				R.drawable.social_group, 0, false));
		adapter.add(new MenuModel("Inbox",
				R.drawable.content_unread, 12, false));
		adapter.add(new MenuModel("Chat",
				R.drawable.social_chat, 4, false));
		adapter.add(new MenuModel("Other", 0, 0, true));
		adapter.add(new MenuModel("Setting",
				R.drawable.action_settings, 0, false));
		
		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterview, View view,
					int position, long id) {
				Toast.makeText(
						MainActivity.this,
						((MenuModel) adapterview.getItemAtPosition(position))
								.getTitle(), Toast.LENGTH_LONG).show();
			}

		});
		
		drawerToggle = new MyActionBarDrawerToggle(this, drawerLayout);
		drawerLayout.setDrawerListener(drawerToggle);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
	}

	private class MyActionBarDrawerToggle extends ActionBarDrawerToggle{
		
		public MyActionBarDrawerToggle(Activity mActivity,DrawerLayout mDrawerLayout){
			super(
			    mActivity,
			    mDrawerLayout, 
			    R.drawable.ic_drawer,
			    R.string.app_name, 
			    R.string.app_name);
		}
		
		@Override
		public void onDrawerClosed(View view) {
			invalidateOptionsMenu();
		}

		@Override
		public void onDrawerOpened(View drawerView) {
			invalidateOptionsMenu();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		drawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		drawerToggle.onConfigurationChanged(newConfig);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    if(drawerToggle.onOptionsItemSelected(item)) {
	    	return true;
	    }
	    return super.onOptionsItemSelected(item);
	}
	
	@Override
    public boolean onPrepareOptionsMenu(Menu menu) {
		boolean drawerOpen = drawerLayout.isDrawerOpen(listView);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }
}
