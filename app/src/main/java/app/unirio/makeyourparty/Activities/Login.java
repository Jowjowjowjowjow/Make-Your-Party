package app.unirio.makeyourparty.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import app.unirio.makeyourparty.DAO.ConfiguracaoFirebase;
import app.unirio.makeyourparty.Domain.User;
import app.unirio.makeyourparty.R;
import app.unirio.makeyourparty.TesteSugaBancoActivity;

public class Login extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewAbreCadastro;
    private Button btnLogin;
    private FirebaseAuth autentication;
    private User user;
    private TextView textViewSugaBanco;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textViewAbreCadastro = (TextView) findViewById(R.id.textView_SignUp);
        btnLogin = (Button) findViewById(R.id.buttonFazerLogin);
        textViewSugaBanco = (TextView) findViewById(R.id.textView_SugaBanco);

        textViewSugaBanco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, TesteSugaBancoActivity.class);
                startActivity(intent);
            }
        });

        //Click listener do botão "Fazer login"
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editTextEmail.getText().toString().equals("") && !editTextPassword.getText().toString().equals("")){

                    user = new User();
                    user.setEmail(editTextEmail.getText().toString());
                    user.setPassword(editTextPassword.getText().toString());
                    validateLogin();
                }else{
                    Toast.makeText(Login.this,"Preencha os campos de e-mail e senha!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        textViewAbreCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignUp();
            }
        });
    }

    private void validateLogin(){
        autentication = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autentication.signInWithEmailAndPassword(user.getEmail(), user.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    openMainActivity();
                    Toast.makeText(Login.this,"Login efetuado com sucesso", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Login.this,"Usuário ou senha inválidos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void openMainActivity(){
        Intent intentOpenMainActivity = new Intent(Login.this, MainActivity.class);
        startActivity(intentOpenMainActivity);
    }

    public void openSignUp(){
        Intent intent = new Intent(Login.this, SignUpActivity.class);
        startActivity(intent);
    }
}

