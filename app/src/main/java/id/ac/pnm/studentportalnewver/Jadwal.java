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

public class Jadwal extends AppCompatActivity {
    TextView H1,H2,H3,H4,H5,MK1,MK1P,MK2,MK2P,MK3,MK4,MK5,MK5P,MK6,MK6P,Dsn1,Dsn11,Dsn2,Dsn22,Dsn3,Dsn4,Dsn5,Dsn55,Dsn6,Dsn66;
    String url = "https://dekadragonslayer.000webhostapp.com/readjadwal.php";
    JSONObject jsonObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal);
        H1 = (TextView) findViewById(R.id.H1);
        H2 = (TextView) findViewById(R.id.H2);
        H3 = (TextView) findViewById(R.id.H3);
        H4 = (TextView) findViewById(R.id.H4);
        H5 = (TextView) findViewById(R.id.H5);
        MK1 = (TextView) findViewById(R.id.MK1);
        MK1P = (TextView) findViewById(R.id.MK1P);
        MK2 = (TextView) findViewById(R.id.MK2);
        MK2P = (TextView) findViewById(R.id.MK2P);
        MK3 = (TextView) findViewById(R.id.MK3);
        MK4 = (TextView) findViewById(R.id.MK4);
        MK5 = (TextView) findViewById(R.id.MK5);
        MK5P = (TextView) findViewById(R.id.MK5P);
        MK6 = (TextView) findViewById(R.id.MK6);
        MK6P = (TextView) findViewById(R.id.MK6P);
        Dsn1 = (TextView) findViewById(R.id.Dsn1);
        Dsn11 = (TextView) findViewById(R.id.Dsn11);
        Dsn2 = (TextView) findViewById(R.id.Dsn2);
        Dsn22 = (TextView) findViewById(R.id.Dsn22);
        Dsn3 = (TextView) findViewById(R.id.Dsn3);
        Dsn4 = (TextView) findViewById(R.id.Dsn4);
        Dsn5 = (TextView) findViewById(R.id.Dsn5);
        Dsn55 = (TextView) findViewById(R.id.Dsn55);
        Dsn6 = (TextView) findViewById(R.id.Dsn6);
        Dsn66 = (TextView) findViewById(R.id.Dsn66);

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String s = response;
                try {
                    jsonObject = new JSONObject(s);
                    JSONArray a = jsonObject.getJSONArray("result");
                    for (int i = 0; i < a.length(); i++) {
                        JSONObject c = a.getJSONObject(i);
                        String h1 = c.getString("h1");
                        String h2 = c.getString("h2");
                        String h3 = c.getString("h3");
                        String h4 = c.getString("h4");
                        String h5 = c.getString("h5");
                        String mk1 = c.getString("mk1");
                        String mk1p = c.getString("mk1p");
                        String mk2 = c.getString("mk2p");
                        String mk2p = c.getString("mk2p");
                        String mk3 = c.getString("mk3");
                        String mk4 = c.getString("mk4");
                        String mk5 = c.getString("mk5");
                        String mk5p = c.getString("mk5p");
                        String mk6 = c.getString("mk6");
                        String mk6p = c.getString("mk6p");
                        String dosen1 = c.getString("dosen1");
                        String dosen2 = c.getString("dosen2");
                        String dosen3 = c.getString("dosen3");
                        String dosen4 = c.getString("dosen4");
                        String dosen5 = c.getString("dosen5");
                        String dosen6 = c.getString("dosen6");

                        HashMap<String, String> resultx = new HashMap<>();
                        H1.setText(h1);
                        H2.setText(h2);
                        H3.setText(h3);
                        H4.setText(h4);
                        H5.setText(h5);
                        MK1.setText(mk1);
                        MK1P.setText(mk1p);
                        MK2.setText(mk2);
                        MK2P.setText(mk2p);
                        MK3.setText(mk3);
                        MK4.setText(mk4);
                        MK5.setText(mk5);
                        MK5P.setText(mk5p);
                        MK6.setText(mk6);
                        MK6P.setText(mk6p);
                        Dsn1.setText(dosen1);
                        Dsn11.setText(dosen1);
                        Dsn2.setText(dosen2);
                        Dsn22.setText(dosen2);
                        Dsn3.setText(dosen3);
                        Dsn4.setText(dosen4);
                        Dsn5.setText(dosen5);
                        Dsn55.setText(dosen5);
                        Dsn6.setText(dosen6);
                        Dsn66.setText(dosen6);
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

        RequestQueue requestQueue = Volley.newRequestQueue(Jadwal.this);
        requestQueue.add(request);
    }
}
