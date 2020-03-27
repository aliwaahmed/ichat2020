package com.chatapp.abobakrdev.egychat2.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.SharedPreferencesKt;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chatapp.abobakrdev.egychat2.ActiveUser.home;
import com.chatapp.abobakrdev.egychat2.login.CompleteInfo.Completeinfo;
import com.chatapp.abobakrdev.egychat2.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    SignInButton signInButton;
    private GoogleApiClient googleApiClient;
    TextView textView;
    private static final int RC_SIGN_IN = 1;
    FirebaseAuth mAuth;
    private GoogleSignInAccount account;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);



        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "-1");
        String mail = sharedPreferences.getString("mail", "-1");
        String age = sharedPreferences.getString("age", "-1");
        String gender = sharedPreferences.getString("gender", "-1");
        String img = sharedPreferences.getString("img", "-1");

        if (!mail.equals("-1")) {

            Intent intent = new Intent(this, home.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            this.finish();
        } else {

            FirebaseApp.initializeApp(getApplicationContext());

            mAuth = FirebaseAuth.getInstance();

            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .requestIdToken("120873551649-8283s03tmnp17nf7mh8s7sm7uajf4ove.apps.googleusercontent.com")
                    .build();
            googleApiClient = new GoogleApiClient.Builder(this)
                    .enableAutoManage(MainActivity.this, this)
                    .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                    .build();


            signInButton = (SignInButton) findViewById(R.id.sign_in_button);
            signInButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                    startActivityForResult(intent, RC_SIGN_IN);
                }
            });


        }
        //  sendVerificationCode("01015124020");
    }


//    private void sendVerificationCode(String mobile) {
//        PhoneAuthProvider.getInstance().verifyPhoneNumber(
//                "+2" + mobile,
//                60,
//                TimeUnit.SECONDS,
//                TaskExecutors.MAIN_THREAD,
//                mCallbacks);
//    }
//
//
//    String code1;
//
//    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//        @Override
//        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
//            //Getting the code sent by SMS
//            String code = phoneAuthCredential.getSmsCode();
//
//
//
//            //sometime the code is not detected automatically
//            //in this case the code will be null
//            //so user has to manually enter the code
//            if (code != null) {
//                Log.e("code",code);
////                editTextCode.setText(code);
////                //verifying the code
//                verifyVerificationCode(code,code1);
//            }
//        }
//
//
//        @Override
//        public void onVerificationFailed(FirebaseException e) {
//        }
//
//        @Override
//        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
//            super.onCodeSent(s, forceResendingToken);
//
//
//            code1=s;
//
//        }
//    };
//
//    private void verifyVerificationCode(String otp,String  mAuthoid) {
//        //creating the credential
//        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mAuthoid, otp);
//
//        //signing the user
//        signInWithPhoneAuthCredential(credential);
//    }
//
//    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
//        mAuth.signInWithCredential(credential)
//                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
////                            //verification successful we will start the profile activity
////                            Intent intent = new Intent(VerifyPhoneActivity.this, ProfileActivity.class);
////                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
////                            startActivity(intent);
//
//                        } else {
//
//                            //verification unsuccessful.. display an error message
//
//                            String message = "Somthing is wrong, we will fix it soon...";
//
//                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
//                                message = "Invalid code entered...";
//                            }
//
//                            Snackbar snackbar = Snackbar.make(findViewById(R.id.parent), message, Snackbar.LENGTH_LONG);
//                            snackbar.setAction("Dismiss", new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//
//                                }
//                            });
//                            snackbar.show();
//                        }
//                    }
//                });
//    }
//
//
//
//
//
//
//
//
//


    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {

        Log.d("TAG", "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        SharedPreferences.Editor sharedPreferences = getSharedPreferences("credential",MODE_PRIVATE).edit();
        sharedPreferences.putString("credential",acct.getIdToken());
        sharedPreferences.apply();
        sharedPreferences.commit();
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithCredential:failure", task.getException());
                        }


                    }
                });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.e("result", String.valueOf(result.getStatus()));
        if (result.isSuccess()) {


            account = result.getSignInAccount();


            gotoProfile();
            firebaseAuthWithGoogle(result.getSignInAccount());


        } else {
            Toast.makeText(getApplicationContext(), "Sign in cancel", Toast.LENGTH_LONG).show();
        }
    }

    private void gotoProfile() {


        Intent intent = new Intent(MainActivity.this, Completeinfo.class);
        intent.putExtra("name", account.getDisplayName());
        intent.putExtra("mail", account.getEmail());
        intent.putExtra("img", account.getPhotoUrl().toString());
        startActivity(intent);
        this.finish();
    }


}