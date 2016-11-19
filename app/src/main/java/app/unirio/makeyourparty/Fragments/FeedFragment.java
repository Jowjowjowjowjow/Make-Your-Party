package app.unirio.makeyourparty.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import app.unirio.makeyourparty.Adapters.FeedActivityAdapter;
import app.unirio.makeyourparty.Domain.Event;
import app.unirio.makeyourparty.Activities.FeedActivity;
import app.unirio.makeyourparty.Interfaces.RecyclerViewOnClickListener;
import app.unirio.makeyourparty.R;


/**
 * Created by Gabriel on 19/11/2016.
 */
public class FeedFragment extends Fragment implements RecyclerViewOnClickListener
{
    private RecyclerView mRecyclerView;
    private List<Event> mList;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_feed);
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                FeedActivityAdapter adapter = (FeedActivityAdapter) mRecyclerView.getAdapter();
            }
        });

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);

        mList = ((FeedActivity) getActivity()).getEventList();
        FeedActivityAdapter adapter = new FeedActivityAdapter(getActivity(), mList);
        adapter.setRecyclerViewOnClickListener(this);
        mRecyclerView.setAdapter( adapter );

        return view;
    }

    @Override
    public void onClickListener(View view, int position) {
        Event event = mList.get(position);
    }
}
