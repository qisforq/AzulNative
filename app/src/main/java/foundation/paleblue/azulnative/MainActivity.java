package foundation.paleblue.azulnative;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void clickSignup(View view) {

        EditText firstNameEditText = (EditText) findViewById(R.id.firstNameEditText);
        EditText lastNameEditText = (EditText) findViewById(R.id.lastNameEditText);
        EditText emailEditText = (EditText) findViewById(R.id.emailEditText);

        Log.i("Info", "Signup button clicked!!");
        Log.i("Values", firstNameEditText.getText().toString());
        Log.i("Values", lastNameEditText.getText().toString());
        Log.i("Values", emailEditText.getText().toString());

        Toast.makeText(this, "Hola " + firstNameEditText.getText().toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
