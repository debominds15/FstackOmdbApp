package com.demo.fstack.fstackapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.request.RequestOptions;
import com.demo.fstack.fstackapp.IAlienContract;
import com.demo.fstack.fstackapp.R;
import com.demo.fstack.fstackapp.model.Search;
import com.demo.fstack.fstackapp.util.AppConstants;
import com.demo.fstack.fstackapp.util.AppUtil;
import com.demo.fstack.fstackapp.views.AllAliensFragment;
import com.demo.fstack.fstackapp.views.MainActivity;

import java.util.List;

public class AlienRecyclerAdapter extends RecyclerView.Adapter<AlienRecyclerAdapter.WikiHolder> {

    private List<Search> mSearches;
    private Context mContext;
    private IAlienContract mHomePageListener;
    private AllAliensFragment mFragment;

    public AlienRecyclerAdapter(Context context, List<Search> searchList, AllAliensFragment fragment) {
        mContext = context;
        mSearches = searchList;
        mHomePageListener = (MainActivity) context;
        mFragment = fragment;
    }

    @NonNull
    @Override
    public WikiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case VIEW_TYPES.Normal:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.alien_row_item, parent, false);
                return new AlienViewHolder(view);
            case VIEW_TYPES.Footer:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.layout_load_more, parent, false);
                return new WikiHolderLoadButton(view);
            default:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.alien_row_item, parent, false);
                return new AlienViewHolder(view);
        }
    }


    @NonNull
    @Override
    public void onBindViewHolder(@NonNull WikiHolder holder, final int position) {
        Search search = mSearches.get(position);

        if (holder instanceof AlienViewHolder) {
            AlienViewHolder normalHolder = (AlienViewHolder) holder;

            if (search != null) {
                if (search.getTitle() != null && !TextUtils.isEmpty(search.getTitle())) {
                    normalHolder.alienNameTxt.setText(search.getTitle());
                }

                if (search.getYear() != null && !TextUtils.isEmpty(search.getYear())) {
                    normalHolder.alienYearTxt.setText(search.getYear());
                }

                if (search.getPoster() != null && !TextUtils.isEmpty(search.getPoster())) {
                    GlideUrl glideUrl = new GlideUrl(search.getPoster(), new LazyHeaders.Builder()
                            .addHeader("Authorization", "Basic")
                            .build());


                    Glide.with(mContext).load(glideUrl)
                            .apply(new RequestOptions().fitCenter().placeholder(R.drawable.placeholder_image)
                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                    .error(R.drawable.placeholder_image))
                            .into(normalHolder.alienImage);
                }

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (AppUtil.isConnectedToNetwork(mContext))
                            mHomePageListener.switchToAlienDetailFragment(mSearches.get(position).getImdbID());
                        else
                            AppUtil.showDialog(mContext, "Not connected to Internet");
                    }
                });
            }
        } else if (holder instanceof WikiHolderLoadButton) {
            WikiHolderLoadButton wikiHolderLoad = (WikiHolderLoadButton) holder;
            wikiHolderLoad.mBtnLoad.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mFragment.performLoadMoreSearchOperation();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mSearches.size();
    }

    public class AlienViewHolder extends WikiHolder {

        private ImageView alienImage;
        private TextView alienNameTxt;
        private TextView alienYearTxt;

        public AlienViewHolder(View itemView) {
            super(itemView);
            alienImage = itemView.findViewById(R.id.iv_alien);
            alienNameTxt = itemView.findViewById(R.id.tv_alien_name);
            alienYearTxt = itemView.findViewById(R.id.tv_alien_year);

        }
    }

    public class WikiHolderLoadButton extends WikiHolder {
        private Button mBtnLoad;

        public WikiHolderLoadButton(View view) {
            super(view);
            mBtnLoad = view.findViewById(R.id.btn_load_more);
        }
    }

    public class WikiHolder extends RecyclerView.ViewHolder {
        public WikiHolder(View itemView) {
            super(itemView);
        }
    }

    private class VIEW_TYPES {
        public static final int Normal = 1;
        public static final int Footer = 2;
    }

    @Override
    public int getItemViewType(int position) {

        if (position == mSearches.size() - 1)
            return VIEW_TYPES.Footer;
        else
            return VIEW_TYPES.Normal;

    }
}
