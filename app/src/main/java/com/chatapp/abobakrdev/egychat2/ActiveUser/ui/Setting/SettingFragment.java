package com.chatapp.abobakrdev.egychat2.ActiveUser.ui.Setting;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chatapp.abobakrdev.egychat2.ActiveUser.ui.Setting.photoadapter.PhotoAdapter;
import com.chatapp.abobakrdev.egychat2.AddNewUser.AddNewUser;
import com.chatapp.abobakrdev.egychat2.DarkMode.InitApplication;
import com.chatapp.abobakrdev.egychat2.R;
import com.chatapp.abobakrdev.egychat2.login.MainActivity;
import com.chatapp.abobakrdev.egychat2.login.Splash_Activity;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

public class SettingFragment extends Fragment  implements GoogleApiClient.OnConnectionFailedListener{


    private SharedPreferences sharedPreferences;

    private String name;
    private String mail;
    private String age;
    private String gender;
    private String img;
    private String phone, Gmail;
    private ImageView imageView;
    private TextView gander, date, _phone, _mail, _my_name;
    private ImageView _add_img,
            _profile_recycler_img0, _profile_recycler_img1, edit_statue, close_statue;
    private LinearLayout update_statue;
    final private int GET_GALLERY_CODE = 111;
    final private int GET_GALLERY_CODE0 = 110;

    final private int GET_GALLERY_CODE1 = 1999;

    private ImageView imageView0;
    private StorageReference mStorageRef;
    private SharedPreferences.Editor sharedPreferences1;
    private ProgressDialog progressDialog;
    private EditText about;
    private Button update,logoutBtn;
    private TextView text_about;

    private GoogleApiClient googleApiClient;
    private GoogleSignInOptions gso;
    private RecyclerView recyclerView ;

    private PhotoAdapter photoAdapter ;
    private GridLayoutManager gridLayoutManager ;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.my_profile_layout, container, false);

        recyclerView = root.findViewById(R.id.listimg);
        gridLayoutManager =new GridLayoutManager(getContext(),3);
        photoAdapter =new PhotoAdapter();

        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(photoAdapter);



//        Switch switchCompat =root.findViewById(R.id._switch);
//
//        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
//            switchCompat.setChecked(true);
//
//        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//
//                    InitApplication.getInstance(getContext()).setIsNightModeEnabled(true);
//                    if (InitApplication.getInstance(getContext()).isNightModeEnabled()) {
//                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//
//                    } else {
//                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//
//                    }
//
//
//                } else {
//                    InitApplication.getInstance(getContext()).setIsNightModeEnabled(false);
//
//                    if (InitApplication.getInstance(getContext()).isNightModeEnabled()) {
//                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//
//                    } else {
//                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//
//                    }
//
//                }
//
//
//            }
//        });

        logoutBtn=root.findViewById(R.id.logoutBtn);

        gander = root.findViewById(R.id._gender);
        date = root.findViewById(R.id._date);
        _phone = root.findViewById(R.id._phone);
        _mail = root.findViewById(R.id._mail);
        _my_name = root.findViewById(R.id._my_name);
        _add_img = root.findViewById(R.id._edit_img);
        about = root.findViewById(R.id.about);
        update = root.findViewById(R.id.update);
        text_about = root.findViewById(R.id.text_about);
        _profile_recycler_img0 = root.findViewById(R.id._profile_recycler_img0);
        _profile_recycler_img1 = root.findViewById(R.id._profile_recycler_img1);
