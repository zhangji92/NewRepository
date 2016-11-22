package com.allactivity.speech;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.allactivity.R;

/**
 * Created by 张继 on 2016/11/21.
 * 演示
 */

public class SpeechFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.speech_activity, container, false);

        return view;
    }
}
