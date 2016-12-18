package com.ant_robot.mfc.api.request;

import android.content.Context;

import com.ant_robot.mfc.api.pojo.AlterItem;
import com.ant_robot.mfc.api.request.cookie.PersistentCookieStore;
import com.ant_robot.mfc.api.request.json.DynamicJsonConverterFactory;
import com.ant_robot.mfc.api.request.json.GalleryJsonConverterFactory;
import com.ant_robot.mfc.api.request.service.CollectionService;
import com.ant_robot.mfc.api.request.service.ConnexionService;
import com.ant_robot.mfc.api.request.service.GalleryService;
import com.ant_robot.mfc.api.request.service.ItemDate;
import com.ant_robot.mfc.api.request.service.ManageItemService;
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
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MFCRequest {
    private static MFCRequest INSTANCE;
    private Context mContext;
    public static final String ROOT_URL = "http://myfigurecollection.net/";
    public static final String LOGIN = "https://secure.myfigurecollection.net/";
    public static final String ITEM = "http://myfigurecollection.net/items.php";
    private final Retrofit restAdapter;
    private final Retrofit connectAdapter;
    private final Retrofit galleryAdapter;

    private final OkHttpClient client;
    private final DynamicJsonConverterFactory standardConverterFactory;
    private final GalleryJsonConverterFactory galleryConverterFactory;
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
        INSTANCE = this;
        throw new IllegalStateException("Unavailable contructor. Use MFCRequest(Context) constructor.");
    }

    private MFCRequest(Context mContext) {
        INSTANCE = this;
        this.mContext = mContext.getApplicationContext();

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        // set your desired log level
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .followRedirects(false);
        client = builder.build();

        standardConverterFactory = new DynamicJsonConverterFactory();
        galleryConverterFactory = new GalleryJsonConverterFactory();

        restAdapter = new Retrofit.Builder()
                .addConverterFactory(standardConverterFactory)
                .baseUrl(ROOT_URL)
                .client(client.newBuilder().build())
                .build();

        galleryAdapter = new Retrofit.Builder()
                .addConverterFactory(galleryConverterFactory)
                .baseUrl(ROOT_URL)
                .client(client.newBuilder().build())
                .build();

        connectAdapter = new Retrofit.Builder()
                .baseUrl(LOGIN)
                .client(client.newBuilder().cookieJar(new JavaNetCookieJar(new CookieManager(
                        new PersistentCookieStore(mContext),
                        CookiePolicy.ACCEPT_ALL))).build())
                //.setEndpoint(poe)
                .build();
    }

    public static MFCRequest initialize(Context context) {
        synchronized (MFCRequest.class) {
            if (INSTANCE != null) {
                throw new IllegalStateException("Library is already initialized.");
            }
            return new MFCRequest(context);
        }
    }

    public static MFCRequest getInstance() {
        if (INSTANCE == null) {
            throw new IllegalStateException("Library must me initialized before use it.");
        }
        return INSTANCE;
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
     * @param callback calls success true if connection succeed, calls success false if everything went ok but connexion failed, calls failure otherwise
     */
    public void connect(String username, String password, final MFCCallback<Boolean> callback) {
        Call<ResponseBody> responseCall = connectAdapter.create(ConnexionService.class).connectUser(username, password, 1, "signin", "http://myfigurecollection.net/");
        responseCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful() || response.code() == 302) {
                    //Request went well, but MFC should return a HTTP 302 status if connection succeeded
                    callback.success(checkCookies(mContext));
                } else {
                    callback.success(false);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
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
    public void disconnect(final Context context, final MFCCallback<Boolean> callback) {
        PersistentCookieStore persistentCookieStore = new PersistentCookieStore(context);
        callback.success(persistentCookieStore.removeAll());
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

    public void orderFigure(String figureId, int number, double price, String where, SHIPPING method, String trackingNumber, ItemDate boughtDate, ItemDate shippingDate, SUBSTATUS substatus, STATUS previousStatus, Context context, final MFCCallback<MANAGECOLLECTION> callback) {
        if (checkCookies(context)) {

            //TODO Risolvere questo problema
            //poe.setMode(PostEndPoint.MODE.ITEM);
            Call<AlterItem> alterItemCall = connectAdapter.create(ManageItemService.class).orderItem(figureId, "collect", STATUS.ORDERED.toInt(), number, price, where, method.toInt(), trackingNumber, boughtDate, shippingDate, substatus.toInt(), previousStatus.toInt(), 0);
            alterItemCall.enqueue(new Callback<AlterItem>() {
                @Override
                public void onResponse(Call<AlterItem> call, Response<AlterItem> response) {
                    callback.success(MANAGECOLLECTION.OK);
                }

                @Override
                public void onFailure(Call<AlterItem> call, Throwable t) {
                    callback.error(t);
                }
            });
        } else {
            callback.success(MANAGECOLLECTION.NOTCONNECTED);
        }
    }

    public void ownFigure(String figureId, int number, ItemDate odate, int score, double price, String where, SHIPPING method, String trackingNumber, ItemDate boughtDate, ItemDate shippingDate, SUBSTATUS substatus, STATUS previousStatus, Context context, final MFCCallback<MANAGECOLLECTION> callback) {
        if (checkCookies(context)) {

            //TODO Risolvere questo problema
            //poe.setMode(PostEndPoint.MODE.ITEM);
            Call<AlterItem> alterItemCall = connectAdapter.create(ManageItemService.class).ownItem(figureId, "collect", STATUS.OWNED.toInt(), number, score, odate, price, where, method.toInt(), trackingNumber, boughtDate, shippingDate, substatus.toInt(), previousStatus.toInt(), 0);
            alterItemCall.enqueue(new Callback<AlterItem>() {
                @Override
                public void onResponse(Call<AlterItem> call, Response<AlterItem> response) {
                    callback.success(MANAGECOLLECTION.OK);
                }

                @Override
                public void onFailure(Call<AlterItem> call, Throwable t) {
                    callback.error(t);
                }
            });
        } else {
            callback.success(MANAGECOLLECTION.NOTCONNECTED);
        }
    }

    public void wishFigure(String figureId, int wishability, STATUS previousStatus, Context context, final MFCCallback<MANAGECOLLECTION> callback) {
        if (checkCookies(context)) {

            //TODO Risolvere questo problema
            //poe.setMode(PostEndPoint.MODE.ITEM);
            Call<AlterItem> alterItemCall = connectAdapter.create(ManageItemService.class).wishItem(figureId, "collect", STATUS.WISHED.toInt(), wishability, previousStatus.toInt(), 0);
            alterItemCall.enqueue(new Callback<AlterItem>() {
                @Override
                public void onResponse(Call<AlterItem> call, Response<AlterItem> response) {
                    callback.success(MANAGECOLLECTION.OK);
                }

                @Override
                public void onFailure(Call<AlterItem> call, Throwable t) {
                    callback.error(t);
                }
            });
        } else {
            callback.success(MANAGECOLLECTION.NOTCONNECTED);
        }
    }

    public void deleteFigure(String figureId, STATUS previousStatus, Context context, final MFCCallback<MANAGECOLLECTION> callback) {
        if (checkCookies(context)) {
            //TODO Risolvere questo problema
            //poe.setMode(PostEndPoint.MODE.ITEM);
            Call<AlterItem> alterItemCall = connectAdapter.create(ManageItemService.class).deleteItem(figureId, "collect", STATUS.DELETE.toInt(), previousStatus.toInt(), 0);
            alterItemCall.enqueue(new Callback<AlterItem>() {
                @Override
                public void onResponse(Call<AlterItem> call, Response<AlterItem> response) {
                    callback.success(MANAGECOLLECTION.OK);
                }

                @Override
                public void onFailure(Call<AlterItem> call, Throwable t) {
                    callback.error(t);
                }
            });
        } else {
            callback.success(MANAGECOLLECTION.NOTCONNECTED);
        }
    }


    public interface MFCCallback<T> {
        void success(T t);

        void error(Throwable throwable);
    }
}
