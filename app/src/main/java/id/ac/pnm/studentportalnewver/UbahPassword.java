package id.ac.pnm.studentportalnewver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UbahPassword extends AppCompatActivity {
    private EditText namaSetting, passwordSetting, npmSetting, namaSetting1, passwordSetting1, npmSetting1;
    String namaSetting2, passwordSetting2, npmSetting2, npm;
    Button btnSetting;
    String url2 = "https://dekadragonslayer.000webhostapp.com/login/editlogin.php";
    String url1 = "https://dekadragonslayer.000webhostapp.com/login/readloginbynpm.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_password);
        npmSetting = (EditText) findViewById(R.id.npmSetting);
        final Bundle extras = getIntent().getExtras();
        npm = extras.getString("NPM_ubhpassword");
        npmSetting.setText(npm);

        npmSetting = (EditText) findViewById(R.id.npmSetting);
        passwordSetting = (EditText) findViewById(R.id.passwordSetting);

        StringRequest request = new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String s = response;
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray a = jsonObject.getJSONArray("result");
                    for (int i = 0; i < a.length(); i++) {
                        JSONObject c = a.getJSONObject(i);
                        String npm = c.getString("npm");

                        HashMap<String, String> resultx = new HashMap<>();
                        npmSetting.setText(npm);
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

        RequestQueue requestQueue = Volley.newRequestQueue(UbahPassword.this);
        requestQueue.add(request);


        npmSetting1 = (EditText) findViewById(R.id.npmSetting);
        passwordSetting1 = (EditText) findViewById(R.id.passwordSetting);
        btnSetting = (Button) findViewById(R.id.btnUbahPassword);

        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                npmSetting2 = npmSetting1.getText().toString().trim();
                passwordSetting2 = md5(passwordSetting1.getText().toString().trim());
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String s = response;
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONArray a = jsonObject.getJSONArray("result");
                            for (int i = 0; i < a.length(); i++) {
                                JSONObject c = a.getJSONObject(i);
                                String npm = c.getString("npm");

                                npmSetting.setText(npm);
                                passwordSetting.setText("");
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

                        params.put("npm", npmSetting2);
                        params.put("password", passwordSetting2);
                        return params;

                    }
                };

                passwordSetting.setText("");
                String berhasil = "Password Berhasil Diubah";
                Toast.makeText(UbahPassword.this, berhasil, Toast.LENGTH_SHORT).show();
                RequestQueue requestQ = Volley.newRequestQueue(UbahPassword.this);
                requestQ.add(stringRequest);
                Intent intent = new Intent(UbahPassword.this, UbahPassword.class);
                intent.putExtra("NPM_ubhpassword", npm);
                startActivity(intent);
            }

        });
    }
    public static String md5(String message) {
        String digest = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(message.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder(2 * hash.length);
            for (byte b : hash) {
                sb.append(String.format("%02x", b & 0xff));
            }
            digest = sb.toString();
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, null, ex);
        }
        return digest;
    }
}
