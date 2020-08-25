package com.maquipuray.maquipuray_apk.data.remote;


import com.maquipuray.maquipuray_apk.data.remote.model.FcmNotificationResponse;
import com.maquipuray.maquipuray_apk.data.remote.model.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface FCMAPIService {

    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:"+ ApiEndPoint.FCM_AUTORIZATION_KEY
            }

    )

    @POST("fcm/send")
    Call<FcmNotificationResponse> sendNotification(@Body Sender body);
}
