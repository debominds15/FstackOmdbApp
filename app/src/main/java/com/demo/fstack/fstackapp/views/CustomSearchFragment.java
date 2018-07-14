package com.demo.fstack.fstackapp.views;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.demo.fstack.fstackapp.IAlienContract;
import com.demo.fstack.fstackapp.R;
import com.demo.fstack.fstackapp.util.AppUtil;

import java.util.ArrayList;
import java.util.List;

public class CustomSearchFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private EditText mEdtYear;
    private EditText mEdtSearchQuery;
    private Button mBtnSearch;
    private Spinner mSpinner;
    private ConstraintLayout mLayoutMainSearch;
    private static CustomSearchFragment mInstance;
    private IAlienContract mHomePageListener;
    private String mType;
    private List<String> categories;
    private String TYPE_MOVIE = "Movie";
    private String TYPE_SERIES = "Series";
    private String TYPE_EPISODE = "Episode";

    public static CustomSearchFragment getInstance() {
        mInstance = new CustomSearchFragment();
        return mInstance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_custom_alien_search, container, false);
        mEdtYear = view.findViewById(R.id.edt_year);
        mEdtSearchQuery = view.findViewById(R.id.edt_search);
        mBtnSearch = view.findViewById(R.id.btn_search);
        mLayoutMainSearch = view.findViewById(R.id.layout_main_search);
        mHomePageListener = (MainActivity) getActivity();

        mBtnSearch.setOnClickListener(this);

        AppUtil.setButtonTypeFace(getActivity(), mBtnSearch);
        AppUtil.setEditTextTypeFace(getActivity(), mEdtSearchQuery);
        AppUtil.setEditTextTypeFace(getActivity(), mEdtYear);

        mLayoutMainSearch.setOnClickListener(this);

        mSpinner = view.findViewById(R.id.spinner);
        mSpinner.setOnItemSelectedListener(this);

        categories = new ArrayList<String>();
        categories.add(TYPE_MOVIE);
        categories.add(TYPE_SERIES);
        categories.add(TYPE_EPISODE);

        mType = categories.get(0);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item_layout, categories);

        dataAdapter.setDropDownViewResource(R.layout.spinner_item_layout);

        mSpinner.setAdapter(dataAdapter);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mType = parent.getItemAtPosition(position).toString();

            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_search:
                if (mEdtSearchQuery.getText().toString().length() > 0) {
                    if (AppUtil.isConnectedToNetwork(getActivity()))
                mHomePageListener.switchToAllAliensFragment(mEdtSearchQuery.getText().toString(), mEdtYear.getText().toString(), mType);
                    else
                        AppUtil.showDialog(getActivity(), getString(R.string.str_net_conn));
                }
                break;
            case R.id.layout_main_search:
                AppUtil.hideKeyboard(mLayoutMainSearch, getActivity());
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
