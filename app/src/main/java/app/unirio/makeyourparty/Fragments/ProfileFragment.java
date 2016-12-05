package app.unirio.makeyourparty.Fragments;

/**
 * Created by Gabriel on 02/12/2016.
 */

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import app.unirio.makeyourparty.Domain.User;
import app.unirio.makeyourparty.R;

public class ProfileFragment extends Fragment implements AppBarLayout.OnOffsetChangedListener {

    private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR  = 0.9f;
    private static final float PERCENTAGE_TO_HIDE_TITLE_DETAILS     = 0.3f;
    private static final int ALPHA_ANIMATIONS_DURATION              = 200;
    final Uri imageUri = Uri.parse("http://i.imgur.com/VIlcLfg.jpg");

    private boolean mIsTheTitleVisible          = false;
    private boolean mIsTheTitleContainerVisible = true;

    private AppBarLayout appbar;
    private CollapsingToolbarLayout collapsing;
    private ImageView coverImage;
    private FrameLayout framelayoutTitle;
    private LinearLayout linearlayoutTitle;
    private Toolbar toolbar;
    private TextView textviewTitle;
    private SimpleDraweeView avatar;
    private FloatingActionButton fab;

    private void findViews(View view) {
        appbar = (AppBarLayout)view.findViewById( R.id.appbar );
        collapsing = (CollapsingToolbarLayout)view.findViewById( R.id.collapsing );
        coverImage = (ImageView)view.findViewById( R.id.imageview_placeholder );
        framelayoutTitle = (FrameLayout)view.findViewById( R.id.framelayout_title );
        linearlayoutTitle = (LinearLayout)view.findViewById( R.id.linearlayout_title );
        toolbar = (Toolbar)view.findViewById( R.id.toolbar );
        textviewTitle = (TextView)view.findViewById( R.id.textview_title );
        avatar = (SimpleDraweeView)view.findViewById(R.id.avatar);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Fresco.initialize(getActivity().getApplicationContext());
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        findViews(view);

        toolbar.setTitle("");
        appbar.addOnOffsetChangedListener(this);

        startAlphaAnimation(textviewTitle, 0, View.INVISIBLE);

        //set avatar and cover
        avatar.setImageURI(imageUri);
        coverImage.setImageResource(R.drawable.cover);

        setInformation(view, getUserInformation());

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putSerializable("USER_KEY", getUserInformation());

                EditProfileFragment editProfileFragment = new EditProfileFragment();
                editProfileFragment.setArguments(args);

                /*FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.rl_fragment_container, editProfileFragment );
                fragmentTransaction.commit();*/
            }
        });

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    private User getUserInformation() {
        User user = new User("Gabriel Nogueira",
                "(21)98791-2241",
                "Rua Pascal, 624",
                "Rio de Janeiro",
                "Rio de Janeiro",
                "nogueiragabriel.github.io",
                "GabrielNogueira");

        return user;
    }

    private void setInformation(View view, User user){
        TextView tv_username = (TextView) view.findViewById(R.id.tv_username);
        TextView tv_jobRole = (TextView) view.findViewById(R.id.tv_jobRole);
        TextView tv_number = (TextView) view.findViewById(R.id.tv_number);
        TextView tv_adress = (TextView) view.findViewById(R.id.tv_adress);
        TextView tv_email = (TextView) view.findViewById(R.id.tv_email);
        TextView tv_state = (TextView) view.findViewById(R.id.tv_city);

        tv_username.setText(user.getName());
        tv_jobRole.setText(user.getState());
        tv_number.setText(user.getPhone());
        tv_adress.setText(user.getAdress());
        tv_email.setText(user.getSite());
        tv_state.setText(user.getState());

        final String TOOLBAR_TITLE = user.getName() + " " + "information";

        textviewTitle.setText(TOOLBAR_TITLE);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);  // Use filter.xml from step 1
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int offset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(offset) / (float) maxScroll;

        handleAlphaOnTitle(percentage);
        handleToolbarTitleVisibility(percentage);
    }

    private void handleToolbarTitleVisibility(float percentage) {
        if (percentage >= PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR) {

            if(!mIsTheTitleVisible) {
                startAlphaAnimation(textviewTitle, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleVisible = true;
            }

        } else {

            if (mIsTheTitleVisible) {
                startAlphaAnimation(textviewTitle, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleVisible = false;
            }
        }
    }

    private void handleAlphaOnTitle(float percentage) {
        if (percentage >= PERCENTAGE_TO_HIDE_TITLE_DETAILS) {
            if(mIsTheTitleContainerVisible) {
                startAlphaAnimation(linearlayoutTitle, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleContainerVisible = false;
            }

        } else {

            if (!mIsTheTitleContainerVisible) {
                startAlphaAnimation(linearlayoutTitle, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleContainerVisible = true;
            }
        }
    }

    public static void startAlphaAnimation (View v, long duration, int visibility) {
        AlphaAnimation alphaAnimation = (visibility == View.VISIBLE)
                ? new AlphaAnimation(0f, 1f)
                : new AlphaAnimation(1f, 0f);

        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        v.startAnimation(alphaAnimation);
    }
}