//        Add0 = root.findViewById(R.id.Add0);
//        Add1 = root.findViewById(R.id.Add1);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Upload New Image");
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);

        edit_statue = root.findViewById(R.id._edit_statue);
        update_statue = root.findViewById(R.id._update_statue);
        close_statue = root.findViewById(R.id._close_statue);


        sharedPreferences1 = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE).edit();

        imageView = root.findViewById(R.id._profile_img);
        sharedPreferences = getActivity().getSharedPreferences("login", MODE_PRIVATE);

        name = sharedPreferences.getString("name", "-1");
        Gmail = sharedPreferences.getString("Gmail", "-1");
        mail = sharedPreferences.getString("mail", "-1");
        age = sharedPreferences.getString("age", "-1");
        gender = sharedPreferences.getString("gender", "-1");
        img = sharedPreferences.getString("img", "-1");
        phone = sharedPreferences.getString("phone", "-1");
        mStorageRef = FirebaseStorage.getInstance().getReference();
        Log.e("age", age);
        _mail.setText(name);
        date.setText(age);
        gander.setText(gender);
        _phone.setText(phone);
        _my_name.setText(Gmail);
        text_about.setText(sharedPreferences.getString("about", "Enter Data About U "));

        Glide.with(getActivity()).load(img).into(imageView);
//        Glide.with(getActivity()).load(sharedPreferences.getString("img0", "-1")).into(_profile_recycler_img0);
//
//        Glide.with(getActivity()).load(sharedPreferences.getString("img1", "-1")).into(_profile_recycler_img1);
//

        edit_statue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                update_statue.setVisibility(View.VISIBLE);
                edit_statue.setVisibility(View.GONE);
                close_statue.setVisibility(View.VISIBLE);


            }
        });

        close_statue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_statue.setVisibility(View.VISIBLE);
                update_statue.setVisibility(View.GONE);
                close_statue.setVisibility(View.GONE);

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!about.getText().toString().isEmpty()) {
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(root.getWindowToken(), 0);
                    AddNewUser.getInstance(getContext()).update_about_me(mail, about.getText().toString());
                    edit_statue.setVisibility(View.VISIBLE);
                    update_statue.setVisibility(View.GONE);
                    close_statue.setVisibility(View.GONE);
                    text_about.setText(about.getText().toString());
                    Snackbar.make(root, "About is updated", Snackbar.LENGTH_LONG).show();
                    sharedPreferences1.putString("about", about.getText().toString());
                    sharedPreferences1.apply();
                    about.setText("");


                } else {
                    about.setError("Enter new About ");
                }


            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), Viewimage.class);
                intent.putExtra("path", sharedPreferences.getString("img", "-1"));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);


            }
        });

//        _profile_recycler_img0.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(getContext(), Viewimage.class);
//                intent.putExtra("path", sharedPreferences.getString("img0", "-1"));
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                getActivity().startActivity(intent);
//                getActivity().overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
//
//
//            }
//        });
//
//        _profile_recycler_img1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(getContext(), Viewimage.class);
//                intent.putExtra("path", sharedPreferences.getString("img1", "-1"));
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                getActivity().startActivity(intent);
//                getActivity().overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
//
//
//            }
//        });


        _add_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int permissionCheck = ContextCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.READ_EXTERNAL_STORAGE);

                if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");
                    startActivityForResult(intent, GET_GALLERY_CODE);

                } else {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            2000);
                }


            }
        });


//        Add0.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int permissionCheck = ContextCompat.checkSelfPermission(getActivity(),
//                        Manifest.permission.READ_EXTERNAL_STORAGE);
//
//                if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
//                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//                    intent.setType("image/*");
//                    startActivityForResult(intent, GET_GALLERY_CODE0);
//
//                } else {
//                    ActivityCompat.requestPermissions(getActivity(),
//                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
//                            2000);
//                }
//
//            }
//        });
//
//
//        Add1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int permissionCheck = ContextCompat.checkSelfPermission(getActivity(),
//                        Manifest.permission.READ_EXTERNAL_STORAGE);
//
//                if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
//                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//                    intent.setType("image/*");
//                    startActivityForResult(intent, GET_GALLERY_CODE1);
//
//                } else {
//                    ActivityCompat.requestPermissions(getActivity(),
//                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
//                            2000);
//                }
//
//
//            }
//        });


//        logoutBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(
//                        new ResultCallback<Status>() {
//                            @Override
//                            public void onResult(Status status) {
//                                if (status.isSuccess()){
//                                    gotoMainActivity();
//                                }else{
//                                    Toast.makeText(getContext(),"Session not close",Toast.LENGTH_LONG).show();
//                                }
//                            }
//                        });
//            }
//        });







        return root;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        gso =  new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestEmail()
