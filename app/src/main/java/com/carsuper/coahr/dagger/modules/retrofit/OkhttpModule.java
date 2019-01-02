package com.carsuper.coahr.dagger.modules.retrofit;

import android.text.TextUtils;

import com.carsuper.coahr.BuildConfig;
import com.carsuper.coahr.common.Configuration;
import com.carsuper.coahr.common.Constants;
import com.carsuper.coahr.mvp.model.HttpLogging.MyHttpLogging;
import com.socks.library.KLog;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;


@Module
public class OkhttpModule {

    HttpLoggingInterceptor logInterceptor;
    @Singleton
    @Provides
    @Named("default")
    public OkHttpClient providerOkHttpClient(){


        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (builder.interceptors() != null) {
            builder.interceptors().clear();
        }

        builder.connectTimeout(Constants.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.readTimeout(Constants.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.writeTimeout(Constants.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);

        //DEBUG模式下配Log拦截器
        if (BuildConfig.DEBUG) {
             logInterceptor = new HttpLoggingInterceptor(new MyHttpLogging());
             logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        }
        if (BuildConfig.DEBUG){
            if (Configuration.isShowNetworkParams()) {
                builder.addInterceptor(logInterceptor);
            }
        }

        return builder.build();
    }



    @Singleton
    @Provides
    @Named("cache")
    public OkHttpClient providerAutoCacheOkHttpClient(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response response = chain.proceed(request);

                String cacheControl = request.cacheControl().toString();
                if (TextUtils.isEmpty(cacheControl)) {
                    cacheControl = "public, max-age=" + 3600 * 6 + " ,max-stale=2419200";
                }
                return response.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            }
        };
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(interceptor)
                .addNetworkInterceptor(cacheInterceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();
    }


}
