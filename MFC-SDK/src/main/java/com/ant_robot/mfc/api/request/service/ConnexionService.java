package com.ant_robot.mfc.api.request.service;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by climbatize on 20/03/15.
 */
public interface ConnexionService {
    @FormUrlEncoded
    @Headers({
            "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
            "User-Agent: MFC Android app",
            "Cache-Control: max-age=0",
            "Accept-Language: fr,fr-FR;q=0.8,en;q=0.6,en-US;q=0.4,es;q=0.2,ja;q=0.2",
            "Connection: keep-alive"
    }
    )
    @POST("signs.php?mode=in&ln=en")
    Call<ResponseBody> connectUser(@Field("username") String name, @Field("password") String pass, @Field("set_cookie") int cookie, @Field("commit") String commit, @Field("location") String location);

    @FormUrlEncoded
    @Headers({
            "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
            "User-Agent: MFC Android app",
            "Cache-Control: max-age=0",
            "Accept-Language: fr,fr-FR;q=0.8,en;q=0.6,en-US;q=0.4,es;q=0.2,ja;q=0.2",
            "Connection: keep-alive"
    }
    )
    @POST("signs.php?mode=in&ln=en")
    Observable<ResponseBody> connectUserRx(@Field("username") String name, @Field("password") String pass, @Field("set_cookie") int cookie, @Field("commit") String commit, @Field("location") String location);
}
