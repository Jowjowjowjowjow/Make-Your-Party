package app.unirio.makeyourparty.Fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;

import com.facebook.drawee.backends.pipeline.Fresco;

import app.unirio.makeyourparty.Domain.User;
import app.unirio.makeyourparty.R;

/**
 * Created by Gabriel on 19/11/2016.
 */

public class EditProfileFragment extends Fragment {
    private Toolbar toolbar;
    private EditText inputName, inputEmail, inputPhone;
    private TextInputLayout inputLayoutName, inputLayoutEmail, inputLayoutPhone;
    private User user;
    private FloatingActionButton fab;

    private void findViews(View view){
        inputLayoutName = (TextInputLayout) view.findViewById(R.id.input_layout_name);
        inputLayoutEmail = (TextInputLayout) view.findViewById(R.id.input_layout_email);
        inputLayoutPhone = (TextInputLayout) view.findViewById(R.id.input_layout_phone);
        inputName = (EditText) view.findViewById(R.id.input_name);
        inputEmail = (EditText) view.findViewById(R.id.input_email);
        inputPhone = (EditText) view.findViewById(R.id.input_phone);
        fab = (FloatingActionButton) view.findViewById(R.id.fab_edit);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar_edit);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Fresco.initialize(getActivity().getApplicationContext());
        View view = inflater.inflate(R.layout.activity_edit_profile, container, false);
        user = getUserInformation();
        findViews(view);
        //toolbar.setTitle(user.getName());

        inputName.addTextChangedListener(new MyTextWatcher(inputName));
        inputEmail.addTextChangedListener(new MyTextWatcher(inputEmail));
        inputPhone.addTextChangedListener(new MyTextWatcher(inputPhone));

        inputName.setText(user.getName());
        inputEmail.setText(user.getEmail());
        inputPhone.setText(user.getPhone());
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Perfil alterado!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                submitForm(user);
            }
        });

        return view;
    }

    private User getUserInformation(){
        Bundle bundle = new Bundle();
        User u = (User) bundle.getSerializable("USER_KEY");
        try{
            Log.i("getUserInformation()", u.getName());
        } catch (Exception e) {
            Log.i("getUserInformation()", e.toString());
        }

        return u;
    }

    private void submitForm(User u) {
        if (!validateName()) {
            return;
        }

        if (!validateEmail()) {
            return;
        }

        if (!validatePhone()) {
            return;
        }

        u.setLogin(inputName.getText().toString());
        u.setPhone(inputPhone.getText().toString());
        u.setEmail(inputEmail.getText().toString());

        Log.i("EditProfile", u.getLogin() + " " + u.getPhone() + " " + u.getEmail() );
    }

    private boolean validateName() {
        if (inputName.getText().toString().trim().isEmpty()) {
            inputLayoutName.setError(getString(R.string.err_msg_name));
            requestFocus(inputName);
            return false;
        } else {
            inputLayoutName.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateEmail() {
        String email = inputEmail.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            inputLayoutEmail.setError(getString(R.string.err_msg_email));
            requestFocus(inputEmail);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePhone() {
        if (inputPhone.getText().toString().trim().isEmpty()) {
            inputLayoutPhone.setError(getString(R.string.err_msg_phone));
            requestFocus(inputPhone);
            return false;
        } else {
            inputLayoutPhone.setErrorEnabled(false);
        }

        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.input_name:
                    validateName();
                    break;
                case R.id.input_email:
                    validateEmail();
                    break;
                case R.id.input_phone:
                    validatePhone();
                    break;
            }
        }
    }
}
