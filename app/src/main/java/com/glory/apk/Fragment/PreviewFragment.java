package com.glory.apk.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.glory.apk.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class PreviewFragment extends Fragment {

    public PreviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_preview, container, false);
        return root;

    }
}
