package com.maquipuray.maquipuray_apk;

import androidx.test.runner.AndroidJUnit4;

import junit.framework.TestCase;

import org.junit.runner.RunWith;

/**
 * Created by rogergcc on 16/07/2020.
 * Copyright Ⓒ 2020 . All rights reserved.
 */
@RunWith(AndroidJUnit4.class)
public class ApiServiceTest extends TestCase {
//    private MockWebServer mockWebServer ;
//
//    private Service2 apiService;
//
//    @Before
//    public void setUp() throws Exception {
//
//        mockWebServer = new MockWebServer();
//        mockWebServer.start();
//
//        apiService = new Retrofit.Builder()
//
////                .baseUrl(baseUrl)
//                .addConverterFactory(GsonConverterFactory.create())
////                .client(AppOkHttpClient())
//                .client(RetrofitClient.provideOkHttp())
//                .baseUrl(mockWebServer.url("/"))
//                .build()
//                .create(Service2.class)
//        ;
//
//    }
//
//    private static OkHttpClient provideOkHttp() {
//        return new OkHttpClient.Builder()
//                .connectTimeout(30, TimeUnit.SECONDS)
//                .writeTimeout(30, TimeUnit.SECONDS)
//                .readTimeout(30, TimeUnit.SECONDS)
//                .addNetworkInterceptor(provideLoggingInterceptor())
//                .build();
//    }
//    private static Interceptor provideLoggingInterceptor() {
//        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
//    }
//    @After
//    public void teardown() throws Exception {
//        mockWebServer.shutdown();
//    }
//
//    @Test
//    public void testAppVersions() throws Exception {
//        String fileName = "ok_response_json_200.json";
//        // Assign
//        MockResponse response =new MockResponse()
//                .setResponseCode(HttpURLConnection.HTTP_OK)
////                .setBody(getStringFromFile(RestServiceTestHelper.convertStreamToString(fileName)));
//                .setBody(RestServiceTestHelper.getStringFromFile(InstrumentationRegistry.getInstrumentation().getContext(), fileName));
//        try {
//            mockWebServer.enqueue(response);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        // Act
//        Call<ApiResponse<CategoriaResponse>> product = apiService.getCategorias("101");
//        // Assert
//
//    }


}
