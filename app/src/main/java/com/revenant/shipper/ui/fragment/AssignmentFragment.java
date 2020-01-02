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
public class AssignmentFragment extends Fragment {


    public AssignmentFragment() {
        // Required empty public constructor
    }

    public static AssignmentFragment newInstance(String param1) {
        AssignmentFragment fragment = new AssignmentFragment();
        Bundle args = new Bundle();
        args.putString(param1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_assignment, container, false);
    }

}
