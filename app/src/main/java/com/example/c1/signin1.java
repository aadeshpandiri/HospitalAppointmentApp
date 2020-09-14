package com.example.c1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class signin1 extends AppCompatActivity {

    SignInButton signInButton;

    GoogleSignInClient mGoogleSignInClient;
    String TAG="MainActivity";
    FirebaseAuth mAuth;
    Button btnSignOut,pro;
    int RC_SIGN_IN=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin1);

        signInButton=findViewById(R.id.sign_in_button);

        mAuth =FirebaseAuth.getInstance();
        btnSignOut=findViewById(R.id.signin);
        pro=findViewById(R.id.proc4);

        btnSignOut.setVisibility(View.INVISIBLE);
        pro.setVisibility(View.INVISIBLE);



        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient= GoogleSignIn.getClient(this,gso);

      signInButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              signIn();

          }
      });

       btnSignOut.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               mGoogleSignInClient.signOut();
               Toast.makeText(signin1.this,"You are Logged Out",Toast.LENGTH_SHORT).show();

               btnSignOut.setVisibility(View.INVISIBLE);
               pro.setVisibility(View.INVISIBLE);


           }

       });

       pro.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               startActivity(new Intent(signin1.this,navact.class));

           }
       });

    }
    private void signIn(){

        Intent signInIntent=mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent,RC_SIGN_IN);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);

            }


}

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {

        try{

            GoogleSignInAccount acc=completedTask.getResult(ApiException.class);

             //Toast.makeText(signin1.this,"Signed In Successfully",Toast.LENGTH_LONG).show();

             FirebaseGoogleAuth(acc);

        } catch (ApiException e) {

            //Toast.makeText(signin1.this,"Failed",Toast.LENGTH_LONG).show();

        }

    }

    private void FirebaseGoogleAuth(GoogleSignInAccount acct) {

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(signin1.this,"Succesfull",Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {

                            Toast.makeText(signin1.this,"Failed",Toast.LENGTH_SHORT).show();

                            updateUI(null);
                        }

                    }
                });
    }

    private  void updateUI(FirebaseUser fUser)
    {
        btnSignOut.setVisibility(View.VISIBLE);
        pro.setVisibility(View.VISIBLE);


        GoogleSignInAccount account=GoogleSignIn.getLastSignedInAccount(getApplicationContext());

        if(account!=null)
        {
            String personname=account.getDisplayName();
            String personEmail=account.getEmail();


        }

    }

}