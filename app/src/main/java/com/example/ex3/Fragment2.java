package com.example.ex3;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Fragment2 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_2, container, false);

        Button button = (Button) view.findViewById(R.id.button);
        getParentFragmentManager().setFragmentResultListener("r1Key", this,
                new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String resString = result.getString("b1Key");
                TextView text = (TextView) view.findViewById(R.id.text);
                text.setText(resString);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle result = new Bundle();
                EditText text = (EditText) view.findViewById(R.id.editText);
                result.putString("b2Key", text.getText().toString());
                getParentFragmentManager().setFragmentResult("r2Key", result);

                getActivity().getSupportFragmentManager().beginTransaction().
                        replace(R.id.fragment, Fragment1.class, null).addToBackStack(null).commit();
            }
        });

        return view;
    }
}