//                .build();
//        googleApiClient=new GoogleApiClient.Builder(getContext())
//                .enableAutoManage(getActivity(),this)
//                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
//                .build();

    }


    private void gotoMainActivity(){





        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        currentUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    SharedPreferences pref = getContext().getSharedPreferences("login", 0); // 0 - for private mode
                    SharedPreferences.Editor editor = pref.edit();

                    editor.putString("mail", "-1"); // Storing boolean - true/false

                    editor.commit(); // commit changes
                    Intent intent=new Intent(getContext(), Splash_Activity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("TAG","Ocurrio un error durante la eliminación del usuario", e);
            }
        });




    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {


        switch (requestCode) {
            case GET_GALLERY_CODE:

                if (resultCode == RESULT_OK) {
                    Glide.with(getActivity()).load(data.getData()).into(imageView);

                    upload_Image_profile(data.getData());
                    progressDialog.show();
                } else if (requestCode == RESULT_CANCELED) {
                    Log.e("cancel", "cancel");

                }
                break;

            case GET_GALLERY_CODE0:

                if (resultCode == RESULT_OK) {
                    Glide.with(getActivity()).load(data.getData()).into(_profile_recycler_img0);

                    upload_Image(data.getData(), "img0");
                    progressDialog.show();
                } else if (requestCode == RESULT_CANCELED) {
                    Log.e("cancel", "cancel");

                }
                break;

            case GET_GALLERY_CODE1:

                if (resultCode == RESULT_OK) {
                    Glide.with(getActivity()).load(data.getData()).into(_profile_recycler_img1);

                    upload_Image(data.getData(), "img1");
                    progressDialog.show();
                } else if (requestCode == RESULT_CANCELED) {
                    Log.e("cancel", "cancel");

                }
                break;


            default: // none of these
                break;
        }


    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

    public void upload_Image_profile(final Uri uri1) {
        final StorageReference riversRef = mStorageRef
                .child("images")
                .child(sharedPreferences.getString("mail", "-1"))
                .child(uri1.getLastPathSegment());


        riversRef.putFile(uri1)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content

                        riversRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                // uri is your download path
                                Log.e("path", uri.toString());
                                AddNewUser.getInstance(getContext()).add_img_profile(String.valueOf(uri), sharedPreferences.getString("mail", "-1"));
                                sharedPreferences1.putString("img", uri.toString());
                                sharedPreferences1.apply();
                                progressDialog.dismiss();

                            }
                        });

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...

                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        Toast.makeText(getActivity(), "Try again", Toast.LENGTH_LONG).show();

                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        double progress = (100.0 * taskSnapshot.getBytesTransferred()
                                / taskSnapshot.getTotalByteCount());

                        progressDialog.setProgress((int) progress);
                        Log.e("pro", String.valueOf(progress));
                    }
                });
    }


    public void upload_Image(final Uri uri1, final String img0) {
        final StorageReference riversRef = mStorageRef
                .child("images")
                .child(sharedPreferences.getString("mail", "-1"))
                .child(uri1.getLastPathSegment());


        riversRef.putFile(uri1)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content

                        riversRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                // uri is your download path
                                Log.e("path", uri.toString());
                                AddNewUser.getInstance(getContext()).add_img(String.valueOf(uri), sharedPreferences.getString("mail", "-1"), img0);
                                sharedPreferences1.putString(img0, uri.toString());
                                sharedPreferences1.apply();
                                progressDialog.dismiss();

                            }
                        });

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...

                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        Toast.makeText(getActivity(), "Try again", Toast.LENGTH_LONG).show();

                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());

                        progressDialog.setProgress((int) progress);
                        Log.e("pro", String.valueOf(progress));
                    }
                });
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }





    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onStop() {
        super.onStop();
//        googleApiClient.stopAutoManage(getActivity());
//        googleApiClient.disconnect();
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        googleApiClient.stopAutoManage(getActivity());
//        googleApiClient.disconnect();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        googleApiClient.stopAutoManage(getActivity());
//        googleApiClient.disconnect();
    }
}