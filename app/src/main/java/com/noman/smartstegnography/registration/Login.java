package com.noman.smartstegnography.registration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.noman.smartstegnography.Dashboard;
import com.noman.smartstegnography.R;
import com.noman.smartstegnography.helper.SharePreferences;

public class Login extends AppCompatActivity {
    //Declare Variable.
    TextView textView_login;
    Button button_login;
    EditText editText_email, editText_pass;
    String email, pass;
    FirebaseAuth mAuth;
    SharePreferences sharePreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );
        //Variables initilaize
        textView_login = findViewById( R.id.textView_login );
        button_login = findViewById( R.id.btn_login );
        editText_email = findViewById( R.id.login_email );
        editText_pass = findViewById( R.id.login_pass );
        sharePreferences = new SharePreferences(getApplicationContext());
        mAuth = FirebaseAuth.getInstance();

        textView_login.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( Login.this, SignUp.class );
                startActivity( intent );
            }
        } );
        button_login.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean error = validationInputs();
                if(error){
                    return;
                }
                else {
//                    startActivity(new Intent(Login.this, MainActivity.class));
                    signIn( email, pass );

                }
            }
        } );

    }

    private boolean validationInputs(){
        boolean errorCheck=false;
        email = editText_email.getText().toString();
        pass = editText_pass.getText().toString();
        if(email.equals( "" )){
            editText_email.setError( "Please Enter Email" );
            errorCheck = true;
        }
        if(pass.equals( "" )){
            editText_pass.setError( "Please Enter Password" );
            errorCheck= true;
        }

        return errorCheck;

    }
    private void signIn(final String email, String pass){
        mAuth.signInWithEmailAndPassword( email, pass )
                .addOnCompleteListener( this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText( Login.this.getApplicationContext(), "Login SucessFul", Toast.LENGTH_SHORT ).show();
                            sharePreferences.setLogin(true);
                            sharePreferences.setUserEmail(email);
                            Login.this.startActivity( new Intent( Login.this, Dashboard.class ) );
                            Login.this.finish();
                        } else {
                            Toast.makeText( Login.this.getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT ).show();
                        }
                    }
                } );
    }

}
