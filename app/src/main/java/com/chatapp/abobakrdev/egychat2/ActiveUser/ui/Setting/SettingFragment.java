package com.chatapp.abobakrdev.egychat2.ActiveUser.ui.Setting;

import android.Manifest;
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
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.chatapp.abobakrdev.egychat2.AddNewUser.AddNewUser;
import com.chatapp.abobakrdev.egychat2.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

public class SettingFragment extends Fragment {


    private SharedPreferences sharedPreferences;

    private String name;
    private String mail;
    private String age;
    private String gender;
    private String img;
    private String phone,Gmail;
    private ImageView imageView;
    private TextView gander, date, _phone, _mail,_my_name;
    private ImageView _add_img,Add0,Add1,_profile_recycler_img0,_profile_recycler_img1;
    final private int GET_GALLERY_CODE =111;
    final private int GET_GALLERY_CODE0 =110;

    final private int GET_GALLERY_CODE1 =1999;

    private ImageView imageView0;
    private StorageReference mStorageRef;
    private AddNewUser addNewUser ;
    private SharedPreferences.Editor sharedPreferences1 ;
    private ProgressDialog progressDialog ;
    private FrameLayout frameLayout ;




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.myprofile_layout, container, false);
        frameLayout=root.findViewById(R.id.frameLayout);
        gander=root.findViewById(R.id._gender);
        date=root.findViewById(R.id._date);
        _phone =root.findViewById(R.id._phone);
        _mail=root.findViewById(R.id._mail);
        _my_name=root.findViewById(R.id._my_name);
        _add_img =root.findViewById(R.id._edit_img);
        _profile_recycler_img0=root.findViewById(R.id._profile_recycler_img0);
        _profile_recycler_img1=root.findViewById(R.id._profile_recycler_img1);
        Add0 =root.findViewById(R.id.Add0);
        Add1 =root.findViewById(R.id.Add1);
        addNewUser=new AddNewUser(getActivity());
        progressDialog =new ProgressDialog(getActivity());
        progressDialog.setTitle("Upload New Image");
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);


        sharedPreferences1=getActivity().getSharedPreferences("login", Context.MODE_PRIVATE).edit();

        imageView = root.findViewById(R.id._profile_img);
        sharedPreferences = getActivity().getSharedPreferences("login", MODE_PRIVATE);

        name = sharedPreferences.getString("name", "-1");
        Gmail=sharedPreferences.getString("Gmail", "-1");
        mail = sharedPreferences.getString("mail", "-1");
        age = sharedPreferences.getString("age", "-1");
        gender = sharedPreferences.getString("gender", "-1");
        img = sharedPreferences.getString("img", "-1");
        phone = sharedPreferences.getString("phone", "-1");
        mStorageRef = FirebaseStorage.getInstance().getReference();
        Log.e("age",age);
        _my_name.setText(name);
        date.setText(age);
        gander.setText(gender);
        _phone.setText(phone);
        _mail.setText(Gmail);

        Glide.with(getActivity()).load(img).into(imageView);
        Glide.with(getActivity()).load(sharedPreferences.getString("img0","-1")).into(_profile_recycler_img0);

        Glide.with(getActivity()).load(sharedPreferences.getString("img1","-1")).into(_profile_recycler_img1);






        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(getContext(),Viewimage.class);
                intent.putExtra("path",sharedPreferences.getString("img","-1"));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().startActivity(intent);
                getActivity().overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );



            }
        });

       _profile_recycler_img0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(getContext(),Viewimage.class);
                intent.putExtra("path",sharedPreferences.getString("img0","-1"));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().startActivity(intent);
                getActivity().overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );



            }
        });

        _profile_recycler_img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(getContext(),Viewimage.class);
                intent.putExtra("path",sharedPreferences.getString("img1","-1"));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().startActivity(intent);
                getActivity().overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );




            }
        });


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


        Add0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int permissionCheck = ContextCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.READ_EXTERNAL_STORAGE);

                if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");
                    startActivityForResult(intent, GET_GALLERY_CODE0);

                } else {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            2000);
                }


            }
        });



        Add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int permissionCheck = ContextCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.READ_EXTERNAL_STORAGE);

                if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");
                    startActivityForResult(intent, GET_GALLERY_CODE1);

                } else {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            2000);
                }


            }
        });

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {



        switch (requestCode) {
            case GET_GALLERY_CODE:

                if (resultCode == RESULT_OK) {
                        Glide.with(getActivity()).load(data.getData()).into(imageView);

                        upload_Image_profile(data.getData());
                        progressDialog.show();
                }
                else if(requestCode==RESULT_CANCELED)
                {
                    Log.e("cancel","cancel");

                }
                break;

            case GET_GALLERY_CODE0:

                if (resultCode == RESULT_OK) {
                    Glide.with(getActivity()).load(data.getData()).into(_profile_recycler_img0);

                    upload_Image(data.getData(),"img0");
                    progressDialog.show();
                }
                else if(requestCode==RESULT_CANCELED)
                {
                    Log.e("cancel","cancel");

                }
                break;

            case GET_GALLERY_CODE1:

                if (resultCode == RESULT_OK) {
                    Glide.with(getActivity()).load(data.getData()).into(_profile_recycler_img1);

                    upload_Image(data.getData(),"img1");
                    progressDialog.show();
                }
                else if(requestCode==RESULT_CANCELED)
                {
                    Log.e("cancel","cancel");

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

    public void upload_Image_profile(final Uri uri1 )
    {
        final StorageReference riversRef = mStorageRef
                .child("images")
                .child(sharedPreferences.getString("mail","-1"))
                .child(uri1.getLastPathSegment());


        riversRef.putFile(uri1)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content

                        riversRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()
                        {
                            @Override
                            public void onSuccess(Uri uri)
                            {
                                // uri is your download path
                                Log.e("path",uri.toString());
                                addNewUser.add_img_profile(String.valueOf(uri),sharedPreferences.getString("mail","-1"));
                                sharedPreferences1.putString("img",uri.toString());
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

                        if(progressDialog.isShowing())
                        {
                            progressDialog.dismiss();
                        }
                        Toast.makeText(getActivity(),"Try again",Toast.LENGTH_LONG).show();

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



    public void upload_Image(final Uri uri1, final String img0)
    {
        final StorageReference riversRef = mStorageRef
                .child("images")
                .child(sharedPreferences.getString("mail","-1"))
                .child(uri1.getLastPathSegment());



        riversRef.putFile(uri1)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content

                        riversRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()
                        {
                            @Override
                            public void onSuccess(Uri uri)
                            {
                                // uri is your download path
                                Log.e("path",uri.toString());
                                addNewUser.add_img(String.valueOf(uri),sharedPreferences.getString("mail","-1"),img0);
                                sharedPreferences1.putString(img0,uri1.toString());
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

                        if(progressDialog.isShowing())
                        {
                            progressDialog.dismiss();
                        }
                        Toast.makeText(getActivity(),"Try again",Toast.LENGTH_LONG).show();

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




}