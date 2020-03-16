package com.chatapp.abobakrdev.egychat2.ActiveUser.ui.dashboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chatapp.abobakrdev.egychat2.ActiveUser.ui.dashboard.model.Post;
import com.chatapp.abobakrdev.egychat2.ActiveUser.ui.dashboard.rec.postAdapter;
import com.chatapp.abobakrdev.egychat2.AddNewUser.AddNewUser;
import com.chatapp.abobakrdev.egychat2.CompleteInfo.Completeinfo;
import com.chatapp.abobakrdev.egychat2.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import static android.view.WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN;


public class DashboardFragment extends Fragment implements View.OnClickListener {

    private DashboardViewModel dashboardViewModel;
    private FloatingActionButton floatingActionButton;
    private AdView mAdView;
    private AdRequest adRequest;
    private ImageView _POST;
    private EditText txt;
    private SharedPreferences sharedPreferences;
    private ImageView imageView6, imageView7, imageView8;
    private String color;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private SwipeRefreshLayout swip;
    private ProgressBar progressBar;
    private MediaPlayer mediaPlayer;
    InterstitialAd mInterstitialAd;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sharedPreferences = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        mediaPlayer = MediaPlayer.create(getContext(), R.raw.postsound);

        final View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        progressBar = root.findViewById(R.id.progressBar);
        swip = root.findViewById(R.id.swap);
        getActivity().getWindow().setSoftInputMode(SOFT_INPUT_ADJUST_PAN);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView = root.findViewById(R.id.recyclerView2);
        _POST = root.findViewById(R.id._POST);
        txt = root.findViewById(R.id.textView4);
        imageView6 = root.findViewById(R.id.imageView6);
        imageView7 = root.findViewById(R.id.imageView7);
        imageView8 = root.findViewById(R.id.imageView8);
        mAdView = root.findViewById(R.id.adView);
        adRequest = new AdRequest.Builder().build();
        final BottomSheetBehavior mBottomSheetBehavior = BottomSheetBehavior.from(root.findViewById(R.id.bottom_sheet));
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        floatingActionButton = root.findViewById(R.id.floatingActionButton);

        color = getActivity().getResources().getString(R.string.item1);
        progressBar.setVisibility(View.VISIBLE);


        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txt.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.iteam1));
                color = getActivity().getResources().getString(R.string.item1);

            }
        });
        imageView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txt.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.iteam2));
                color = getActivity().getResources().getString(R.string.item2);

            }
        });
        imageView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txt.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.iteam3));
                color = getActivity().getResources().getString(R.string.item3);

            }
        });


        swip.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                dashboardViewModel.getlist();
                dashboardViewModel.getlist().observe(getActivity(), new Observer<ArrayList<Post>>() {
                    @Override
                    public void onChanged(ArrayList<Post> posts) {


                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setAdapter(new postAdapter(getContext(), posts));
                        if (posts.size() > 0) {
                            swip.setRefreshing(false);
                        }

                    }
                });

            }
        });

        _POST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!txt.getText().toString().isEmpty()) {
                    int Month = Calendar.getInstance().get(Calendar.MONTH);
                    int Day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
                    Log.e("rw", String.valueOf(Month));
                    Log.e("rw", String.valueOf(Day));
                    String delegate = "hh:mm aaa";
                    AddNewUser.getInstance(getContext()).add_post(
                            sharedPreferences.getString("name", "-1"),
                            sharedPreferences.getString("email", "-1"),
                            txt.getText().toString(),
                            color,
                            String.valueOf(DateFormat.format(delegate, Calendar.getInstance().getTime()))
                            , String.valueOf(Day) + ":" + String.valueOf(Month + 1)
                            , sharedPreferences.getString("img", "-1"));


                    txt.setText("");
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);


                    mediaPlayer.start();
                    Snackbar.make(root, "Post Add ", Snackbar.LENGTH_LONG).show();

                } else
                    txt.setError(getString(R.string.EnterPost));

            }
        });


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdView.loadAd(adRequest);

                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });


        dashboardViewModel.getlist().observe(getActivity(), new Observer<ArrayList<Post>>() {
            @Override
            public void onChanged(ArrayList<Post> posts) {


                    progressBar.setVisibility(View.INVISIBLE);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(new postAdapter(getContext(), posts));


            }
        });

        mInterstitialAd = new InterstitialAd(getContext());

        // set the ad unit ID
        mInterstitialAd.setAdUnitId("ca-app-pub-0664570763252260/1769900428");

        AdRequest adRequest = new AdRequest.Builder().build();

        // Load ads into Interstitial Ads
        mInterstitialAd.loadAd(adRequest);

        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                mInterstitialAd.show();
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