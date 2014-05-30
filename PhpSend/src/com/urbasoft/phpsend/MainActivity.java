package com.urbasoft.phpsend;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;




import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	EditText messagetx;
	Button btnenvoyer;
	String result=null;
	InputStream is;
	StringBuilder sb=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        messagetx=(EditText) findViewById(R.id.editviewid);
        btnenvoyer=(Button) findViewById(R.id.btnviewid);
        btnenvoyer.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				envoyerMessage(messagetx);
			}
		});

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
  public void envoyerMessage(View v){
	  
	HttpClient client=new DefaultHttpClient();
	HttpPost post=new HttpPost("http://10.0.3.2/sendmessage.php");
	String msg=messagetx.getText().toString();
	if (msg.length()>0) {
		try {
			List<NameValuePair> donnes=new ArrayList<NameValuePair>(1);
			donnes.add(new BasicNameValuePair("message", msg));
			post.setEntity(new UrlEncodedFormEntity(donnes));
			client.execute(post);
			messagetx.setText("momo");
			Toast.makeText(this, "Sd9At a m 3lam", Toast.LENGTH_SHORT).show();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			// TODO: handle exception
		}catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	else Toast.makeText(this, "Ktab am3alam", Toast.LENGTH_SHORT).show();
}


}
