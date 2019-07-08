package com.carsuper.coahr.dagger.modules.retrofit;

import com.carsuper.coahr.mvp.view.base.BaseApplication;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;

/**
 * https证书管理
 */

public class SSLContextFactory {
    static final String CLIENT_AGREEMENT = "TLS";//使用协议

    public static SSLSocketFactory getSSLSocketFactory(String keyStoreType, int keystoreResId) {
        try {
            CertificateFactory cf = CertificateFactory.getInstance("X.509", "BC");

            InputStream caInput = BaseApplication.mContext.getResources().openRawResource(keystoreResId);
            Certificate ca = cf.generateCertificate(caInput);
            caInput.close();

            if (keyStoreType == null || keyStoreType.length() == 0) {
                keyStoreType = KeyStore.getDefaultType();
            }

            //取得BKS密库实例
            KeyStore keyStore = KeyStore.getInstance(keyStoreType);
            keyStore.load(null, null);
            keyStore.setCertificateEntry("ca", ca);

            String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
            //取得TrustManagerFactory的X509密钥管理器实例
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
            //初始化密钥管理器
            tmf.init(keyStore);
            TrustManager[] wrappedTrustManagers = getWrappedTrustManagers(tmf.getTrustManagers());
            //取得SSL的SSLContext实例
            SSLContext sslContext = SSLContext.getInstance(CLIENT_AGREEMENT);
            //初始化SSLContext
            sslContext.init(null, wrappedTrustManagers, null);
            return sslContext.getSocketFactory();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 忽略证书
     *
     * @return
     */
    public static OkHttpClient.Builder getSSLTrustAllOkHttpClientBuilder() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        try {
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder;
    }

    private static TrustManager[] getWrappedTrustManagers(TrustManager[] trustManagers) {
        final X509TrustManager originalTrustManager = (X509TrustManager) trustManagers[0];
        return new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                        try {
                            originalTrustManager.checkClientTrusted(chain, authType);
                        } catch (CertificateException e) {
                            e.printStackTrace();

                        }
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                        try {
                            originalTrustManager.checkServerTrusted(chain, authType);
                        } catch (CertificateException e) {
                            e.printStackTrace();
                        }
                    }

                    public X509Certificate[] getAcceptedIssuers() {
                        return originalTrustManager.getAcceptedIssuers();
                    }
                }
        };
    }

    protected static HostnameVerifier getHostnameVerifier(final String[] hostUrls) {
        HostnameVerifier TRUSTED_VERIFIER = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                boolean ret = false;
                for (String host : hostUrls) {
                    if (host.equalsIgnoreCase(hostname)) {
                        ret = true;
                    }
                }
                return ret;
            }
        };
        return TRUSTED_VERIFIER;
    }

    //忽略证书验证
    public final static HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };
}