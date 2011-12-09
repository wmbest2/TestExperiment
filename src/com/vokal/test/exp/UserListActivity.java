package com.vokal.test.exp;

import android.app.Activity;
import android.os.*;
import android.widget.*;

import java.util.HashMap;

import com.vokal.test.exp.network.*;

public class UserListActivity extends Activity
{

    private TextView mResponse;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mResponse = (TextView) findViewById(R.id.response);

        new FetchUsersAsync().execute();
    }

    public class FetchUsersAsync extends AsyncTask<Void, Void, String> {

        public String doInBackground(Void... aParams) {
            return NetworkClient.getInstance().get("http://192.168.1.119:3000/api/users/", new HashMap<String, String>());
        }

        public void onPostExecute(String aResult) {
            if (aResult != null) {
                mResponse.setText(aResult);
            }
        }
    };
}
