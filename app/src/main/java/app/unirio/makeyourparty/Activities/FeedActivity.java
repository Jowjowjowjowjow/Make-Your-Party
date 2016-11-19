package app.unirio.makeyourparty.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import app.unirio.makeyourparty.Domain.Event;
import app.unirio.makeyourparty.Fragments.FeedFragment;
import app.unirio.makeyourparty.R;

/**
 * Created by Gabriel on 18/11/2016.
 */
public class FeedActivity extends AppCompatActivity{

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.settings){
            Intent intent = new Intent();
        }

        return super.onOptionsItemSelected(item);
    }

    public List<Event> getEventList() {
        String[] name = new String[]{"FESTA 1", "FESTA 2", "FESTA 3"};
        String[] date = new String[]{"19-NOV", "20-NOV", "3-DEZ"};
        String[] description = new String[]{"DESCRIÇÃO 1", "DESCRIÇÃO 2", "DESCRIÇÃO 3"};
        String[] city = new String[]{"CIDADE 1", "CIDADE 2", "CIDADE 3"};
        String[] adress = new String []{"ENDEREÇO 1", "ENDEREÇO 2", "ENDEREÇO 3"};
        int[] photo = new int[]{R.drawable.login_bg, R.drawable.login_bg, R.drawable.login_bg};

        List<Event> listAux = new ArrayList<>();

        for(int i = 0; i < name.length; i++) {
            Event event = new Event(photo[i], name[i], date[i], description[i], city[i], adress[i]);
            listAux.add(event);
        }
        return(listAux);
    }

}
