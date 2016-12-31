package com.ant_robot.mfc.api.request.service;


import com.ant_robot.mfc.api.pojo.PictureGallery;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Returned items are limited to 20 but you can access full galleries by modifying the page param.
 */
public interface GalleryService {
    @GET("api.php?mode=gallery&type=json")
    Observable<PictureGallery> getGalleryForUserRx(@Query("username") String username, @Query("page") int page);

    @GET("api.php?mode=gallery&type=json")
    Call<PictureGallery> getGalleryForUser(@Query("username") String keywords, @Query("page") int page);

    @GET("api.php?mode=gallery&type=json")
    Observable<PictureGallery> getGalleryForItemRx(@Query("item") String itemId, @Query("page") int page);

    @GET("api.php?mode=gallery&type=json")
    Call<PictureGallery> getGalleryForItem(@Query("item") String itemId, @Query("page") int page);

    @GET("api.php?mode=gallery&type=json")
    Call<PictureGallery> getLatestPicturesRx(@Query("page") int page);

    @GET("api.php?mode=gallery&type=json")
    Call<PictureGallery> getLatestPictures(@Query("page") int page);


}
