package app.unirio.makeyourparty.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;


import app.unirio.makeyourparty.Fragments.FeedFragment;
import app.unirio.makeyourparty.Fragments.ProfileFragment;
import app.unirio.makeyourparty.R;

public class MainActivity extends AppCompatActivity {
    //private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.rl_fragment_container, new FeedFragment());
        fragmentTransaction.commit();

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_events:
                                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                                fragmentTransaction.replace(R.id.rl_fragment_container, new FeedFragment());
                                fragmentTransaction.commit();
                                break;
                            case R.id.action_favorities:

                                break;
                            case R.id.action_profile:
                                FragmentTransaction f = getSupportFragmentManager().beginTransaction();
                                f.replace(R.id.rl_fragment_container, new ProfileFragment());
                                f.commit();
                                break;
                        }
                        return false;
                    }
                });
    }
}
