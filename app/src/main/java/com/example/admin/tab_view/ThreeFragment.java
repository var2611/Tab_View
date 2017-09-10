package com.example.admin.tab_view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Admin on 9/3/2017.
 */

@SuppressLint("ValidFragment")
class ThreeFragment extends Fragment {

    TextView txt1, txt2, txt3;
    String text, text1, text2;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View font_view = inflater.inflate(R.layout.fragment3, container, false);

        txt1 = (TextView) font_view.findViewById(R.id.tv1);
        txt2 = (TextView) font_view.findViewById(R.id.tv2);
        txt3 = (TextView) font_view.findViewById(R.id.tv3);

        String fontPath = "font/bromello.ttf";
        Typeface tf1 = Typeface.createFromAsset(getActivity().getAssets(),fontPath);
        txt1.setTypeface(tf1);

        String fontPath1 = "font/Cartoon Marker.ttf";
        Typeface tf2 = Typeface.createFromAsset(getActivity().getAssets(), fontPath1);
        txt2.setTypeface(tf2);

        String fontPath2 = "font/Hollow Cartoonlings.ttf";
        Typeface tf3 = Typeface.createFromAsset(getActivity().getAssets(), fontPath2);
        txt3.setTypeface(tf3);

        text=txt1.getText().toString();
        text1=txt2.getText().toString();
        text2=txt3.getText().toString();

        Button btn_share=(Button)font_view.findViewById(R.id.button_share_it);
        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareIt();
            }
        });

        return font_view;
    }

    private void shareIt() {

        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "AndroidSolved");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT,text);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }


}

