package com.ant_robot.mfc.api.request.service;


import com.ant_robot.mfc.api.pojo.UserProfile;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface UserService {
    @GET("api.php?mode=user&type=json")
    Observable<UserProfile> getUserRx(@Query("username") String username);

    @GET("api.php?mode=user&type=json")
    Call<UserProfile> getUser(@Query("username") String keywords);
}
