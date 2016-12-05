package app.unirio.makeyourparty.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import app.unirio.makeyourparty.Adapters.FeedFragmentAdapter;
import app.unirio.makeyourparty.Domain.Event;
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
            }
        });

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);

        mList = getEventList();
        FeedFragmentAdapter adapter = new FeedFragmentAdapter(getActivity(), mList);
        adapter.setRecyclerViewOnClickListener(this);
        mRecyclerView.setAdapter( adapter );

        return view;
    }

    public List<Event> getEventList() {
        String[] name = new String[]{"FESTA 1", "FESTA 2", "FESTA 3", "FESTA 4"};
        String[] date = new String[]{"19-NOV", "20-NOV", "3-DEZ", "20-DEZ"};
        String[] description = new String[]{"DESCRIÇÃO 1", "DESCRIÇÃO 2", "DESCRIÇÃO 3", "DESCRIÇÃO 4"};
        String[] city = new String[]{"CIDADE 1", "CIDADE 2", "CIDADE 3", "CIDADE 4"};
        String[] adress = new String []{"ENDEREÇO 1", "ENDEREÇO 2", "ENDEREÇO 3", "ENDEREÇO 4"};
        int[] photo = new int[]{R.drawable.login_bg, R.drawable.login_bg, R.drawable.login_bg, R.drawable.login_bg};

        List<Event> listAux = new ArrayList<>();

        for(int i = 0; i < name.length; i++) {
            Event event = new Event(photo[i], name[i], date[i], description[i], city[i], adress[i]);
            listAux.add(event);
        }
        return(listAux);
    }

    @Override
    public void onClickListener(View view, int position) {
        Event event = mList.get(position);

        Bundle bundle = new Bundle();
        bundle.putSerializable("EVENT_KEY", event);
        EventFragment eventFragment = new EventFragment();
        eventFragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.rl_fragment_container, eventFragment);
        fragmentTransaction.commit();

    }
}
