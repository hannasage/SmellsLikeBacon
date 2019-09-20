package com.kevinmhaube.smellslikebacon;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class DualPaneFragment extends Fragment {
    private static final String INGREDIENTS_FRAGMENT = "ingredients_fragment";
    private static final String DIRECTIONS_FRAGMENT = "directions_fragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dualpane, container, false);
        int index = getArguments().getInt(ViewPagerFragment.KEY_RECIPE_INDEX);
        getActivity().setTitle(Recipes.names[index]);

        FragmentManager fragmentManager = getChildFragmentManager();

        IngredientsFragment savedIngredientsFragment = (IngredientsFragment) fragmentManager.findFragmentByTag(INGREDIENTS_FRAGMENT);
        if (savedIngredientsFragment == null) {
            IngredientsFragment ingredientsFragment = new IngredientsFragment();

            Bundle bundle = new Bundle();

            bundle.putInt(ViewPagerFragment.KEY_RECIPE_INDEX, index);
            ingredientsFragment.setArguments(bundle);
            
            fragmentManager.beginTransaction()
                    .add(R.id.leftPlaceholder, ingredientsFragment, INGREDIENTS_FRAGMENT)
                    .commit();
        }

        DirectionsFragment savedDirectionsFragment = (DirectionsFragment) fragmentManager.findFragmentByTag(DIRECTIONS_FRAGMENT);
        if (savedDirectionsFragment == null) {
            DirectionsFragment directionsFragment = new DirectionsFragment();

            Bundle bundle = new Bundle();

            bundle.putInt(ViewPagerFragment.KEY_RECIPE_INDEX, index);
            directionsFragment.setArguments(bundle);

            fragmentManager.beginTransaction()
                    .add(R.id.rightPlaceholder, directionsFragment, DIRECTIONS_FRAGMENT)
                    .commit();
        }

        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        getActivity().setTitle(getResources().getString(R.string.app_name));
    }
}
