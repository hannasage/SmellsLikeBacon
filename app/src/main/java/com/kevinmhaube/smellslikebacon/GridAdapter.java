package com.kevinmhaube.smellslikebacon;

public class GridAdapter extends RecyclerAdapter {

    private final GridFragment.OnRecipeSelectedInterface mListener;

    public GridAdapter(GridFragment.OnRecipeSelectedInterface listener) {
        mListener = listener;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.gried_item;
    }

    @Override
    protected void onRecipeSelected(int index) {
        mListener.onGridRecipeSelected(index);
    }

}
