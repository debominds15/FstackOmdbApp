package com.demo.fstack.fstackapp.views;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.demo.fstack.fstackapp.IAlienContract;
import com.demo.fstack.fstackapp.R;
import com.demo.fstack.fstackapp.util.AppUtil;

public class SearchAlienFragment extends Fragment implements View.OnClickListener {
    private Button mSearchBtn;
    private Button mSCustomSearchBtn;
    private EditText mSearchEdt;
    private ConstraintLayout mLayoutMainSearch;
    private static SearchAlienFragment mInstance;
    private IAlienContract mHomePageListener;

    public static SearchAlienFragment getInstance() {
        mInstance = new SearchAlienFragment();
        return mInstance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_alien, container, false);
        mHomePageListener = (MainActivity) getActivity();
        mSearchBtn = view.findViewById(R.id.btn_search);
        mSearchEdt = view.findViewById(R.id.edt_search);
        mLayoutMainSearch = view.findViewById(R.id.layout_main_search);
        mSCustomSearchBtn = view.findViewById(R.id.btn_custom_search);

        mSearchBtn.setOnClickListener(this);
        mLayoutMainSearch.setOnClickListener(this);
        mSCustomSearchBtn.setOnClickListener(this);

        AppUtil.setButtonTypeFace(getActivity(), mSearchBtn);
        AppUtil.setButtonTypeFace(getActivity(), mSCustomSearchBtn);
        AppUtil.setEditTextTypeFace(getActivity(), mSearchEdt);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_search:
                if (mSearchEdt.getText().toString().length() > 0) {
                    if (AppUtil.isConnectedToNetwork(getActivity()))
                        mHomePageListener.switchToAllAliensFragment(mSearchEdt.getText().toString(), "", "");
                    else
                        AppUtil.showDialog(getActivity(), getString(R.string.str_net_conn));
                }
                break;
            case R.id.layout_main_search:
                AppUtil.hideKeyboard(mLayoutMainSearch, getActivity());
                break;
            case R.id.btn_custom_search:
                mHomePageListener.switchToAllCustomAliensFragment();
                break;
        }
    }
}
