package com.chatapp.abobakrdev.egychat2.ActiveUser.ui.Setting;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.chatapp.abobakrdev.egychat2.ActiveUser.ui.Live.model.model;
import com.chatapp.abobakrdev.egychat2.AddNewUser.FirebaseOperation;
import com.chatapp.abobakrdev.egychat2.R;
import com.chatapp.abobakrdev.egychat2.login.Splash_Activity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

public class SettingFragment extends Fragment {


    private static final int GET_GALLERY_CODE2 = 1000;
    private ImageView edit1, edit2, edit3;
    private RoundedImageView img1, img2, img3;


    private SharedPreferences sharedPreferences;
    private String name;
    private String mail;
    private String age;
    private String gender;
    private String img;
    private String phone, Gmail;
    private ImageView imageView, male_female;
    private TextView date, _phone, _mail, _my_name, text_about, male, views;


    final private int GET_GALLERY_CODE = 111;
    final private int GET_GALLERY_CODE0 = 110;

    final private int GET_GALLERY_CODE1 = 1999;

    private StorageReference mStorageRef;
    private SharedPreferences.Editor sharedPreferences1;
    private ProgressDialog progressDialog;


    private ConstraintLayout logout;

    private ImageView edite, _add_img;
    private Button update, cancel;

    private String Temp_Bio, Temp_phone, Temp_date;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.activity_scrolling, container, false);


        views = root.findViewById(R.id.views);
        edit1 = root.findViewById(R.id.edite1);
        edit2 = root.findViewById(R.id.edite2);
        edit3 = root.findViewById(R.id.edite3);
        logout = root.findViewById(R.id._con_logout);
        img1 = root.findViewById(R.id.img1);
        img2 = root.findViewById(R.id.img2);
        img3 = root.findViewById(R.id.img3);
        _add_img = root.findViewById(R.id.profileimg);


        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Wait Upload");

        male = root.findViewById(R.id.male);
        date = root.findViewById(R.id.date);
        _phone = root.findViewById(R.id.phone);
        male_female = root.findViewById(R.id.imageView13);
        _mail = root.findViewById(R.id.mail);
        text_about = root.findViewById(R.id.text_about);
        _my_name = root.findViewById(R.id.name);
        cancel = root.findViewById(R.id.cancel);
        edite = root.findViewById(R.id.imageView14);
        update = root.findViewById(R.id.update);
        update.setVisibility(View.GONE);
        cancel.setVisibility(View.GONE);
        text_about.setEnabled(false);
        _phone.setEnabled(false);
        date.setEnabled(false);
        _mail.setEnabled(false);


        sharedPreferences1 = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE).edit();

        imageView = root.findViewById(R.id.imageView10);
        sharedPreferences = getActivity().getSharedPreferences("login", MODE_PRIVATE);

        name = sharedPreferences.getString("name", "-1");
        Gmail = sharedPreferences.getString("Gmail", "-1");
        mail = sharedPreferences.getString("mail", "-1");
        age = sharedPreferences.getString("age", "-1");
        gender = sharedPreferences.getString("gender", "-1");
        img = sharedPreferences.getString("img", "-1");
        phone = sharedPreferences.getString("phone", "-1");


        setviews(mail);


        edite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update.setVisibility(View.VISIBLE);
                cancel.setVisibility(View.VISIBLE);
                text_about.setTextColor(Color.RED);
                _phone.setTextColor(Color.RED);
                date.setTextColor(Color.RED);
                Temp_Bio = (text_about.getText().toString());
                Temp_date = date.getText().toString();
                Temp_phone = _phone.getText().toString();
                edite.setColorFilter(Color.RED);
                text_about.setEnabled(true);
                text_about.setEnabled(true);
                _phone.setEnabled(true);
                date.setEnabled(true);


            }
        });


        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog.OnDateSetListener pickerListener = null;

                pickerListener = new DatePickerDialog.OnDateSetListener() {

                    // when dialog box is closed, below method will be called.
                    @Override
                    public void onDateSet(DatePicker view, int selectedYear,
                                          int selectedMonth, int selectedDay) {

                        int year = selectedYear;
                        int month = selectedMonth;
                        int day = selectedDay;

                        // Show selected date
                        date.setText(new StringBuilder().append(month + 1)
                                .append("-").append(day).append("-").append(year)
                                .append(" "));

                    }
                };
                DatePickerDialog dialog = new DatePickerDialog(getContext(), pickerListener, 1995, 1, 1);
                dialog.show();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update.setVisibility(View.GONE);
                cancel.setVisibility(View.GONE);

                _phone.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
                date.setBackgroundColor(getActivity().getResources().getColor(R.color.white));

                text_about.setTextColor(getActivity().getResources().getColor(R.color.gray));
                _phone.setTextColor(getActivity().getResources().getColor(R.color.black));
                date.setTextColor(getActivity().getResources().getColor(R.color.black));
                text_about.setText(Temp_Bio);
                _phone.setText(Temp_phone);
                date.setText(Temp_date);
                edite.setColorFilter(Color.BLACK);
                text_about.setEnabled(false);
                _phone.setEnabled(false);
                date.setEnabled(false);
                _mail.setEnabled(false);


            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!date.getText().toString().isEmpty()) {
                    FirebaseOperation.getInstance(getContext()).update_about_me(mail,
                            text_about.getText().toString(),
                            _phone.getText().toString(),
                            date.getText().toString());
                    edite.setColorFilter(Color.BLACK);
                    text_about.setEnabled(false);
                    _phone.setEnabled(false);
                    date.setEnabled(false);
                    _mail.setEnabled(false);
                    update.setVisibility(View.GONE);
                    cancel.setVisibility(View.GONE);
                    _phone.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
                    date.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
                    text_about.setTextColor(getActivity().getResources().getColor(R.color.gray));
                    _phone.setTextColor(getActivity().getResources().getColor(R.color.black));
                    date.setTextColor(getActivity().getResources().getColor(R.color.black));
                } else {
                    date.setError("Enter Date");
                }
            }
        });


        if (gender.toString().toLowerCase().trim().equals("male")) {
            male_female.setBackgroundResource(R.drawable.male);
        } else {
            male_female.setBackgroundResource(R.drawable.female);
        }

        mStorageRef = FirebaseStorage.getInstance().getReference();
        Log.e("age", age);
        _mail.setText(Gmail);
        date.setText(age);
        male.setText(gender);
        _phone.setText(phone);
        _my_name.setText(name);
        text_about.setText(sharedPreferences.getString("about", "Bio"));
        Glide.with(getActivity()).load(img).into(imageView);


        Glide.with(getActivity()).load(sharedPreferences.getString("img0", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSjZTzWoX3vA4nnkaEfcdudteE6inB-xTcNCSG3iZzNmoVWCyhn")).into(img1);

        Glide.with(getActivity()).load(sharedPreferences.getString("img1", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSjZTzWoX3vA4nnkaEfcdudteE6inB-xTcNCSG3iZzNmoVWCyhn")).into(img2);

        Glide.with(getActivity()).load(sharedPreferences.getString("img2", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSjZTzWoX3vA4nnkaEfcdudteE6inB-xTcNCSG3iZzNmoVWCyhn")).into(img3);


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


        edit1.setOnClickListener(new View.OnClickListener() {
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

        edit2.setOnClickListener(new View.OnClickListener() {
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

        edit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int permissionCheck = ContextCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.READ_EXTERNAL_STORAGE);

                if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");
                    startActivityForResult(intent, GET_GALLERY_CODE2);

                } else {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            2000);
                }


            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                final FirebaseUser currentUser = firebaseAuth.getCurrentUser();
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("credential", MODE_PRIVATE);
                AuthCredential credential = GoogleAuthProvider.getCredential(sharedPreferences.getString("credential", "-1"), null);


                firebaseAuth.signInWithCredential(credential);

                FirebaseDatabase database = FirebaseDatabase.getInstance();

                DatabaseReference myRef = database.getReference("active_user");
                myRef.child(mail).removeValue();


                currentUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {




                            SharedPreferences pref = getContext().getSharedPreferences("login", 0); // 0 - for private mode
                            SharedPreferences.Editor editor = pref.edit();

                            editor.putString("mail", "-1"); // Storing boolean - true/false

                            editor.commit(); // commit changes

                            Intent intent = new Intent(getContext(), Splash_Activity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            getContext().startActivity(intent);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("TAG", "Ocurrio un error durante la eliminación del usuario", e);
                    }
                });


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
                    progressDialog.setCancelable(false);

                } else if (requestCode == RESULT_CANCELED) {
                    Log.e("cancel", "cancel");

                }
                break;

            case GET_GALLERY_CODE0:

                if (resultCode == RESULT_OK) {
                    Glide.with(getActivity()).load(data.getData()).into(img1);

                    upload_Image(data.getData(), "img0");
                    progressDialog.show();
                    progressDialog.setCancelable(false);
                } else if (requestCode == RESULT_CANCELED) {
                    Log.e("cancel", "cancel");

                }
                break;

            case GET_GALLERY_CODE1:

                if (resultCode == RESULT_OK) {
                    Glide.with(getActivity()).load(data.getData()).into(img2);

                    upload_Image(data.getData(), "img1");
                    progressDialog.show();
                    progressDialog.setCancelable(false);

                } else if (requestCode == RESULT_CANCELED) {
                    Log.e("cancel", "cancel");

                }
                break;
            case GET_GALLERY_CODE2:

                if (resultCode == RESULT_OK) {
                    Glide.with(getActivity()).load(data.getData()).into(img3);

                    upload_Image(data.getData(), "img2");
                    progressDialog.show();
                    progressDialog.setCancelable(false);

                } else if (requestCode == RESULT_CANCELED) {
                    Log.e("cancel", "cancel");

                }
                break;


            default: // none of these
                break;
        }


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
                                FirebaseOperation.getInstance(getContext()).add_img_profile(String.valueOf(uri), sharedPreferences.getString("mail", "-1"));
                                sharedPreferences1.putString("img", uri.toString());
                                sharedPreferences1.apply();
                                if (progressDialog.isShowing()) {
                                    progressDialog.dismiss();
                                }

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
                                FirebaseOperation.getInstance(getContext()).add_img(String.valueOf(uri), sharedPreferences.getString("mail", "-1"), img0);
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


    public void setviews(String mail) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        DatabaseReference user = firebaseDatabase.getReference("users");

        user.child(mail).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {


                Log.e("views", dataSnapshot.getValue().toString());

                if (dataSnapshot.getKey().equals("views")) {
                    views.setText(dataSnapshot.getValue().toString());
                }


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {


                Log.e("views", dataSnapshot.getValue().toString());
                if (dataSnapshot.getKey().equals("views")) {
                    views.setText(dataSnapshot.getValue().toString());
                }

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


}