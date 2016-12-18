package com.ant_robot.testmfc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.ant_robot.mfc.api.pojo.ItemList;
import com.ant_robot.mfc.api.pojo.PictureGallery;
import com.ant_robot.mfc.api.request.MFCRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MFC SDK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MFCRequest.initialize(this);

        //testConnect();
        //testOrderedCollection();
        //testOwnedCollection();
        testWishedCollection();
        //testGallery();
    }

    private void testConnect() {

        MFCRequest.getInstance().connect("spawn150", "pul78lce", new MFCRequest.MFCCallback<Boolean>() {
            @Override
            public void success(Boolean aBoolean) {
                Log.d(TAG, "Loging completed with " + (aBoolean ? "Success" : "Failure"));
            }

            @Override
            public void error(Throwable throwable) {
                Log.e(TAG, throwable.getMessage());
            }
        });

    }

    private void testOwnedCollection() {

        Call<ItemList> call = MFCRequest.getInstance().getCollectionService().getOwned("spawn150");
        call.enqueue(new Callback<ItemList>() {
            @Override
            public void onResponse(Call<ItemList> call, Response<ItemList> response) {
                Log.d(TAG, "Items size: "+response.body().getCollection().getOwned().getNumItems());
            }

            @Override
            public void onFailure(Call<ItemList> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    private void testOrderedCollection() {

        Call<ItemList> call = MFCRequest.getInstance().getCollectionService().getOrdered("spawn150");
        call.enqueue(new Callback<ItemList>() {
            @Override
            public void onResponse(Call<ItemList> call, Response<ItemList> response) {
                Log.d(TAG, "Items size: "+response.body().getCollection().getOrdered().getNumItems());
            }

            @Override
            public void onFailure(Call<ItemList> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    private void testWishedCollection() {

        Call<ItemList> call = MFCRequest.getInstance().getCollectionService().getWished("spawn150");
        call.enqueue(new Callback<ItemList>() {
            @Override
            public void onResponse(Call<ItemList> call, Response<ItemList> response) {
                Log.d(TAG, "Items size: "+response.body().getCollection().getWished().getNumItems());
            }

            @Override
            public void onFailure(Call<ItemList> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    private void testGallery() {

        Call<PictureGallery> call = MFCRequest.getInstance().getGalleryService().getGalleryForUser("spawn150", 1);
        call.enqueue(new Callback<PictureGallery>() {
            @Override
            public void onResponse(Call<PictureGallery> call, Response<PictureGallery> response) {
                Log.d(TAG, response.body().getName());
            }

            @Override
            public void onFailure(Call<PictureGallery> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
