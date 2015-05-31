package com.thehindu.digitaljobs.evaluationtask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.net.ConnectivityManager;
import java.util.ArrayList;
import java.util.List;
import android.net.NetworkInfo;
import com.thehindu.digitaljobs.evaluationtask.com.thehindu.digitaljobs.evaluationtask.pojo.ProjectDetails;
import com.thehindu.digitaljobs.evaluationtask.com.thehindu.digitaljobs.eveluationtask.customlist.CustomListViewAdapter;
import com.thehindu.digitaljobs.evaluationtask.com.thehindu.digitaljobs.eveluationtask.customlist.ListModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


public class ListActvity extends Activity {
    ListModel list;
    List<ListModel> listItems;

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        lv = (ListView) findViewById(R.id.list);
        String serverURL = "http://54.254.240.217:8080/app-task/projects/";

        // Use AsyncTask execute Method To Prevent ANR Problem

        if(isNetworkAvailable())
        new GetList().execute(serverURL);
        else
            Toast.makeText(ListActvity.this,
                    "Check your Internet connection",
                    Toast.LENGTH_LONG).show();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*Toast.makeText(ListActvity.this,
                        "You Clicked ----"+listItems.get(position).getProjectName(),
                        Toast.LENGTH_LONG).show();*/
                startActivity(new Intent(ListActvity.this, ProjectDetailsActivity.class)
                        .putExtra("ID", listItems.get(position).getId()));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.list_actvity, menu);
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
    private class GetList extends AsyncTask<String, Void, Void> {

        private String Content;
        private String Error = null;
        private ProgressDialog Dialog = new ProgressDialog(ListActvity.this);
        String data ="";
        protected void onPreExecute() {
            // NOTE: You can call UI Element here.

            //Start Progress Dialog (Message)

            Dialog.setMessage("Please wait..");
            Dialog.show();

            /*try{
                // Set Request parameter
                data;
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }*/

        }
        @Override
        protected Void doInBackground(String... urls) {

            BufferedReader reader=null;
            try{
                URL url=new URL(urls[0]);
                URLConnection conn = url.openConnection();
               // conn.setDoOutput(true);
                /*OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                wr.write( data );
                wr.flush();*/

                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = null;

                while((line = reader.readLine()) != null)
                {
                    // Append server response in string
                    sb.append(line + "");
                }

                // Append Server Response To Content String
                Content = sb.toString();

            }catch(Exception e){
                Error=e.getMessage();
            }finally {
                try{
                    reader.close();
                }catch (Exception e){

                }
            }
            return null;
        }
        protected void onPostExecute(Void unused) {
                  Dialog.dismiss();
            String OutputData = "";
            JSONObject jsonResponse;
            listItems=new ArrayList<ListModel>();

            try {

                /****** Creates a new JSONObject with name/value mappings from the JSON string. ********/
                //jsonResponse = new JSONObject(Content);
                /***** Returns the value mapped by name if it exists and is a JSONArray. ***/
                /*******  Returns null otherwise.  *******/
                JSONArray jsonMainNode = new JSONArray(Content);
                /*********** Process each JSON Node ************/
                int lengthJsonArr = jsonMainNode.length();
                for(int i=0; i < lengthJsonArr; i++) {
                    /****** Get Object for each JSON node.***********/
                    JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);

                    /******* Fetch node values **********/
                    String id = jsonChildNode.optString("id").toString();
                    String pjctName = jsonChildNode.optString("projectName").toString();
                    Double latitude = jsonChildNode.optDouble("lat");
                    Double longitude = jsonChildNode.optDouble("lon");
                    OutputData += "ID: " + id + " " + "Project Name : " + pjctName + " " + "Latitude : " + latitude + " " + "Longitude : " + longitude + "*********************************";
                    list = new ListModel(id, pjctName, latitude, longitude);
                    listItems.add(list);
                }
                /****************** End Parse Response JSON Data *************/

                //Show Parsed Output on screen (activity)
                Log.e("OUTPUT FOR LIST DETAILS",OutputData );
                } catch (JSONException e) {

                e.printStackTrace();
                }

                   CustomListViewAdapter adapter = new CustomListViewAdapter(ListActvity.this, R.layout.list_details,
                           listItems);

                    lv.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
            Toast.makeText(getApplicationContext(), "List Updated",
                    Toast.LENGTH_SHORT).show();


        }
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
