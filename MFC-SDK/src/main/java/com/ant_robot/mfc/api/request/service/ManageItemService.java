package com.ant_robot.mfc.api.request.service;

import com.ant_robot.mfc.api.pojo.AlterItem;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * unofficial service to manage collection
 * e.g: commit=collect&status=1&num=1&value=52.0&location=AmiAmi&method=0&track=&bdate=0000-00-00&sdate=0000-00-00&sub_status=1&previous_status=1&reload=0
 */
public interface ManageItemService {

    /**
     * @param itemId          item id
     * @param commit          set value "collect" for the collection
     * @param status          new status in collection (0)
     * @param wishability     how much you wish item on 5
     * @param previous_status the current status in collection
     * @param reload          unknown effect, prefer a 0 value
     */
    @FormUrlEncoded
    @Headers({
            "User-Agent: MFC Android app",
            "Accept-Language: fr,fr-FR;q=0.8,en;q=0.6,en-US;q=0.4,es;q=0.2,ja;q=0.2",
            "Connection: keep-alive"
    }
    )
    @POST
    Call<AlterItem> wishItem(@Query("iid") String itemId,
                             @Field("commit") String commit,
                             @Field("status") int status,
                             @Field("wishability") int wishability,
                             @Field("previous_status") int previous_status,
                             @Field("reload") int reload);


    /**
     * @param itemId          item id
     * @param commit          set value "collect" for the collection
     * @param status          new status in collection (0)
     * @param wishability     how much you wish item on 5
     * @param previous_status the current status in collection
     * @param reload          unknown effect, prefer a 0 value
     */
    @FormUrlEncoded
    @Headers({
            "User-Agent: MFC Android app",
            "Accept-Language: fr,fr-FR;q=0.8,en;q=0.6,en-US;q=0.4,es;q=0.2,ja;q=0.2",
            "Connection: keep-alive"
    }
    )
    @POST
    Observable<AlterItem> wishItemRx(@Query("iid") String itemId,
                                     @Field("commit") String commit,
                                     @Field("status") int status,
                                     @Field("wishability") int wishability,
                                     @Field("previous_status") int previous_status,
                                     @Field("reload") int reload);

    /**
     * @param itemId          item id
     * @param commit          set value "collect" for the collection
     * @param status          new status in collection (1)
     * @param num             number in collection for this status
     * @param value           price you bought item
     * @param location        shop where you bought item
     * @param method          shipping method (0:N/A, 1:EMS, 2:SAL, 3:AIRMAIL, 4:SURFACE, 5:FEDEX, 6:DHL, 7:COLISSIMO, 8:UPS, 9:DOMESTIC)
     * @param track           tracking number
     * @param bdate           order date
     * @param sdate           shipping date
     * @param sub_status      0:N/A, 1: second hand, 2:Sealed, 3:stored
     * @param previous_status the current status in collection
     * @param reload          unknown effect, prefer a 0 value
     */
    @FormUrlEncoded
    @Headers({
            "User-Agent: MFC Android app",
            "Accept-Language: fr,fr-FR;q=0.8,en;q=0.6,en-US;q=0.4,es;q=0.2,ja;q=0.2",
            "Connection: keep-alive"
    }
    )
    @POST
    Call<AlterItem> orderItem(@Query("iid") String itemId,
                              @Field("commit") String commit,
                              @Field("status") int status,
                              @Field("num") int num,
                              @Field("value") double value,
                              @Field("location") String location,
                              @Field("method") int method,
                              @Field("track") String track,
                              @Field("bdate") ItemDate bdate,
                              @Field("sdate") ItemDate sdate,
                              @Field("sub_status") int sub_status,
                              @Field("previous_status") int previous_status,
                              @Field("reload") int reload);


    /**
     * @param itemId          item id
     * @param commit          set value "collect" for the collection
     * @param status          new status in collection (1)
     * @param num             number in collection for this status
     * @param value           price you bought item
     * @param location        shop where you bought item
     * @param method          shipping method (0:N/A, 1:EMS, 2:SAL, 3:AIRMAIL, 4:SURFACE, 5:FEDEX, 6:DHL, 7:COLISSIMO, 8:UPS, 9:DOMESTIC)
     * @param track           tracking number
     * @param bdate           order date
     * @param sdate           shipping date
     * @param sub_status      0:N/A, 1: second hand, 2:Sealed, 3:stored
     * @param previous_status the current status in collection
     * @param reload          unknown effect, prefer a 0 value
     */
    @FormUrlEncoded
    @Headers({
            "User-Agent: MFC Android app",
            "Accept-Language: fr,fr-FR;q=0.8,en;q=0.6,en-US;q=0.4,es;q=0.2,ja;q=0.2",
            "Connection: keep-alive"
    }
    )
    @POST
    Observable<AlterItem> orderItemRx(@Query("iid") String itemId,
                                      @Field("commit") String commit,
                                      @Field("status") int status,
                                      @Field("num") int num,
                                      @Field("value") double value,
                                      @Field("location") String location,
                                      @Field("method") int method,
                                      @Field("track") String track,
                                      @Field("bdate") ItemDate bdate,
                                      @Field("sdate") ItemDate sdate,
                                      @Field("sub_status") int sub_status,
                                      @Field("previous_status") int previous_status,
                                      @Field("reload") int reload);

