package app.unirio.makeyourparty;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.unirio.makeyourparty.DAO.ConfiguracaoFirebase;
import app.unirio.makeyourparty.Domain.User;

public class TesteSugaBancoActivity extends AppCompatActivity {

    DatabaseReference databaseReference = ConfiguracaoFirebase.getFirebase();
    private TextView txtEmail, txtNome, txtSite;
    private TextView txtEmail1, txtNome1, txtSite1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste_suga_banco);
        databaseReference.child("Users").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                User usuario;
                User usuario1;
                int count = 0;
                /*Nesse ponto seria pra retornos multiplos*/
/*
                for(DataSnapshot snap: dataSnapshot.getChildren()){
                    if(count == 0){
                        snap.get
                        Object usuario = snap.getValue();
                        txtEmail = (TextView) findViewById(R.id.textViewEmail);
                        txtNome = (TextView) findViewById(R.id.textViewName);
                        txtSite = (TextView) findViewById(R.id.textViewSite);
                        txtEmail.setText(String.valueOf(usuario.getEmail()));
                        txtNome.setText(String.valueOf(usuario.getName()));
                        txtSite.setText(String.valueOf(usuario.getSite()));
                    }
                    if(count == 1){
                        usuario1 = snap.getValue(User.class);
                        txtEmail1 = (TextView) findViewById(R.id.textViewEmail1);
                        txtNome1 = (TextView) findViewById(R.id.textViewName1);
                        txtSite1 = (TextView) findViewById(R.id.textViewSite1);
                        txtEmail1.setText(String.valueOf(usuario1.getEmail()));
                        txtNome1.setText(String.valueOf(usuario1.getName()));
                        txtSite1.setText(String.valueOf(usuario1.getSite()));
                    }
                    count++;
                }
            }*/

            /* Aqui já é retorno unico */
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
               /* User usuario = dataSnapshot.getValue(User.class);
                User usuario2 = dataSnapshot.getValue(User.class);
                txtEmail = (TextView) findViewById(R.id.textViewEmail);
                txtNome = (TextView) findViewById(R.id.textViewName);
                txtSite = (TextView) findViewById(R.id.textViewSite);

                txtEmail.setText(usuario2.getEmail());
                txtNome.setText(usuario2.getName());
                txtSite.setText(usuario2.getSite());*/
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                /*User usuario = dataSnapshot.getValue(User.class);

                User usuario2 = dataSnapshot.getValue(User.class);

                txtEmail = (TextView) findViewById(R.id.textViewEmail);
                txtNome = (TextView) findViewById(R.id.textViewName);
                txtSite = (TextView) findViewById(R.id.textViewSite);

                txtEmail.setText(usuario2.getEmail());
                txtNome.setText(usuario2.getName());
                txtSite.setText(usuario2.getSite());*/
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                /*User usuario = dataSnapshot.getValue(User.class);


                User usuario2 = dataSnapshot.getValue(User.class);
                txtEmail = (TextView) findViewById(R.id.textViewEmail);
                txtNome = (TextView) findViewById(R.id.textViewName);
                txtSite = (TextView) findViewById(R.id.textViewSite);

                txtEmail.setText(usuario2.getEmail());
                txtNome.setText(usuario2.getName());
                txtSite.setText(usuario2.getSite());*/
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "UI mamae", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
