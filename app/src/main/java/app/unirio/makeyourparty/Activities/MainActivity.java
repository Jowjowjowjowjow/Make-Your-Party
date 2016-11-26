package app.unirio.makeyourparty.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import app.unirio.makeyourparty.Fragments.FeedFragment;
import app.unirio.makeyourparty.R;

public class MainActivity extends AppCompatActivity {
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        setSupportActionBar(mToolbar);

        FeedFragment feedFragment = (FeedFragment) getSupportFragmentManager().findFragmentByTag("feedFragment");

        if(feedFragment == null) {
            feedFragment = new FeedFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.rl_fragment_container, feedFragment, "feedFragment");
            fragmentTransaction.commit();
        }
    }
}
