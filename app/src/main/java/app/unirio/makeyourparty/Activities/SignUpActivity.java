package app.unirio.makeyourparty.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

import java.util.Date;

import app.unirio.makeyourparty.DAO.ConfiguracaoFirebase;
import app.unirio.makeyourparty.Domain.User;
import app.unirio.makeyourparty.Helper.Base64Custom;
import app.unirio.makeyourparty.Helper.Preferences;
import app.unirio.makeyourparty.R;

public class SignUpActivity extends AppCompatActivity {

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
    private EditText editTextBirthDateRegister;
    private Button buttonRegisterAccount;
    private Button buttonCleanForm;
    private User user;
    private FirebaseAuth autentication;

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
        editTextBirthDateRegister = (EditText) findViewById(R.id.editTextBirthDateRegister);
        buttonRegisterAccount = (Button) findViewById(R.id.buttonRegisterAccount);
        buttonCleanForm = (Button) findViewById(R.id.buttonCleanForm);

        buttonCleanForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextAddressRegister.setText("");
                editTextBirthDateRegister.setText("");
                editTextCityRegister.setText("");
                editTextConfirmPasswordRegister.setText("");
                editTextCpfRegister.setText("");
                editTextEmailRegister.setText("");
                editTextNameRegister.setText("");
                editTextPasswordRegister.setText("");
                editTextPhoneRegister.setText("");
                editTextSiteRegister.setText("");
                editTextStateRegister.setText("");
            }
        });

        buttonRegisterAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextPasswordRegister.getText().toString().equals(editTextConfirmPasswordRegister.getText().toString())){
                    try {
                        /**
                         * TODO: PASSAR A DATA DE NASCIMENTO PARA CALENDAR
                         * TODO: FAZER UMA FUNÇÃO PRA TRANSFORMAR A DATA DO PADRÃO US PRO PADRÃO PT-BR
                         * TODO: FAZER VERIFICAÇÃO DE CADA CAMPO INDIVIDUAL PARA VERIFICAR SE ESTÁ VAZIO
                         * TODO: PASSAR CIDADE E ESTADO PARA CAMPOS SELECT
                         */
                        Date bornDate = new Date(editTextBirthDateRegister.getText().toString());
                        user = new User(Long.parseLong(editTextCpfRegister.getText().toString()), editTextEmailRegister.getText().toString(),
                                editTextPasswordRegister.getText().toString(), editTextNameRegister.getText().toString(),
                                editTextAddressRegister.getText().toString(), editTextPhoneRegister.getText().toString(),
                                editTextCityRegister.getText().toString(), editTextStateRegister.getText().toString(),
                                editTextSiteRegister.getText().toString(), bornDate);
                        registerUser();
                    }catch(Exception e){
                        Toast.makeText(SignUpActivity.this, "Ocorreu um erro, verifique os dados e tente novamente.", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(SignUpActivity.this, "As senhas não correspondem",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void registerUser(){

        autentication = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autentication.createUserWithEmailAndPassword(user.getEmail(), user.getPassword()).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Usuário cadastrado com sucesso!", Toast.LENGTH_LONG).show();

                    String userIdentifier = Base64Custom.encodeBase64(user.getEmail());
                    FirebaseUser firebaseUser = task.getResult().getUser();
                    user.setId(userIdentifier);
                    user.save();

                    Preferences preferences = new Preferences(SignUpActivity.this);
                    preferences.saveUserPreferences(userIdentifier, user.getName());
                    openUserLogin();
                }else{
                    String errorException = "";
                    try{
                        throw task.getException();
                    }catch(FirebaseAuthWeakPasswordException e){
                        errorException = "Digite uma senha mais forte, contendo 8 caracteres de letras e números";
                    }catch (FirebaseAuthInvalidCredentialsException e ){
                        errorException = "O e-mail digitado é inválido, digite um novo e-mail.";
                    }catch (FirebaseAuthUserCollisionException e ){
                        errorException = "Esse e-mail já está cadastrado no sistema.";
                    }catch (Exception e){
                        errorException = "Erro ao efetuar o cadastro!";
                        e.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext(), "Erro: " +errorException, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void openUserLogin(){
        Intent intent = new Intent(SignUpActivity.this, Login.class);
        startActivity(intent);
        finish();
    }

}
