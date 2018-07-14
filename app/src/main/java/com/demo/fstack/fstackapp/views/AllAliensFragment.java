package com.demo.fstack.fstackapp.views;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.fstack.fstackapp.IAlienContract;
import com.demo.fstack.fstackapp.R;
import com.demo.fstack.fstackapp.adapter.AlienRecyclerAdapter;
import com.demo.fstack.fstackapp.model.AlienDetailModel;
import com.demo.fstack.fstackapp.model.AlienResponseModel;
import com.demo.fstack.fstackapp.model.Search;
import com.demo.fstack.fstackapp.presenter.IAlienPresenter;
import com.demo.fstack.fstackapp.presenter.IAlienPresenterImpl;
import com.demo.fstack.fstackapp.util.AppConstants;
import com.demo.fstack.fstackapp.util.CustomProgressDialogUtil;

import java.util.ArrayList;
import java.util.List;

public class AllAliensFragment extends Fragment implements IAlienContract.IAlienView, View.OnClickListener {

    private static AllAliensFragment mInstance;
    private TextView mTvTitle;
    private ImageView mIvBack;
    private String query;
    private String year;
    private String type;
    private IAlienPresenter mPresenter;
    private RecyclerView mRecyclerView;
    private static int PAGE_NO = 1;
    private List<Search> mSearches;
    private AlienRecyclerAdapter mAdapter;
    private boolean isLoadBtnClicked = false;
    private TextView mTvNoResults;

    public static AllAliensFragment getInstance() {
        mInstance = new AllAliensFragment();

        return mInstance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_alien, container, false);
        mTvTitle = view.findViewById(R.id.tv_title);
        mTvNoResults = view.findViewById(R.id.tv_no_results);
        mIvBack = view.findViewById(R.id.btn_back);
        mRecyclerView = view.findViewById(R.id.rv_alien);
        mSearches = new ArrayList<Search>();

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        llm.isSmoothScrollbarEnabled();
        mRecyclerView.setLayoutManager(llm);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            query = bundle.getString(AppConstants.SEARCH_QUERY);
            year = bundle.getString(AppConstants.SEARCH_YEAR);
            type = bundle.getString(AppConstants.SEARCH_TYPE);
        }
        mPresenter = new IAlienPresenterImpl(this);
        mPresenter.searchAliens(AppConstants.API_KEY, query, year, type, PAGE_NO);

        mIvBack.setOnClickListener(this);
        mTvTitle.setText(getString(R.string.str_alien));
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                getFragmentManager().popBackStack();
                break;
        }
    }

    @Override
    public void searchAliensResponse(AlienResponseModel model) {
        if (model != null && model.getSearch() != null && model.getSearch().size() > 0) {
            mTvNoResults.setVisibility(View.GONE);
            mSearches.addAll(model.getSearch());

            if (isLoadBtnClicked) {
                if (PAGE_NO > 1 && mAdapter != null) {
                    mAdapter.notifyDataSetChanged();
                    isLoadBtnClicked = false;
                }
            } else {
                mAdapter = new AlienRecyclerAdapter(getActivity(), mSearches, this);
                mRecyclerView.setAdapter(mAdapter);
            }
        }
        else {
            mTvNoResults.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void getAlienDetailsResponse(AlienDetailModel model) {
        //Not required
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

    public void performLoadMoreSearchOperation() {
        isLoadBtnClicked = true;
        PAGE_NO++;
        mPresenter.searchAliens(AppConstants.API_KEY, query, year, type, PAGE_NO);
    }
}
