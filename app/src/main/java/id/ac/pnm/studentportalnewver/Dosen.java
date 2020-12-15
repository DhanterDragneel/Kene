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

public class Dosen extends AppCompatActivity {
    TextView namaDosenH,mkDosenH,namaDosenS,mkDosenS,namaDosenSO,mkDosenSO,namaDosenT,mkDosenT,namaDosenL,mkDosenL,namaDosenHin,mkDOsenHin;
    String url = "https://dekadragonslayer.000webhostapp.com/dosen/readdosen.php";
    JSONObject jsonObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen);

        namaDosenH = (TextView) findViewById(R.id.namaDosen1);
        mkDosenH = (TextView) findViewById(R.id.mkDosen1);
        namaDosenS = (TextView) findViewById(R.id.namaDosen2);
        mkDosenS = (TextView) findViewById(R.id.mkDosen2);
        namaDosenSO = (TextView) findViewById(R.id.namaDosen3);
        mkDosenSO = (TextView) findViewById(R.id.mkDosen3);
        namaDosenT = (TextView) findViewById(R.id.namaDosen4);
        mkDosenT = (TextView) findViewById(R.id.mkDosen4);
        namaDosenL = (TextView) findViewById(R.id.namaDosen5);
        mkDosenL = (TextView) findViewById(R.id.mkDosen5);
        namaDosenHin = (TextView) findViewById(R.id.namaDosen6);
        mkDOsenHin = (TextView) findViewById(R.id.mkDosen6);

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String s = response;
                try {
                    jsonObject = new JSONObject(s);
                    JSONArray a = jsonObject.getJSONArray("result");
                    for (int i = 0; i < a.length(); i++) {
                        JSONObject c = a.getJSONObject(i);
                        String namaDosen = c.getString("namaDosen");
                        String mkDosen = c.getString("mkDosen");
                        String namaDosen1 = c.getString("namaDosen1");
                        String mkDosen1 = c.getString("mkDosen1");
                        String namaDosen2 = c.getString("namaDosen2");
                        String mkDosen2 = c.getString("mkDosen2");
                        String namaDosen3 = c.getString("namaDosen3");
                        String mkDosen3 = c.getString("mkDosen3");
                        String namaDosen4 = c.getString("namaDosen4");
                        String mkDosen4 = c.getString("mkDosen4");
                        String namaDosen5 = c.getString("namaDosen5");
                        String mkDosen5 = c.getString("mkDosen5");

                        HashMap<String, String> resultx = new HashMap<>();
                        namaDosenH.setText(namaDosen);
                        mkDosenH.setText(mkDosen);
                        namaDosenS.setText(namaDosen1);
                        mkDosenS.setText(mkDosen1);
                        namaDosenSO.setText(namaDosen2);
                        mkDosenSO.setText(mkDosen2);
                        namaDosenT.setText(namaDosen3);
                        mkDosenT.setText(mkDosen3);
                        namaDosenL.setText(namaDosen4);
                        mkDosenL.setText(mkDosen4);
                        namaDosenHin.setText(namaDosen5);
                        mkDOsenHin.setText(mkDosen5);
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
                return params;

            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Dosen.this);
        requestQueue.add(request);
    }
}
