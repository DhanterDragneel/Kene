package id.ac.pnm.studentportalnewver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    String KEY_NPM = "NPM", npm, npmOut;
    TextView masterNPM,masterNama;
    Button btnprofil, btnDosen, btnPerkuliahan,btnSetting;
    JSONObject jsonObject;
    String url = "https://dekadragonslayer.000webhostapp.com/dtdiri/readdiribynpm.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        masterNama = (TextView) findViewById(R.id.masterNama);
        masterNPM = (TextView) findViewById(R.id.masterNPM);
        Bundle extras = getIntent().getExtras();
        npm = extras.getString(KEY_NPM);
        masterNPM.setText(npm);
        btnprofil = (Button) findViewById(R.id.btnProfil);
        btnprofil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String npm = masterNPM.getText().toString();
                Intent intent = new Intent(MainActivity.this, Profile.class);
                intent.putExtra("npmE", npm);
                startActivity(intent);
            }
        });
        btnDosen = (Button) findViewById(R.id.btnDosen);
        btnDosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Dosen.class);
                startActivity(intent);
            }
        });
        btnPerkuliahan = (Button) findViewById(R.id.btnPerkuliahan);
        btnPerkuliahan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Perkuliahan.class);
                startActivity(intent);
            }
        });
        btnSetting = (Button) findViewById(R.id.btnSetting);
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String npm = masterNPM.getText().toString();
                Intent intent = new Intent(MainActivity.this, Setting.class);
                intent.putExtra("npmC", npm);
                startActivity(intent);
            }
        });

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String s = response;
                try {
                    jsonObject = new JSONObject(s);
                    JSONArray a = jsonObject.getJSONArray("result");
                    for (int i = 0; i < a.length(); i++) {
                        JSONObject c = a.getJSONObject(i);
                        String namaLengkap = c.getString("namaLengkap");


                        HashMap<String, String> resultx = new HashMap<>();
                        masterNama.setText(namaLengkap);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("npm", npm);
                return params;

            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(request);
    }
}
