package app.unirio.makeyourparty.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

import app.unirio.makeyourparty.Domain.User;
import app.unirio.makeyourparty.R;

public class SignInActivity extends AppCompatActivity {

    private EditText editTextEmailRegister;
    private EditText editTextCpfRegister;
    private EditText editTextNameRegister;
    private EditText editTextPasswordRegister;
    private EditText editTextConfirmPasswordRegister;
    private EditText editTextAddressRegister;
    private EditText editTextPhoneRegister;
    private EditText editTextCityRegister;
    private EditText editTextStateRegister;
    private EditText editTextSiteRegister;
    private EditText editTextBornDateRegister;
    private Button buttonRegisterAccount;
    private Button buttonCleanForm;
    private User user;


    /**
     * TODO: Mudar os campos de cidade e estado para algum tipo de lista.
     * TODO: Criar algum filtro para os campos dataNascimento e telefone?
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        editTextEmailRegister = (EditText) findViewById(R.id.editTextEmailRegister);
        editTextCpfRegister = (EditText) findViewById(R.id.editTextCpfRegister);
        editTextNameRegister = (EditText) findViewById(R.id.editTextNameRegister);
        editTextPasswordRegister = (EditText) findViewById(R.id.editTextPasswordRegister);
        editTextConfirmPasswordRegister = (EditText) findViewById(R.id.editTextConfirmPasswordRegister);
        editTextAddressRegister = (EditText) findViewById(R.id.editTextAddressRegister);
        editTextPhoneRegister = (EditText) findViewById(R.id.editTextPhoneRegister);
        editTextCityRegister = (EditText) findViewById(R.id.editTextCityRegister);
        editTextStateRegister = (EditText) findViewById(R.id.editTextStateRegister);
        editTextSiteRegister = (EditText) findViewById(R.id.editTextSiteRegister);
        editTextBornDateRegister = (EditText) findViewById(R.id.editTextBornDateRegister);
        buttonRegisterAccount = (Button) findViewById(R.id.buttonRegisterAccount);
        buttonCleanForm = (Button) findViewById(R.id.buttonCleanForm);

        buttonRegisterAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextPasswordRegister.getText().toString().equals(editTextConfirmPasswordRegister.getText().toString())){
                    Date bornDate = new Date(editTextBornDateRegister.getText().toString());
                    user = new User(Integer.parseInt(editTextCpfRegister.getText().toString()), editTextEmailRegister.getText().toString(),
                            editTextPasswordRegister.getText().toString(), editTextNameRegister.getText().toString(),
                            editTextAddressRegister.getText().toString(), editTextPhoneRegister.getText().toString(),
                            editTextCityRegister.getText().toString(), editTextStateRegister.getText().toString(),
                            editTextSiteRegister.getText().toString(), bornDate);
                }else{
                    Toast.makeText(SignInActivity.this, "As senhas não correspondem",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
