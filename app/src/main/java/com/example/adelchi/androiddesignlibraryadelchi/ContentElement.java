package com.example.adelchi.androiddesignlibraryadelchi;


import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;


/**
 * Fragment che contiene alcuni elementi del design library
 * quali TextInputLayout e FloatingActionButton + Snackbar (con
 * l'aggiunta di un plusante azione)
 */
public class ContentElement extends Fragment {
    private TextInputLayout textInputLayoutInsertName;
    private TextInputLayout textInputLayoutInsertSurname;
    private FloatingActionButton mFabClean;
    private EditText editTextName;
    private EditText editTextSurname;
    private CoordinatorLayout coordinatorLayout;

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

        coordinatorLayout = (CoordinatorLayout)view.findViewById(R.id.coorLayout);

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
                final String name = editTextName.getText().toString();
                editTextName.setText("");
                final String surname = editTextSurname.getText().toString();
                editTextSurname.setText("");
                editTextName.requestFocus();

                /**
                 * è importante passare come genitore il coordinatorlayout se si hai il floatingactionbutton
                 * per sincronizzare il movimento del pulsante con l'entrata dello snackbar
                 */
                Snackbar.make(coordinatorLayout, "Elementi cancellati", Snackbar.LENGTH_LONG).setAction("Annnulla", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        editTextName.setText(name);
                        editTextSurname.setText(surname);
                    }
                }).show();
            }
        });

    }
}
