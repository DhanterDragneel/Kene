package id.ac.pnm.studentportalnewver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

public class DataOrtu extends AppCompatActivity {
    TextView NamaAyah, NamaIbu, PekerjaanAyah, PekerjaanIbu, NoHpAyah, NoHpIbu, NikAyah, NikIbu, npmDtortu;
    String npm;
    String url = "https://dekadragonslayer.000webhostapp.com/dtortu/readortubynpm.php";
    JSONObject jsonObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_ortu);
        npmDtortu = (TextView) findViewById(R.id.npmDtortu);
        final Bundle extras = getIntent().getExtras();
        npm = extras.getString("npmDtOrtu");
        npmDtortu.setText(npm);

        NamaAyah = (TextView) findViewById(R.id.namaAyah);
        PekerjaanAyah = (TextView) findViewById(R.id.pekerjaanAyah);
        NikAyah = (TextView) findViewById(R.id.nikAyah);
        NoHpAyah = (TextView) findViewById(R.id.noHpAyah);
        NamaIbu = (TextView) findViewById(R.id.namaIbu);
        PekerjaanIbu = (TextView) findViewById(R.id.pekerjaanIbu);
        NikIbu = (TextView) findViewById(R.id.nikIbu);
        NoHpIbu = (TextView) findViewById(R.id.noHpIbu);

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String s = response;
                try {
                    jsonObject = new JSONObject(s);
                    JSONArray a = jsonObject.getJSONArray("result");
                    for (int i = 0; i < a.length(); i++) {
                        JSONObject c = a.getJSONObject(i);
                        String nikAyah = c.getString("nikAyah");
                        String namaAyah = c.getString("namaAyah");
                        String pekerjaanAyah = c.getString("pekerjaanAyah");
                        String noHpAyah = c.getString("noHpAyah");
                        String namaIbu = c.getString("namaIbu");
                        String pekerjaanIbu = c.getString("pekerjaanIbu");
                        String noHpIbu = c.getString("noHpIbu");
                        String nikIbu = c.getString("nikIbu");

                        HashMap<String, String> resultx = new HashMap<>();
                        NikAyah.setText(nikAyah);
                        NamaAyah.setText(namaAyah);
                        PekerjaanAyah.setText(pekerjaanAyah);
                        NoHpAyah.setText(noHpAyah);
                        NoHpIbu.setText(noHpIbu);
                        NamaIbu.setText(namaIbu);
                        PekerjaanIbu.setText(pekerjaanIbu);
                        NikIbu.setText(nikIbu);
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

        RequestQueue requestQueue = Volley.newRequestQueue(DataOrtu.this);
        requestQueue.add(request);
    }
}