    /**
     * @param itemId          item id
     * @param commit          set value "collect" for the collection
     * @param status          new status in collection (2)
     * @param num             number in collection for this status
     * @param value           price you bought item
     * @param location        shop where you bought item
     * @param method          shipping method (0:N/A, 1:EMS, 2:SAL, 3:AIRMAIL, 4:SURFACE, 5:FEDEX, 6:DHL, 7:COLISSIMO, 8:UPS, 9:DOMESTIC)
     * @param track           tracking number
     * @param bdate           order date
     * @param sdate           shipping date
     * @param sub_status      0:N/A, 1: second hand, 2:Sealed, 3:stored
     * @param previous_status the current status in collection
     * @param reload          unknown effect, prefer a 0 value
     */
    @FormUrlEncoded
    @Headers({
            "User-Agent: MFC Android app",
            "Accept-Language: fr,fr-FR;q=0.8,en;q=0.6,en-US;q=0.4,es;q=0.2,ja;q=0.2",
            "Connection: keep-alive"
    }
    )
    @POST
    Call<AlterItem> ownItem(@Query("iid") String itemId,
                            @Field("commit") String commit,
                            @Field("status") int status,
                            @Field("num") int num,
                            @Field("score") int score,
                            @Field("odate") ItemDate odate,
                            @Field("value") double value,
                            @Field("location") String location,
                            @Field("method") int method,
                            @Field("track") String track,
                            @Field("bdate") ItemDate bdate,
                            @Field("sdate") ItemDate sdate,
                            @Field("sub_status") int sub_status,
                            @Field("previous_status") int previous_status,
                            @Field("reload") int reload);


    /**
     * @param itemId          item id
     * @param commit          set value "collect" for the collection
     * @param status          new status in collection (2)
     * @param num             number in collection for this status
     * @param value           price you bought item
     * @param location        shop where you bought item
     * @param method          shipping method (0:N/A, 1:EMS, 2:SAL, 3:AIRMAIL, 4:SURFACE, 5:FEDEX, 6:DHL, 7:COLISSIMO, 8:UPS, 9:DOMESTIC)
     * @param track           tracking number
     * @param bdate           order date
     * @param sdate           shipping date
     * @param sub_status      0:N/A, 1: second hand, 2:Sealed, 3:stored
     * @param previous_status the current status in collection
     * @param reload          unknown effect, prefer a 0 value
     */
    @FormUrlEncoded
    @Headers({
            "User-Agent: MFC Android app",
            "Accept-Language: fr,fr-FR;q=0.8,en;q=0.6,en-US;q=0.4,es;q=0.2,ja;q=0.2",
            "Connection: keep-alive"
    }
    )
    @POST
    Observable<AlterItem> ownItemRx(@Query("iid") String itemId,
                                    @Field("commit") String commit,
                                    @Field("status") int status,
                                    @Field("num") int num,
                                    @Field("score") int score,
                                    @Field("odate") ItemDate odate,
                                    @Field("value") double value,
                                    @Field("location") String location,
                                    @Field("method") int method,
                                    @Field("track") String track,
                                    @Field("bdate") ItemDate bdate,
                                    @Field("sdate") ItemDate sdate,
                                    @Field("sub_status") int sub_status,
                                    @Field("previous_status") int previous_status,
                                    @Field("reload") int reload);


    /**
     * @param itemId          item id
     * @param commit          set value "collect" for the collection
     * @param status          new status in collection (9)
     * @param previous_status the current status in collection
     * @param reload          unknown effect, prefer a 0 value
     */
    @FormUrlEncoded
    @Headers({
            "User-Agent: MFC Android app",
            "Accept-Language: fr,fr-FR;q=0.8,en;q=0.6,en-US;q=0.4,es;q=0.2,ja;q=0.2",
            "Connection: keep-alive"
    }
    )
    @POST
    Call<AlterItem> deleteItem(@Query("iid") String itemId,
                               @Field("commit") String commit,
                               @Field("status") int status,
                               @Field("previous_status") int previous_status,
                               @Field("reload") int reload);


    /**
     * @param itemId          item id
     * @param commit          set value "collect" for the collection
     * @param status          new status in collection (9)
     * @param previous_status the current status in collection
     * @param reload          unknown effect, prefer a 0 value
     */
    @FormUrlEncoded
    @Headers({
            "User-Agent: MFC Android app",
            "Accept-Language: fr,fr-FR;q=0.8,en;q=0.6,en-US;q=0.4,es;q=0.2,ja;q=0.2",
            "Connection: keep-alive"
    }
    )
    @POST
    Observable<AlterItem> deleteItemRx(@Query("iid") String itemId,
                                       @Field("commit") String commit,
                                       @Field("status") int status,
                                       @Field("previous_status") int previous_status,
                                       @Field("reload") int reload);

}
