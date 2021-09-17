package com.hadimusthafa.meridian;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ProfileActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    MyAdapter myAdapter;
    TextView username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        recyclerView = findViewById(R.id.recyclerView);

        username = findViewById(R.id.name);
        username.setText("Hello, " + Preferences.getRegisteredUser(getBaseContext()));

        getWhatWeNeedMethod();
    }

    private void getWhatWeNeedMethod() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL).addConverterFactory(ScalarsConverterFactory.create()).build();
        Api api = retrofit.create(Api.class);
        Call<String> call = api.getWhatWeNeed("1", "12");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                Log.e("call-->", "-->" + call.request());
                Log.e("response-->", "-->" + response.body());
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        try {

                            JSONObject jsonObject1 = new JSONObject(response.body());
                            Log.e("response-->", String.valueOf(jsonObject1));
                            JSONArray jsonArray = jsonObject1.getJSONArray("data");

                            ArrayList<MyModel> modelRecyclerArrayList = new ArrayList<>();
                            try {
                                for (int i = 0; i < jsonArray.length(); i++) {

                                    MyModel modelRecycler = new MyModel();
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    modelRecycler.setAvatar(jsonObject.getString("avatar"));
                                    modelRecycler.setEmail(jsonObject.getString("email"));
                                    modelRecycler.setFirst_name(jsonObject.getString("first_name") + " " + jsonObject.getString("last_name"));
                                    modelRecyclerArrayList.add(modelRecycler);
                                }
                                if (modelRecyclerArrayList.size() > 0) {
                                    myAdapter = new MyAdapter(getApplicationContext(), modelRecyclerArrayList);
                                    recyclerView.setAdapter(myAdapter);
                                    recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
                                } else {
                                    recyclerView.setVisibility(View.GONE);
                                }

                            } catch (JSONException e) {
                                Log.e("e-->", "populateRecycler-->" + e.toString() + "");
                            }
                        } catch (Exception e) {
                            Log.e("ee-->", e.toString());
                        }
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                Log.e("t-->", "-->" + t.toString());
            }
        });
    }
}