package com.demo.fstack.fstackapp.views;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.request.RequestOptions;
import com.demo.fstack.fstackapp.IAlienContract;
import com.demo.fstack.fstackapp.R;
import com.demo.fstack.fstackapp.adapter.AlienDetailsRecyclerAdapter;
import com.demo.fstack.fstackapp.model.AlienDetailModel;
import com.demo.fstack.fstackapp.model.AlienResponseModel;
import com.demo.fstack.fstackapp.presenter.IAlienPresenter;
import com.demo.fstack.fstackapp.presenter.IAlienPresenterImpl;
import com.demo.fstack.fstackapp.util.AppConstants;
import com.demo.fstack.fstackapp.util.AppUtil;
import com.demo.fstack.fstackapp.util.CustomProgressDialogUtil;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AlienDetailFragment extends Fragment implements IAlienContract.IAlienView, View.OnClickListener {
    private static AlienDetailFragment mInstance;
    private String mImdbId;
    private RecyclerView mRecyclerView;
    private IAlienPresenter mPresenter;
    private ImageView mExpandedImage;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private AppBarLayout mAppBarLayout;
    private ImageView mBackBtn;
    private TextView mTitleTxt;
    private TextView mRatingTxt;
    private TextView mAlienDescTxt;

    public static AlienDetailFragment getInstance() {
        mInstance = new AlienDetailFragment();

        return mInstance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alien_detail, container, false);
        mRecyclerView = view.findViewById(R.id.rv_alien_details);
        mExpandedImage = view.findViewById(R.id.expandedImage);
        mBackBtn = view.findViewById(R.id.iv_back);
        mTitleTxt = view.findViewById(R.id.alien_title);
        mRatingTxt = view.findViewById(R.id.tv_rating);
        mAlienDescTxt = view.findViewById(R.id.tv_header_alien_detail);
        mCollapsingToolbarLayout = view.findViewById(R.id.toolbar_layout);
        mAppBarLayout = view.findViewById(R.id.app_bar);

        mPresenter = new IAlienPresenterImpl(this);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        llm.isSmoothScrollbarEnabled();
        mRecyclerView.setLayoutManager(llm);
        mBackBtn.setOnClickListener(this);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            mImdbId = bundle.getString(AppConstants.IMDB_ID);
        }

        if (!TextUtils.isEmpty(mImdbId)) {
                mPresenter.getAlienDetails(AppConstants.API_KEY, mImdbId);
        }

        return view;
    }

    public static Map<String, Object> jsonToMap(JSONObject json) throws JSONException {
        Map<String, Object> retMap = new HashMap<String, Object>();

        if (json != JSONObject.NULL) {
            retMap = toMap(json);
        }
        return retMap;
    }

    public static Map<String, Object> toMap(JSONObject object) throws JSONException {
        Map<String, Object> map = new HashMap<String, Object>();

        Iterator<String> keysItr = object.keys();
        while (keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = object.get(key);

            if (value instanceof JSONArray) {
                value = toList((JSONArray) value);
            } else if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            map.put(key, value);
        }
        return map;
    }

    public static List<Object> toList(JSONArray array) throws JSONException {
        List<Object> list = new ArrayList<Object>();
        for (int i = 0; i < array.length(); i++) {
            Object value = array.get(i);
            if (value instanceof JSONArray) {
                value = toList((JSONArray) value);
            } else if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            list.add(value);
        }
        return list;
    }

    @Override
    public void searchAliensResponse(AlienResponseModel model) {
        //Not required
    }

    @Override
    public void getAlienDetailsResponse(AlienDetailModel model) {
        if (model != null) {
            setExpandedImage(model);
            Gson gson = new Gson();
            String json = gson.toJson(model);
            try {
                JSONObject obj = new JSONObject(json);
                Map<String, Object> alienMap = jsonToMap(obj);
                AlienDetailsRecyclerAdapter adapter = new AlienDetailsRecyclerAdapter(getActivity(), alienMap);
                mRecyclerView.setAdapter(adapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    private void setExpandedImage(final AlienDetailModel model) {
        GlideUrl glideUrl = new GlideUrl(model.getPoster(), new LazyHeaders.Builder()
                .addHeader("Authorization", "Basic")
                .build());


        Glide.with(this).load(glideUrl)
                .apply(new RequestOptions().fitCenter().placeholder(R.drawable.placeholder_image)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .error(R.drawable.placeholder_image))
                .into(mExpandedImage);

        mTitleTxt.setText(model.getTitle());
        mRatingTxt.setText(String.format(getString(R.string.str_rating), model.getImdbRating()));
        mAlienDescTxt.setText(model.getRuntime() + "|" + model.getGenre());
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    mCollapsingToolbarLayout.setTitle(model.getTitle());
                    isShow = true;
                } else if (isShow) {
                    mCollapsingToolbarLayout.setTitle(" ");//carefull there should a space between double quote otherwise it wont work
                    isShow = false;
                }
            }
        });
    }

    @Override
    public void showProgress() {
        if (isAdded()) {
            CustomProgressDialogUtil.show(getActivity());
        }

    }

    @Override
    public void hideProgress() {
        if (isAdded())
            CustomProgressDialogUtil.dismiss();
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                getFragmentManager().popBackStack();
                break;
        }
    }
}
