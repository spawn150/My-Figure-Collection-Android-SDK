package com.ant_robot.mfc.api.request.service;

import com.ant_robot.mfc.api.pojo.SearchResult;

import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Search service results are limited to 20 items
 */
public interface SearchService {
    @GET("/?mode=search&type=json")
    Observable<SearchResult> search(@Query("keywords") String keywords);

    @GET("/?mode=search&type=json")
    void search(@Query("keywords") String keywords, Callback<SearchResult> callback);

    @GET("/?mode=search&type=json")
    SearchResult searchSync(@Query("keywords") String keywords);
}
