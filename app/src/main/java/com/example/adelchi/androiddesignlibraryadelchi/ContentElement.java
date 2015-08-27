package com.example.adelchi.androiddesignlibraryadelchi;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContentElement extends Fragment {
    private TextInputLayout textInputLayoutInsertName;
    private TextInputLayout textInputLayoutInsertSurname;
    private FloatingActionButton mFabClean;
    private EditText editTextName;
    private EditText editTextSurname;

    public ContentElement() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_content_element, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editTextName = (EditText)view.findViewById(R.id.insertName);
        editTextSurname = (EditText)view.findViewById(R.id.insertSurname);
        textInputLayoutInsertName = (TextInputLayout)view.findViewById(R.id.insertNameTextInput);
        textInputLayoutInsertSurname = (TextInputLayout)view.findViewById(R.id.insertSurnameTextInput);

        editTextName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(((EditText)v).getText().toString().equals("")){
                        textInputLayoutInsertName.setError(getString(R.string.field_empty));
                    }
                }else{
                    textInputLayoutInsertName.setError(null);
                }
            }
        });

        editTextSurname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(((EditText)v).getText().toString().equals("")){
                        textInputLayoutInsertSurname.setError(getString(R.string.field_empty));
                    }
                }else{
                    textInputLayoutInsertSurname.setError(null);
                }
            }
        });

        mFabClean = (FloatingActionButton)view.findViewById(R.id.fabClean);
        mFabClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextName.setText("");
                editTextSurname.setText("");
            }
        });

    }
}
