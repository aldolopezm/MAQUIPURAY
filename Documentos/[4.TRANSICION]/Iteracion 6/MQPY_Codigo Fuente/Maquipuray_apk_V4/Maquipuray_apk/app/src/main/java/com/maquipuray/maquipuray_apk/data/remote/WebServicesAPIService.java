package com.maquipuray.maquipuray_apk.data.remote;


import com.maquipuray.maquipuray_apk.data.remote.model.ApiResponse;
import com.maquipuray.maquipuray_apk.data.remote.model.CategoriaResponse;
import com.maquipuray.maquipuray_apk.data.remote.model.PedidoRegistrar;
import com.maquipuray.maquipuray_apk.data.remote.model.PromocionResponse;
import com.maquipuray.maquipuray_apk.data.remote.model.Usuario;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface WebServicesAPIService {


    //Sending Json Object - example php webservices
//    @Headers("Content-Type: application/json")
//    @POST("restaurante")
//    Call<ResponseRestaurant> sendRestaurant(@Body Restaurante restaurante);

    @FormUrlEncoded
    @POST("enviarnotificacion.php")
    Call<ResponseBody> sendnotificacion(@Field("nuevoToken") String email, @Field("imei") String password, @Field("appDetails") String appName);
//    Call<ResponseTestToken> getResponse(@Field("email") String email, @Field("password") String password) ;

    //region REGION FIELD URL ENCODE METHOD
//    @FormUrlEncoded
//    @POST("/login")
//    Call<ResponseBody> getResponse(@Field("email") String email, @Field("password") String password) ;



    //receive an array
//    @Headers("Content-Type: application/json")
//    @GET("3/list/15570")
//    Call<List<MovieTmdbResponse>> getMoviesTmdb(@Query("api_key") String apiKey,
//                                                @Query("language") String language);
//endregion



    @Headers("Content-Type: application/json")
    @GET("CategoriaListarJson")
    Call<ApiResponse<CategoriaResponse>> getCategorias(@Header("Authorization") String token);

    @Headers("Content-Type: application/json")
    @GET("PromocionListarCategoriaJson")
    Call<ApiResponse<PromocionResponse> > getPromocionesXCategoria(@Header("Authorization") String token,
                                                                   @Query("idCategoria") int idCategoria);

    @FormUrlEncoded
    @POST("PedidoRegistrarJson")
    Call<ResponseBody> registrarPedido(@Header("Authorization") String token, @Field("idUsuario") int idUsuario, @Field("idPromocion") int idPromocion) ;

        @Headers("Content-Type: application/json")
    @POST("PedidoRegistrarJson")
    Call<ResponseBody> solicitarRegistroPedido(@Header("Authorization") String token, @Body PedidoRegistrar pedidoRegistrar);

    @FormUrlEncoded
    @POST("UsuarioAutenticarMovilJson")

    Call<ResponseBody> autenticarUsuario(@Field("email") String email, @Field("password") String password) ;

//    return response()->json(['error' => 'credenciales invalidas'], 400);
//    return response()->json(['respuesta' => $respuesta, 'mensaje' => $mensaje, 'token' => $token,'data'=>$usuario_data]);

    @Headers("Content-Type: application/json")
    @POST("UsuarioRegistrarClienteMovilJson")
    Call<ResponseBody> registrarUsuario(@Body Usuario usuario);





}
