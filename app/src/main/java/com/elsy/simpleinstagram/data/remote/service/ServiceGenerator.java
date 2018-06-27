package com.elsy.simpleinstagram.data.remote.service;

import android.annotation.SuppressLint;
import com.elsy.simpleinstagram.data.remote.APIConstants;
import com.elsy.simpleinstagram.utils.ActivityHelper;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Provides methods to easily create API services providing the concrete service class and
 * the user auth token if is needed.
 *
 */

public class ServiceGenerator {

    private static final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static final Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(APIConstants.URL)
                    .addConverterFactory(GsonConverterFactory.create());
    private static final String SECURE_SOCKETS_LAYER = "SSL";

    /**
     * Generates a simple out of the box retrofit service without a token.
     * @param serviceClass service defined using the API reference.
     * @param <S> type of service to call.
     * @return out of the box retrofit service to be used to make requests.
     */
    public static <S> S createService(Class<S> serviceClass) {
        OkHttpClient client = getUnsafeOkHttpClient();
        Retrofit retrofit = builder.client(client).build();
        return retrofit.create(serviceClass);
    }

    /**
     * To avoid SSL problems this method build a unsafe {@link OkHttpClient} to
     * make server request
     * @return a Unsafe HTTP client instance.
     */
    private static OkHttpClient getUnsafeOkHttpClient() {
        try {

            X509TrustManager x509TrustManager = new X509TrustManager() {
                @SuppressLint("TrustAllX509TrustManager")
                @Override
                public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                }

                @SuppressLint("TrustAllX509TrustManager")
                @Override
                public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[]{};
                }
            };

            final TrustManager[] trustAllCerts = new TrustManager[] {
                    x509TrustManager
            };

            final SSLContext sslContext = SSLContext.getInstance(SECURE_SOCKETS_LAYER);
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            httpClient.sslSocketFactory(sslSocketFactory, x509TrustManager);
            httpClient.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return ActivityHelper.isValidHostame(hostname);
                }
            });

            return httpClient.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
