package com.digitalar;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.view.MotionEventCompat;
import android.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.digitalar.ItemFragment.OnListFragmentInteractionListener;
import com.digitalar.dummy.DummyContent.DummyItem;

import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {
    int state;
    private final List<String> mValues;
    private final OnListFragmentInteractionListener mListener;
    Context ct;
    public MyItemRecyclerViewAdapter(List<String> items, OnListFragmentInteractionListener listener, Context c,int st) {
        mValues = items;
        mListener = listener;
        ct=c;
        state=st;
    }
    LayoutInflater lx;
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        lx=LayoutInflater.from(parent.getContext());
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mContentView.setText(mValues.get(position));
        holder.mView.setFocusable(true);
       final Intent in= new Intent(ct,Choosecourse.class);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Integer e1= holder.getAdapterPosition()+1;
                Log.d("recycler",e1.toString());
                Toast.makeText(ct, e1.toString(), Toast.LENGTH_SHORT).show();
                if(state==0)
                {  in.setFlags(FLAG_ACTIVITY_NEW_TASK);
                    ct.startActivity(in);}
                else if(state==1)
                    di();


                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }
    Dialog d1;
    public void di()
    {
        d1 = new Dialog(ct);
        d1.setContentView(R.layout.deldia);
        d1.show();

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        public final TextView mContentView;
        public String mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;

            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
