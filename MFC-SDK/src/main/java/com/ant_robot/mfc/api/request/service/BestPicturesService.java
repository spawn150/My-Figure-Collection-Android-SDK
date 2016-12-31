package com.ant_robot.mfc.api.request.service;


import com.ant_robot.mfc.api.pojo.BestPictureGallery;
import com.ant_robot.mfc.api.pojo.LatestPictureGallery;
import com.ant_robot.mfc.api.pojo.PotdPictureGallery;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Returned items are limited to 20 but you can access full galleries by modifying the page param.
 */
public interface BestPicturesService {

    @GET("api_v2.php?type=json&access=read&object=pictures&request=potd")
    Observable<PotdPictureGallery> getPicturesOfTheDayRx(@Query("page") int page);

    @GET("api_v2.php?type=json&access=read&object=pictures&request=potd")
    Call<PotdPictureGallery> getPicturesOfTheDay(@Query("page") int page);

    //@GET("api_v2.php?type=json&access=read&object=pictures")
    @GET("api.php?mode=gallery&type=json")
    Observable<LatestPictureGallery> getLatestPicturesRx(@Query("page") int page);

    //@GET("api_v2.php?type=json&access=read&object=pictures")
    @GET("api.php?mode=gallery&type=json")
    Call<LatestPictureGallery> getLatestPictures(@Query("page") int page);
}
