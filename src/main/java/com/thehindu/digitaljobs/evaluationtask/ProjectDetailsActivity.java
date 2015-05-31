package com.thehindu.digitaljobs.evaluationtask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.thehindu.digitaljobs.evaluationtask.com.thehindu.digitaljobs.evaluationtask.pojo.ProjectDetails;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;



public class ProjectDetailsActivity extends Activity {
    String ID;
    ProjectDetails response;
    TextView addrline1,addrline2,brouchure,city,sum,caption,directionfacing,landmark,listingid,listingname,locality,maxarea,maxprice,maxpricepersqft,minarea,minprice,
            minpricepersqft,blockno,avl_unitno,unitno,otherinfo,packageID,posession_date,project_Type,status,url,approvedno,approvedby,bank_approvals,bank_creditstatus,
    reference, builder_desc,builderID,builderName,builderurl,electricity_conn,lastmile_landmark,lastmile_lat,lastmile_long,lat,lon,other_amenities,otherbanks,specification,watertypes,amenities;
    ImageView arrow,logo;
    Bitmap builderlogo;
    LinearLayout summarydetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_details);
        Intent i = getIntent();
        ID= i.getStringExtra("ID");
        String serverURL = "http://54.254.240.217:8080/app-task/projects/"+ID;
        addrline1=(TextView)findViewById(R.id.addrline1);
        addrline2=(TextView)findViewById(R.id.addrline2);
        brouchure=(TextView)findViewById(R.id.brouchure);
        city=(TextView)findViewById(R.id.city);
        sum=(TextView)findViewById(R.id.sum);
        amenities=(TextView)findViewById(R.id.amenities);
        caption=(TextView)findViewById(R.id.caption);
        directionfacing=(TextView)findViewById(R.id.directionfacing);
        landmark=(TextView)findViewById(R.id.landmark);
        listingid=(TextView)findViewById(R.id.listingid);
        listingname=(TextView)findViewById(R.id.listingname);
        locality=(TextView)findViewById(R.id.locality);
        maxarea=(TextView)findViewById(R.id.maxarea);
        maxprice=(TextView)findViewById(R.id.maxprice);
        maxpricepersqft=(TextView)findViewById(R.id.maxpricepersqft);
        reference=(TextView)findViewById(R.id.reference);
        minarea=(TextView)findViewById(R.id.minarea);
        minprice=(TextView)findViewById(R.id.minprice);
        minpricepersqft=(TextView)findViewById(R.id.minpricepersqft);
        blockno=(TextView)findViewById(R.id.blockno);
        avl_unitno=(TextView)findViewById(R.id.avl_unitno);
        unitno=(TextView)findViewById(R.id.unitno);
        otherinfo=(TextView)findViewById(R.id.otherinfo);
        packageID=(TextView)findViewById(R.id.packageID);
        posession_date=(TextView)findViewById(R.id.posession_date);
        project_Type=(TextView)findViewById(R.id.project_Type);
        status=(TextView)findViewById(R.id.status);
        url=(TextView)findViewById(R.id.url);
        approvedno=(TextView)findViewById(R.id.approvedno);
        approvedby=(TextView)findViewById(R.id.approvedby);
        bank_approvals=(TextView)findViewById(R.id.bank_approvals);
        bank_creditstatus=(TextView)findViewById(R.id.bank_creditstatus);
        builder_desc=(TextView)findViewById(R.id.builder_desc);
        builderID=(TextView)findViewById(R.id.builderID);
        builderName=(TextView)findViewById(R.id.builderName);
        builderurl=(TextView)findViewById(R.id.builderurl);
        electricity_conn=(TextView)findViewById(R.id.electricity_conn);
        lastmile_landmark=(TextView)findViewById(R.id.lastmile_landmark);
        lastmile_lat=(TextView)findViewById(R.id.lastmile_lat);
        lastmile_long=(TextView)findViewById(R.id.lastmile_long);
        lat=(TextView)findViewById(R.id.lat);
        lon=(TextView)findViewById(R.id.lon);
        other_amenities=(TextView)findViewById(R.id.other_amenities);
        otherbanks=(TextView)findViewById(R.id.otherbanks);
        specification=(TextView)findViewById(R.id.specification);
        watertypes=(TextView)findViewById(R.id.watertypes);


        logo=(ImageView)findViewById(R.id.logo);
        summarydetails=(LinearLayout)findViewById(R.id.summarydetails);



        if(isNetworkAvailable())
        new GetProjectDetails().execute(serverURL);
        else
        Toast.makeText(ProjectDetailsActivity.this,
                "Check your Internet connection",
                Toast.LENGTH_LONG).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.project_details, menu);
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

    private class GetProjectDetails extends AsyncTask<String, Void, Void> {

        private String Content;
        private String Error = null;
        private ProgressDialog Dialog = new ProgressDialog(ProjectDetailsActivity.this);
        String data ="";
        protected void onPreExecute() {
            // NOTE: You can call UI Element here.

            //Start Progress Dialog (Message)

            Dialog.setMessage("Please wait..");
            Dialog.show();

}
        @Override
        protected Void doInBackground(String... urls) {

            BufferedReader reader=null;
            try{
                URL url=new URL(urls[0]);
                URLConnection conn = url.openConnection();
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = null;

                Gson gson = new Gson();
                 response = gson.fromJson(reader, ProjectDetails
                        .class);
                builderlogo=getImageBitmap(response.getBuilderLogo());

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


            addrline1.setText(response.getAddressLine1());
            addrline2.setText(response.getAddressLine2());
            brouchure.setText(response.getBrochure());
            city.setText(response.getCity());
            if(response.getSummary()!=null&&!response.getSummary().isEmpty()) {
                caption.setText(response.getDocuments().get(0).getText());
                directionfacing.setText(response.getDocuments().get(0).getDirectionFacing());
                reference.setText(response.getDocuments().get(0).getReference());

            }
            landmark.setText(response.getLandmark());
            listingid.setText(response.getListingId());
            listingname.setText(response.getListingName());
            locality.setText(response.getLocality()+"");
            maxarea.setText(response.getMaxArea()+"");
            maxprice.setText(response.getMaxPrice()+"");
            maxpricepersqft.setText(response.getMaxPricePerSqft()+"");
            minarea.setText(response.getMinArea()+"");
            minprice.setText(response.getMinPrice()+"");
            minpricepersqft.setText(response.getMinPricePerSqft()+"");
            blockno.setText(response.getNoOfAvailableUnits());
            avl_unitno.setText(response.getNoOfAvailableUnits());
            unitno.setText(response.getNoOfUnits());
            otherinfo.setText(response.getOtherInfo());
            packageID.setText(response.getPackageId());
            posession_date.setText(response.getPosessionDate()+"");
            project_Type.setText(response.getProjectType());
            status.setText(response.getStatus());
            url.setText(response.getUrl());
            approvedno.setText(response.getApprovalNumber());
            approvedby.setText(response.getApprovedBy());
            StringBuilder bankApprovals=new StringBuilder();
            if(response.getBankApprovals()!=null){
                for(int i=0;i<response.getBankApprovals().size();i++){
                    if(i==0){
                        bankApprovals.append(response.getBankApprovals().get(i)+"");
                    }else{
                        bankApprovals.append(","+response.getBankApprovals().get(i));
                    }
                }

            }
            StringBuilder sb_amenities=new StringBuilder();
            if(response.getAmenities()!=null){
                for(int i=0;i<response.getAmenities().size();i++){
                    if(i==0){
                        sb_amenities.append(response.getAmenities().get(i)+"");
                    }else{
                        sb_amenities.append(","+response.getAmenities().get(i));
                    }
                }

            }
            amenities.setText(sb_amenities);
            bank_approvals.setText(bankApprovals+"");
            bank_creditstatus.setText(response.getBuilderCredaiStatus());
            builder_desc.setText(Html.fromHtml(response.getBuilderDescription()));
            builderID.setText(response.getBuilderId());
            builderName.setText(response.getBuilderName());
            builderurl.setText(response.getBuilderUrl());
            electricity_conn.setText(response.getElectricityConnection());
            lastmile_landmark.setText(response.getLastMileLandmark());
            lastmile_lat.setText(response.getLastMileLat());
            lastmile_long.setText(response.getLastMileLon());
            lat.setText(response.getLat()+"");
            lon.setText(response.getLon()+"");
            other_amenities.setText(response.getOtherAmenities());
            otherbanks.setText(response.getOtherBanks());
            specification.setText(Html.fromHtml(response.getSpecification()));
            watertypes.setText(response.getWaterTypes());
            logo.setImageBitmap(builderlogo);




            Toast.makeText(getApplicationContext(),response.getAddressLine1()+"******"+response.getAddressLine2() ,
                    Toast.LENGTH_SHORT).show();


        }
    }
    private Bitmap getImageBitmap(String url) {
        Bitmap bm = null;
        try {
            if(isNetworkAvailable()) {
                URL aURL = new URL(url);
                URLConnection conn = aURL.openConnection();
                conn.connect();
                InputStream is = conn.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);
                bm = BitmapFactory.decodeStream(bis);
                bis.close();
                is.close();
            }else{
                Toast.makeText(ProjectDetailsActivity.this,
                        "Check your Internet connection",
                        Toast.LENGTH_LONG).show();
            }
        } catch (IOException e) {
            Log.e("Image", "Error getting bitmap", e);
        }
        return bm;
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
