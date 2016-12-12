package com.ant_robot.mfc.api.request.service;

import com.ant_robot.mfc.api.pojo.ItemList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 *
 * Returned items are limited to 50 but you can access full collections by modifying the page param.
 *
 */
public interface CollectionService {


    static final String STATUS_WISHED = "0";
    static final String STATUS_ORDERED = "1";
    static final String STATUS_OWNED = "2";

    static final String ROOT_FIGURES = "0";
    static final String ROOT_GOODS = "1";
    static final String ROOT_MEDIA = "2";

    @GET("?mode=collection&type=json")
    Observable<ItemList> getCollectionRx(@Query("username") String userName);

    @GET("?mode=collection&type=json")
    Call<ItemList> getCollection(@Query("username") String userName);

    @GET("?mode=collection&type=json")
    Observable<ItemList> getCollectionRx(@Query("username") String userName, @Query("page") int page);

    @GET("?mode=collection&type=json")
    Call<ItemList> getCollection(@Query("username") String userName, @Query("page") int page);

    @GET("?mode=collection&type=json")
    Observable<ItemList> getCollectionRx(@Query("username") String userName, @Query("page") int page, @Query("status") int status, @Query("root") int root);

    @GET("?mode=collection&type=json")
    Call<ItemList> getCollection(@Query("username") String userName, @Query("page") int page, @Query("status") int status, @Query("root") int root);

    @GET("?mode=collection&type=json&status=" + STATUS_WISHED)
    Observable<ItemList> getWishedRx(@Query("username") String userName);

    @GET("?mode=collection&type=json&status=" + STATUS_WISHED)
    Call<ItemList> getWished(@Query("username") String userName);

    @GET("?mode=collection&type=json&status=" + STATUS_WISHED)
    Observable<ItemList> getWishedRx(@Query("username") String userName, @Query("page") int page);

    @GET("?mode=collection&type=json&status=" + STATUS_WISHED)
    Call<ItemList> getWished(@Query("username") String userName, @Query("page") int page);

    @GET("?mode=collection&type=json&status=" + STATUS_ORDERED)
    Observable<ItemList> getOrderedRx(@Query("username") String userName);

    @GET("?mode=collection&type=json&status=" + STATUS_ORDERED)
    Call<ItemList> getOrdered(@Query("username") String userName);

    @GET("?mode=collection&type=json&statu=s" + STATUS_ORDERED)
    Observable<ItemList> getOrderedRx(@Query("username") String userName, @Query("page") int page);

    @GET("?mode=collection&type=json&status=" + STATUS_ORDERED)
    Call<ItemList> getOrdered(@Query("username") String userName, @Query("page") int page);

    @GET("?mode=collection&type=json&status=" + STATUS_OWNED)
    Observable<ItemList> getOwnedRx(@Query("username") String userName);

    @GET("?mode=collection&type=json&status=" + STATUS_OWNED)
    Call<ItemList> getOwned(@Query("username") String userName);

    @GET("?mode=collection&type=json&status=" + STATUS_OWNED)
    Observable<ItemList> getOwnedRx(@Query("username") String userName, @Query("page") int page);

    @GET("?mode=collection&type=json&status=" + STATUS_OWNED)
    Call<ItemList> getOwned(@Query("username") String userName, @Query("page") int page);

    @GET("?mode=collection&type=json&root=" + ROOT_FIGURES)
    Observable<ItemList> getFiguresRx(@Query("username") String userName, @Query("page") int page);

    @GET("?mode=collection&type=json&root=" + ROOT_FIGURES)
    Call<ItemList> getFigures(@Query("username") String userName, @Query("page") int page);

    @GET("?mode=collection&type=json&root=" + ROOT_GOODS)
    Observable<ItemList> getGoodsRx(@Query("username") String userName, @Query("page") byte page);

    @GET("?mode=collection&type=json&root=" + ROOT_GOODS)
    Call<ItemList> getGoods(@Query("username") String userName, @Query("page") byte page);

    @GET("?mode=collection&type=json&root=" + ROOT_MEDIA)
    Observable<ItemList> getMediaRx(@Query("username") String userName, @Query("page") byte page);

    @GET("?mode=collection&type=json&root=" + ROOT_MEDIA)
    Call<ItemList> getMedia(@Query("username") String userName, @Query("page") byte page);

    @GET("?mode=collection&type=json&root=" + ROOT_FIGURES)
    Observable<ItemList> getFiguresRx(@Query("username") String userName, @Query("page") byte page, @Query("status") String status);

    @GET("?mode=collection&type=json&root=" + ROOT_FIGURES)
    Call<ItemList> getFigures(@Query("username") String userName, @Query("page") byte page, @Query("status") String status);

    @GET("?mode=collection&type=json&root=" + ROOT_GOODS)
    Observable<ItemList> getGoodsRx(@Query("username") String userName, @Query("page") byte page, @Query("status") String status);

    @GET("?mode=collection&type=json&root=" + ROOT_GOODS)
    Call<ItemList> getGoods(@Query("username") String userName, @Query("page") byte page, @Query("status") String status);

    @GET("?mode=collection&type=json&root=" + ROOT_MEDIA)
    Observable<ItemList> getMediaRx(@Query("username") String userName, @Query("page") byte page, @Query("status") String status);

    @GET("?mode=collection&type=json&root=" + ROOT_MEDIA)
    Call<ItemList> getMedia(@Query("username") String userName, @Query("page") byte page, @Query("status") String status);

}
