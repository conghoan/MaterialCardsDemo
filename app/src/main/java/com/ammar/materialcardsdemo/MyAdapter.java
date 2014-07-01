package com.ammar.materialcardsdemo;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private static final float ELEVATION_LOWER = (float) 5;
    private static final float ELEVATION_HIGHER = (float) 50;
    private String[] mDataset;
    private Map<Integer, View> mViewMap;
    private View selected;

    // Provide a reference to the type of views that you are using
    // (custom viewholder)
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(String[] myDataset) {

        mDataset = myDataset;
        mViewMap = new HashMap<Integer, View>();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        CardView v = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_listitem, parent, false);
        // set the view's size, margins, paddings and layout parameters
        v.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (view == selected)
                {
                    view.setElevation(ELEVATION_LOWER);
                    selected = null;
                }
                else
                {
                    selected = view;

                    view.setElevation(ELEVATION_HIGHER);

                    int position = (Integer) view.getTag();

                    Set<Integer> viewPoisitonSet = mViewMap.keySet();

                    for (int viewPosition : viewPoisitonSet) {
                        if (viewPosition != position) {
                            mViewMap.get(viewPosition).setElevation((float) 5);
                        }
                    }
                }
            }
        });

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.cardView.setTag(position);
        mViewMap.put(position, holder.cardView);
        TextView textView = (TextView) holder.cardView.findViewById(R.id.info_text);
        textView.setText(mDataset[position]);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}
