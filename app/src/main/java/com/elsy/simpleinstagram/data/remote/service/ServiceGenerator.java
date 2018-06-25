package com.elsy.simpleinstagram.data.remote.service;

import com.elsy.simpleinstagram.data.APIConstants;

import java.io.IOException;
import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Provides methods to easily create API services providing the concrete service class and
 * the user auth token if is needed.
 *
 */

public class ServiceGenerator {

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(APIConstants.URL)
                    .addConverterFactory(GsonConverterFactory.create());

    /**
     * Generates a simple out of the box retrofit service without a token.
     * @param serviceClass service defined using the API reference.
     * @param <S> type of service to call.
     * @return out of the box retrofit service to be used to make requests.
     */
    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null);
    }

    /**
     *  Generates a out of the box retrofit service with a token on the headers.
     *
     * @param serviceClass service defined using the API reference.
     * @param <S> type of service to call.
     * @param authToken user token necessary to make certain requests.
     * @return out of the box retrofit service to be used to make requests.
     */
    public static <S> S createService(Class<S> serviceClass, final String authToken) {
        if (authToken != null) {
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();
                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Authorization", authToken)
                            .method(original.method(), original.body());

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });
        }

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

            final TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            httpClient.sslSocketFactory(sslSocketFactory);
            httpClient.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            OkHttpClient okHttpClient = httpClient.build();
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
