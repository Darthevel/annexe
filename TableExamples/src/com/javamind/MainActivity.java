package com.javamind;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;



/**
 * Classe principale qui hérite de {@link SherlockActivity} afin de beneficier des memes 
 * menus sur les anciennes versions d'Android.
 * 
 * @author ehret_g
 *
 */
public class MainActivity extends Activity implements TabListener {
    
    private TableLayoutFragment frag1 = new TableLayoutFragment("Fragment 1", 1);
    private TableLayoutFragment frag2 = new TableLayoutFragment("Fragment 2", 2);
    private TableJavaFragment frag3 = new TableJavaFragment();
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
	
	setContentView(R.layout.activity_main);
	
	//Configuration de la barre d'onglet
	getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	getActionBar().addTab(getActionBar().newTab().setText(frag1.getName()).setTabListener(this));
	getActionBar().addTab(getActionBar().newTab().setText(frag2.getName()).setTabListener(this));
	getActionBar().addTab(getActionBar().newTab().setText(frag3.getName()).setTabListener(this));
	
    }

    

    @Override
    public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {
	Toast.makeText(this, tab.getText() + " selected", Toast.LENGTH_SHORT).show();
	if(tab.getText().equals(frag1.getName())){
	    ft.replace(R.id.fragmentContainer, frag1);
	}
	else if(tab.getText().equals(frag2.getName())){
	    ft.replace(R.id.fragmentContainer, frag2);
	}
	else if(tab.getText().equals(frag3.getName())){
	    ft.replace(R.id.fragmentContainer, frag3);
	}
    }

    @Override
    public void onTabUnselected(Tab tab, android.app.FragmentTransaction ft) {
	Toast.makeText(this, tab.getText() + " unselected", Toast.LENGTH_SHORT).show();
	if(tab.getText().equals(frag1.getName())){
	    ft.remove(frag1);
	}
	else if(tab.getText().equals(frag2.getName())){
	    ft.remove(frag2);
	}
	else if(tab.getText().equals(frag3.getName())){
	    ft.remove(frag3);
	}
    }

    @Override
    public void onTabReselected(Tab tab, android.app.FragmentTransaction ft) {
	Toast.makeText(this, tab.getText() + " unselected", Toast.LENGTH_SHORT).show();
    }
    
    
}
