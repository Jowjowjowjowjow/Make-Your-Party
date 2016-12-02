package app.unirio.makeyourparty.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import app.unirio.makeyourparty.Domain.Event;
import app.unirio.makeyourparty.Interfaces.RecyclerViewOnClickListener;
import app.unirio.makeyourparty.R;

/**
 * Created by Gabriel on 18/11/2016.
 */
public class FeedFragmentAdapter extends RecyclerView.Adapter<FeedFragmentAdapter.MyViewHolder> {

    private List<Event> mList;
    private LayoutInflater mLayoutInflater;
    private RecyclerViewOnClickListener mRecyclerViewOnClickListener;


    public FeedFragmentAdapter(Context c, List<Event> l){
        mList = l;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Log.i("LOG", "onCreateViewHolder()");
        View v = mLayoutInflater.inflate(R.layout.item_fragment_feed_card, viewGroup, false);
        MyViewHolder mvh = new MyViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {
        Log.i("LOG", "onBindViewHolder()");
        myViewHolder.imageViewEvent.setImageResource(mList.get(position).getPhoto());
        myViewHolder.textViewName.setText(mList.get(position).getName());
        myViewHolder.textViewDescription.setText( mList.get(position).getDescription());
        myViewHolder.textViewAdress.setText(mList.get(position).getAdress());

        /*try
        {
            YoYo.with(Techniques.FlipInX)
                    .duration(700)
                    .playOn(myViewHolder.itemView);
        }
        catch(Exception e){}*/
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public void setRecyclerViewOnClickListener(RecyclerViewOnClickListener r) {
        mRecyclerViewOnClickListener = r;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imageViewEvent;
        public TextView  textViewName;
        public TextView  textViewDescription;
        public TextView  textViewAdress;

        public MyViewHolder(View itemView) {
            super(itemView);

            imageViewEvent = (ImageView) itemView.findViewById(R.id.imageView_event);
            textViewName = (TextView) itemView.findViewById(R.id.textView_name);
            textViewDescription = (TextView) itemView.findViewById(R.id.textView_description);
            textViewAdress = (TextView) itemView.findViewById(R.id.textView_adress);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mRecyclerViewOnClickListener != null) {
                mRecyclerViewOnClickListener.onClickListener(v, getPosition());
            }
        }
    }
}
