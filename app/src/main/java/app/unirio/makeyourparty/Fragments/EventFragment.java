package app.unirio.makeyourparty.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import app.unirio.makeyourparty.Domain.Event;
import app.unirio.makeyourparty.R;

/**
 * Created by Gabriel on 04/12/2016.
 */
public class EventFragment extends Fragment {

    private Toolbar mToolbar;
    private ImageView eventImage;
    private TextView eventDescription, eventAdress;

    private void findViews(View view){
        eventImage = (ImageView) view.findViewById(R.id.iv_eventImage);
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar_event);
        eventDescription = (TextView) view.findViewById(R.id.tv_eventDescription);
        eventAdress = (TextView) view.findViewById(R.id.tv_eventAdress);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event, container, false);
        findViews(view);

        Bundle b = this.getArguments();
        Event event = (Event) b.getSerializable("EVENT_KEY");

        mToolbar.setTitle(event.getName());


        eventImage.setImageResource(R.drawable.ln_logo);
        eventDescription.setText(event.getDescription());
        eventAdress.setText(event.getAdress());

        return view;
    }

}
