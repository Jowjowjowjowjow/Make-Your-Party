package app.unirio.makeyourparty.Activities;

/**
 * Created by Gabriel on 19/11/2016.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import app.unirio.makeyourparty.Domain.User;
import app.unirio.makeyourparty.R;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getUserInformation();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getUserInformation().getLogin());
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Vai para activity de editar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Intent i = new Intent(ProfileActivity.this, EditProfileActivity.class);
                i.putExtra("USER", getUserInformation());
                startActivity(i);
            }
        });

        setProfile(getUserInformation());
    }

    private User getUserInformation(){
        User user = new User("Gabriel",
                "(21)98791-2241",
                "Rua Pascal, 624",
                "Rio de Janeiro",
                "Rio de Janeiro",
                "nogueiragabriel.github.io",
                "GabrielNogueira");

        return user;
    }

    private void setProfile(User u){
        TextView userNumber = (TextView) findViewById(R.id.tvNumber1);
        TextView userCity = (TextView) findViewById(R.id.city);

        userNumber.setText(u.getPhone());
        userCity.setText(u.getCity());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
