package com.noman.smartstegnography.registration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.noman.smartstegnography.R;

public class SignUp extends AppCompatActivity {
    TextView textView_signup;
    Button button_signUp;
    EditText editText_email, editText_pass, editText_confirmPass;
    ProgressBar progressBar;
    String email, pass, confirmPass,emailPattern;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_sign_up );
        textView_signup = findViewById( R.id.textView_signup );
        button_signUp= findViewById( R.id.btn_signUp );
        editText_confirmPass = findViewById( R.id.editText_confirPass );
        editText_pass = findViewById( R.id.editText_pass );
        editText_email = findViewById( R.id.editText_email );
        progressBar = findViewById( R.id.progressBar );
        mAuth = FirebaseAuth.getInstance();

        textView_signup.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( SignUp.this, Login.class );
                startActivity( intent );
            }
        } );
        button_signUp.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean error = validationInputs();
                if(error)
                    return;
                else {
                    creatingUser(email, pass);
                }


            }
        } );
    }
    private boolean validationInputs(){
        boolean errorCheck=false;
        emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        email = editText_email.getText().toString();
        pass = editText_pass.getText().toString();
        confirmPass = editText_confirmPass.getText().toString();
        if(email.matches( emailPattern )){
            editText_email.setError( null );
        }
        else {
            editText_email.setError( "Please Enter Email" );
            errorCheck=true;
        }
        if(pass.equals("")){
            editText_pass.setError( "Please Enter Password" );
            errorCheck = true;
        }
        if(pass.length()<6){
            editText_pass.setError( " Pass Must be 6 digits" );
            errorCheck = true;
        }
        if(confirmPass.equals( "" )){
            editText_confirmPass.setError( "Please Enter Password" );
            errorCheck = true;
        }
        if(!confirmPass.equals( pass )) {
            editText_confirmPass.setError( "Password not matched" );
            errorCheck = true;
        }

    return errorCheck;

    }
    private void creatingUser(String email, String pass){
        mAuth.createUserWithEmailAndPassword( email, pass )
                .addOnCompleteListener( this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressBar.setVisibility( View.GONE );
                            Toast.makeText( SignUp.this.getApplicationContext(), "Registration SucessFull", Toast.LENGTH_SHORT ).show();
                            Intent intent = new Intent( SignUp.this, Login.class );
                            SignUp.this.startActivity( intent );
                            SignUp.this.finish();
                        } else {
                            Toast.makeText( SignUp.this.getApplicationContext(), "Registration Failed", Toast.LENGTH_SHORT ).show();
                        }
                    }
                } );
    }
}
