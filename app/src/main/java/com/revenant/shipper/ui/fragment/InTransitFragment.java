package com.revenant.shipper.ui.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.revenant.shipper.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class InTransitFragment extends Fragment {


    public InTransitFragment() {
        // Required empty public constructor
    }

    public static InTransitFragment newInstance(String param1) {
        InTransitFragment fragment = new InTransitFragment();
        Bundle args = new Bundle();
        args.putString(param1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_in_transit, container, false);
    }

}
