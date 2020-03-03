package com.chatapp.abobakrdev.egychat2.ActiveUser.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chatapp.abobakrdev.egychat2.ActiveUser.ui.dashboard.model.Post;
import com.chatapp.abobakrdev.egychat2.AddNewUser.AddNewUser;
import com.chatapp.abobakrdev.egychat2.CompleteInfo.Completeinfo;
import com.chatapp.abobakrdev.egychat2.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


public class DashboardFragment extends Fragment implements View.OnClickListener {

    private DashboardViewModel dashboardViewModel;

    private FloatingActionButton floatingActionButton;
    private AdView mAdView;
    AdRequest adRequest;
    private ImageView _POST;
    private EditText txt;
    AddNewUser addNewUser;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);

        addNewUser=new AddNewUser(getActivity());
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        _POST =root.findViewById(R.id._POST);
        txt=root.findViewById(R.id.textView4);

        _POST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Month = Calendar.getInstance().get(Calendar.MONTH);
                int Day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
                Log.e("rw", String.valueOf(Month));
                Log.e("rw", String.valueOf(Day));

                addNewUser.add_post("name","mail","d","sf","d",String.valueOf(Day) + ":" + String.valueOf(Month));

            }
        });

        mAdView = root.findViewById(R.id.adView);
        adRequest = new AdRequest.Builder().build();
        final BottomSheetBehavior mBottomSheetBehavior = BottomSheetBehavior.from(root.findViewById(R.id.bottom_sheet));
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        floatingActionButton = root.findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdView.loadAd(adRequest);

                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });


        dashboardViewModel.getlist().observe(this, new Observer<ArrayList<Post>>() {
            @Override
            public void onChanged(ArrayList<Post> posts) {

            }
        });
        return root;
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.floatingActionButton) {


        }

    }
}