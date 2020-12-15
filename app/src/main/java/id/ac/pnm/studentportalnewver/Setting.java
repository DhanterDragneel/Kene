package id.ac.pnm.studentportalnewver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Setting extends AppCompatActivity {
    TextView npmkene;
    String npm;
    Button btnUbahDtdr, btnubhPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        npmkene = (TextView) findViewById(R.id.npmKene);
        final Bundle extras = getIntent().getExtras();
        npm = extras.getString("npmC");
        npmkene.setText(npm);
        btnUbahDtdr = (Button) findViewById(R.id.btnUbahdtdr);
        btnUbahDtdr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String npm = npmkene.getText().toString();
                Intent intent = new Intent(Setting.this, UbahDataDiri.class);
                intent.putExtra("NPM_ubahdtdiri", npm);
                startActivity(intent);
            }
        });
        btnubhPassword = (Button) findViewById(R.id.btnUbahpass);
        btnubhPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String npm = npmkene.getText().toString();
                Intent intent = new Intent(Setting.this, UbahPassword.class);
                intent.putExtra("NPM_ubhpassword", npm);
                startActivity(intent);
            }
        });
    }
}
