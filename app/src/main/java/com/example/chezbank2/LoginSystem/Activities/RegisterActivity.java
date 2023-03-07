package com.example.chezbank2.LoginSystem.Activities;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.chezbank2.MainActivity;
import com.example.chezbank2.Obj.User;
import com.example.chezbank2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    private TextView fname, lname, email, password, passwordRpt, age, cnp;
    private TextView regMsg;
    private Button signup;
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fname = findViewById(R.id.registerFname);
        lname = findViewById(R.id.registerLname);
        email = findViewById(R.id.registerEmail);
        password = findViewById(R.id.registerPassword);
        passwordRpt = findViewById(R.id.registerPasswordRpt);
        age = findViewById(R.id.registerAge);
        cnp = findViewById(R.id.registerCnp);
        signup = findViewById(R.id.signUpBtnReg2);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateFields();
                User user = new User(fname.getText().toString(),
                                    lname.getText().toString(),
                                    email.getText().toString(),
                                    password.getText().toString(),
                                    age.getText().toString(),
                                    cnp.getText().toString());

                auth.createUserWithEmailAndPassword(user.getUserEmail(), user.getUserPwd()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseDatabase.getInstance("https://chezbank2-default-rtdb.europe-west1.firebasedatabase.app").getReference("users")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()){
                                            Toast.makeText(RegisterActivity.this, "User created!", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                            startActivity(intent);
                                        }
                                        else {
                                            Toast.makeText(RegisterActivity.this, "User was not created!", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                        }
                        else{
                        }
                    }
                });
            }
        });
    }
    private void validateFields(){
        if (fname.getText().toString().isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Empty fields!", Toast.LENGTH_SHORT).show();
            fname.setError("Empty field");
        }
        else if(lname.getText().toString().isEmpty()){
            Toast.makeText(RegisterActivity.this, "Empty fields!", Toast.LENGTH_SHORT).show();
            lname.setError("Empty field");
        }
        else if(email.getText().toString().isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Empty fields!", Toast.LENGTH_SHORT).show();
            email.setError("Empty field");
        }
        else if(password.getText().toString().isEmpty()){
            Toast.makeText(RegisterActivity.this, "Empty fields!", Toast.LENGTH_SHORT).show();
            password.setError("Empty field");
        }
        else if (passwordRpt.getText().toString().isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Empty fields!", Toast.LENGTH_SHORT).show();
            passwordRpt.setError("Empty field");
        }
        else if(age.getText().toString().isEmpty()){
            Toast.makeText(RegisterActivity.this, "Empty fields!", Toast.LENGTH_SHORT).show();
            age.setError("Empty field");
        }
        else if(cnp.getText().toString().isEmpty()){
            Toast.makeText(RegisterActivity.this, "Empty fields!", Toast.LENGTH_SHORT).show();
            cnp.setError("Empty field");
        }
        else if (!password.getText().toString().equals(passwordRpt.getText().toString())){
            Toast.makeText(RegisterActivity.this, "Passwords didn't match!", Toast.LENGTH_SHORT).show();
        }
        else if(password.getText().toString().length() < 6){
            Toast.makeText(RegisterActivity.this, "Password is too short!", Toast.LENGTH_SHORT).show();
        }
    }
}