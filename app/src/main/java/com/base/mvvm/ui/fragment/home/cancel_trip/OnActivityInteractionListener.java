package com.base.mvvm.ui.fragment.home.cancel_trip;

import android.view.Menu;

import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import eu.davidea.flexibleadapter.SelectableAdapter;

/**
 * This interface must be implemented by activities that contain this
 * fragment to allow an interaction in this fragment to be communicated
 * to the activity and potentially other fragments contained in that
 * activity.
 * <p/>
 * See the Android Training lesson <a href=
 * "http://developer.android.com/training/basics/fragments/communicating.html"
 * >Communicating with Other Fragments</a> for more information.
 */
public interface OnActivityInteractionListener {

    void onActivityChange(SwipeRefreshLayout swipeRefreshLayout, RecyclerView recyclerView,
                          @SelectableAdapter.Mode int mode);

    void initSearchView(final Menu menu);

}