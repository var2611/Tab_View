package com.example.admin.tab_view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Admin on 8/28/2017.
 */

@SuppressLint("ValidFragment")
class TwoFragment extends Fragment {

    EditText msg, cl;
    Button dc;
    String msgshare;

    public TwoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View call_view = inflater.inflate(R.layout.fragment2, container, false);

        msg = (EditText) call_view.findViewById(R.id.et_line);
        cl = (EditText) call_view.findViewById(R.id.et_phone);

        Button btn_share = (Button) call_view.findViewById(R.id.button_share);

        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                msgshare = msg.getText().toString();
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "AndroidSolved");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT,msgshare);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });

        dc= (Button) call_view.findViewById(R.id.button_call);
        dc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String uri = "tel:"+cl.getText().toString();
                    Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse(uri));
                    startActivity(dialIntent);
                }catch(Exception e) {
                    Toast.makeText(getContext(), "Your call has failed...",
                            Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }

        });



        return call_view;
    }

}
