package com.ant_robot.mfc.api.request;

import android.content.Context;

import com.ant_robot.mfc.api.pojo.AlterItem;
import com.ant_robot.mfc.api.request.cookie.PersistentCookieStore;
import com.ant_robot.mfc.api.request.json.DynamicJsonConverter;
import com.ant_robot.mfc.api.request.json.GalleryJsonConverter;
import com.ant_robot.mfc.api.request.service.CollectionService;
import com.ant_robot.mfc.api.request.service.ConnexionService;
import com.ant_robot.mfc.api.request.service.GalleryService;
import com.ant_robot.mfc.api.request.service.ItemDate;
import com.ant_robot.mfc.api.request.service.ManageItemService;
import com.ant_robot.mfc.api.request.service.PostEndPoint;
import com.ant_robot.mfc.api.request.service.SearchService;
import com.ant_robot.mfc.api.request.service.UserService;

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpCookie;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import retrofit2.Call;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public enum MFCRequest {
    INSTANCE;
    public static final String ROOT_URL = "http://myfigurecollection.net/api.php";
    public static final String LOGIN = "https://secure.myfigurecollection.net/signs.php";
    public static final String ITEM = "http://myfigurecollection.net/items.php";
    private final Retrofit restAdapter;
    private final Retrofit connectAdapter;
    private final Retrofit galleryAdapter;

    private final OkHttpClient client;
    private final DynamicJsonConverter standartConverter;
    private final GalleryJsonConverter galleryConverter;
    private List<HttpCookie> cookies;
    //private final PostEndPoint poe;

    public enum MANAGECOLLECTION {
        NOTCONNECTED,
        OK,
        KO
    }

    public enum STATUS {
        WISHED(0), ORDERED(1), OWNED(2), DELETE(9);

        int method;

        STATUS(int i) {
            method = i;
        }

        public int toInt() {
            return method;
        }

        @Override
        public String toString() {
            return "" + method;
        }
    }


    public enum ROOT {
        FIGURES(0), GOODS(1), MEDIA(2);

        int method;

        ROOT(int i) {
            method = i;
        }

        public int toInt() {
            return method;
        }

        @Override
        public String toString() {
            return "" + method;
        }
    }

    public enum SHIPPING {
        NA(0), EMS(1), SAL(2), AIRMAIL(3), SURFACE(4), FEDEX(5), DHL(6), COLISSIMO(7), UPS(8), DOMESTIC(9);

        int method;

        SHIPPING(int i) {
            method = i;
        }

        public int toInt() {
            return method;
        }
    }

    public enum SUBSTATUS {
        NA(0), SECONDHAND(1), SEALED(2), STORE(3);

        int method;

        SUBSTATUS(int i) {
            method = i;
        }

        public int toInt() {
            return method;
        }
    }


    private MFCRequest() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .followRedirects(false);
        client = builder.build();

        //poe = new PostEndPoint(PostEndPoint.MODE.LOGIN);

        standartConverter = new DynamicJsonConverter();
        galleryConverter = new GalleryJsonConverter();

        restAdapter = new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .client(client.newBuilder().build())
                .build();

        /*
        restAdapter = new RestAdapter.Builder()
                .setClient(new OkClient(client))
                .setConverter(standartConverter)
                .setEndpoint(ROOT_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
                */

        galleryAdapter = new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .client(client.newBuilder().build())
                //.setConverter(galleryConverter)
                //.setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        connectAdapter = new Retrofit.Builder()
                .client(client.newBuilder().build())
                //.setEndpoint(poe)
                //.setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

    }

    public Retrofit getRestAdapter() {
        return restAdapter;
    }

    public Retrofit getConnectAdapter() {
        return connectAdapter;
    }

    public OkHttpClient getClient() {
        return client;
    }


    public CollectionService getCollectionService() {
        return restAdapter.create(CollectionService.class);
    }

    public GalleryService getGalleryService() {
        return galleryAdapter.create(GalleryService.class);
    }

    public SearchService getSearchService() {
        return restAdapter.create(SearchService.class);
    }

    public UserService getUserService() {
        return restAdapter.create(UserService.class);
    }

    /**
     * Helper to send a connection request
     *
     * @param username the user login name
     * @param password the user password
     * @param context  an application context for the cookie store
     * @param callback calls success true if connection succeed, calls success false if everything went ok but connexion failed, calls failure otherwise
     */
    public void connect(String username, String password, final Context context, final MFCCallback<Boolean> callback) {

        //TODO Manca da aggiornare il client!
        client.newBuilder().cookieJar(new JavaNetCookieJar(new CookieManager(
                new PersistentCookieStore(context),
                CookiePolicy.ACCEPT_ALL))).build();

        Call<Response> responseCall = connectAdapter.create(ConnexionService.class).connectUser(username, password, 1, "signin", "http://myfigurecollection.net/");
        responseCall.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, Response<Response> response) {
                if (response.isSuccessful() || response.code() == 302) {
                    //Request went well, but MFC should return a HTTP 302 status if connection succeeded
                    callback.success(checkCookies(context));
                } else {
                    callback.success(false);
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                callback.error(t);
            }
        });
    }

    /**
     * removes all connection cookies from the permanent cookie store
     *
     * @param context  an application context for the cookie store
     * @param callback calls success true if removal succeed, calls success false if something went wrong
     */
    public void disconnect(final Context context, final Callback<Boolean> callback) {
        PersistentCookieStore persistentCookieStore = new PersistentCookieStore(context);
        callback.success(persistentCookieStore.removeAll(), null);
    }

    public boolean checkCookies(Context context) {
        try {
            PersistentCookieStore persistentCookieStore = new PersistentCookieStore(context);

            cookies = persistentCookieStore.get(new URI("https://myfigurecollection.net/"));
        } catch (URISyntaxException e) {
            cookies = new ArrayList<>();
        }

        for (HttpCookie cookie : cookies) {
            if (cookie.getName().equalsIgnoreCase("tb_session_id")) {
                return true;
            }
        }
        return false;
    }

    public void orderFigure(String figureId, int number, double price, String where, SHIPPING method, String trackingNumber, ItemDate boughtDate, ItemDate shippingDate, SUBSTATUS substatus, STATUS previousStatus, Context context, final Callback<MANAGECOLLECTION> callback) {
        if (checkCookies(context)) {


            //poe.setMode(PostEndPoint.MODE.ITEM);
            connectAdapter.create(ManageItemService.class).orderItem(figureId, "collect", STATUS.ORDERED.toInt(), number, price, where, method.toInt(), trackingNumber, boughtDate, shippingDate, substatus.toInt(), previousStatus.toInt(), 0, new Callback<AlterItem>() {
                @Override
                public void success(AlterItem alterItem, Response response) {
                    callback.success(MANAGECOLLECTION.OK, response);
                }

                @Override
                public void failure(RetrofitError error) {
                    callback.failure(error);
                }
            });

        } else {
            callback.success(MANAGECOLLECTION.NOTCONNECTED, null);
        }
    }

    public void ownFigure(String figureId, int number, ItemDate odate, int score, double price, String where, SHIPPING method, String trackingNumber, ItemDate boughtDate, ItemDate shippingDate, SUBSTATUS substatus, STATUS previousStatus, Context context, final Callback<MANAGECOLLECTION> callback) {
        if (checkCookies(context)) {


            //poe.setMode(PostEndPoint.MODE.ITEM);
            connectAdapter.create(ManageItemService.class).ownItem(figureId, "collect", STATUS.OWNED.toInt(), number, score, odate, price, where, method.toInt(), trackingNumber, boughtDate, shippingDate, substatus.toInt(), previousStatus.toInt(), 0, new Callback<AlterItem>() {
                @Override
                public void success(AlterItem alterItem, Response response) {
                    callback.success(MANAGECOLLECTION.OK, response);
                }

                @Override
                public void failure(RetrofitError error) {
                    callback.failure(error);
                }
            });
        } else {
            callback.success(MANAGECOLLECTION.NOTCONNECTED, null);
        }
    }

    public void wishFigure(String figureId, int wishability, STATUS previousStatus, Context context, final Callback<MANAGECOLLECTION> callback) {
        if (checkCookies(context)) {


            poe.setMode(PostEndPoint.MODE.ITEM);
            connectAdapter.create(ManageItemService.class).wishItem(figureId, "collect", STATUS.WISHED.toInt(), wishability, previousStatus.toInt(), 0, new Callback<AlterItem>() {
                @Override
                public void success(AlterItem alterItem, Response response) {
                    callback.success(MANAGECOLLECTION.OK, response);
                }

                @Override
                public void failure(RetrofitError error) {
                    callback.failure(error);
                }
            });
        } else {
            callback.success(MANAGECOLLECTION.NOTCONNECTED, null);
        }
    }

    public void deleteFigure(String figureId, STATUS previousStatus, Context context, final Callback<MANAGECOLLECTION> callback) {
        if (checkCookies(context)) {


            //poe.setMode(PostEndPoint.MODE.ITEM);
            connectAdapter.create(ManageItemService.class).deleteItem(figureId, "collect", STATUS.DELETE.toInt(), previousStatus.toInt(), 0, new Callback<AlterItem>() {
                @Override
                public void success(AlterItem alterItem, Response response) {
                    callback.success(MANAGECOLLECTION.OK, response);
                }

                @Override
                public void failure(RetrofitError error) {
                    callback.failure(error);
                }
            });
        } else {
            callback.success(MANAGECOLLECTION.NOTCONNECTED, null);
        }
    }


    public interface MFCCallback<T> {
        void success(T t);

        void error(Throwable throwable);
    }

}
