package app.unirio.makeyourparty.Fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import app.unirio.makeyourparty.Domain.User;
import app.unirio.makeyourparty.R;

/**
 * Created by Gabriel on 27/11/2016.
 */
public class ProfileFragment extends android.support.v4.app.Fragment {

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        getUserInformation();

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setTitle(getUserInformation().getLogin());

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Vai para activity de editar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        setProfile(getUserInformation(), view);

        return view;
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

    private void setProfile(User u, View v){
        TextView userNumber = (TextView) v.findViewById(R.id.tvNumber1);
        TextView userCity = (TextView) v.findViewById(R.id.city);

        userNumber.setText(u.getPhone());
        userCity.setText(u.getCity());
    }
}
