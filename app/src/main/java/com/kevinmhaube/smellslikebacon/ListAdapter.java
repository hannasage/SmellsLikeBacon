package com.kevinmhaube.smellslikebacon;

public class ListAdapter extends RecyclerAdapter {

    private final ListFragment.OnRecipeSelectedInterface mListner;
    public ListAdapter(ListFragment.OnRecipeSelectedInterface listener) {
        mListner = listener;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.list_item;
    }

    @Override
    protected void onRecipeSelected(int index) {
        mListner.onListRecipeSelected(index);
    }

}
