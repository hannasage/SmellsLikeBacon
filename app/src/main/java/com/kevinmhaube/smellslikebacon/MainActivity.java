package com.kevinmhaube.smellslikebacon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ListFragment.OnRecipeSelectedInterface,
        GridFragment.OnRecipeSelectedInterface {

    public static final String LIST_FRAGMENT = "list_fragment";
    public static final String VIEWPAGER_FRAGMENT = "viewpager_fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boolean isTablet = getResources().getBoolean(R.bool.is_tablet);
        if (!isTablet) {
            ListFragment savedFragment = (ListFragment) getSupportFragmentManager()
                    .findFragmentByTag(LIST_FRAGMENT);
            if (savedFragment == null) {
                ListFragment fragment = new ListFragment();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.placeHolder, fragment, LIST_FRAGMENT);
                transaction.commit();
            }
        } else {
            GridFragment savedFragment = (GridFragment) getSupportFragmentManager()
                    .findFragmentByTag(LIST_FRAGMENT);
            if (savedFragment == null) {
                GridFragment fragment = new GridFragment();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.placeHolder, fragment, LIST_FRAGMENT);
                transaction.commit();
            }
        }

    }

    @Override
    public void onListRecipeSelected(int index) {
        ViewPagerFragment fragment = new ViewPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ViewPagerFragment.KEY_RECIPE_INDEX, index);
        fragment.setArguments(bundle);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.placeHolder, fragment, VIEWPAGER_FRAGMENT);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onGridRecipeSelected(int index) {
        DualPaneFragment fragment = new DualPaneFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ViewPagerFragment.KEY_RECIPE_INDEX, index);
        fragment.setArguments(bundle);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.placeHolder, fragment, VIEWPAGER_FRAGMENT);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
