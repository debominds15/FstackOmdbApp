package com.demo.fstack.fstackapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.fstack.fstackapp.R;

import java.util.ArrayList;
import java.util.Map;

public class AlienDetailsRecyclerAdapter extends RecyclerView.Adapter<AlienDetailsRecyclerAdapter.AlienDetailsHolder> {
    private final ArrayList mData;
    private Context mContext;

    public AlienDetailsRecyclerAdapter(Context context, Map<String, Object> alienDetails) {
        mContext = context;
        mData = new ArrayList();
        mData.addAll(alienDetails.entrySet());
    }

    @Override
    public AlienDetailsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AlienDetailsRecyclerAdapter.AlienDetailsHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v1 = inflater.inflate(R.layout.alien_detail_row, parent, false);
        viewHolder = new AlienDetailsRecyclerAdapter.AlienDetailsHolder(v1);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AlienDetailsHolder holder, int position) {
        Map.Entry<String, Object> entry = (Map.Entry) mData.get(position);
        String key = entry.getKey();
        Object value = entry.getValue();

        if (!TextUtils.isEmpty(key)) {
            holder.alienDetailTitleTxt.setText(key);
        }

        if (value instanceof String) {
            String alienDesc = (String) value;
            if (!TextUtils.isEmpty(alienDesc)) {
                holder.alienDetailDescTxt.setText(alienDesc);
            }
        }

        if (position == (mData.size() - 1)) {
            holder.separatorView.setVisibility(View.GONE);
        } else {
            holder.separatorView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class AlienDetailsHolder extends RecyclerView.ViewHolder {

        private TextView alienDetailTitleTxt;
        private TextView alienDetailDescTxt;
        private View separatorView;

        public AlienDetailsHolder(View itemView) {
            super(itemView);
            alienDetailTitleTxt = itemView.findViewById(R.id.tv_alien_title);
            alienDetailDescTxt = itemView.findViewById(R.id.tv_alien_desc);
            separatorView = itemView.findViewById(R.id.view_separator);
        }
    }
}
