package com.pzj.navigatetabbar.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.pzj.navigatetabbar.R;

/**
 * PersonFragment
 *
 * @author PengZhenjin
 * @date 2017-9-11
 */
public class PersonFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_person, container, false);
    }
}
