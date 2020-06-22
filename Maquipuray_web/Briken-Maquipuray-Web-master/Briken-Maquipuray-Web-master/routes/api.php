<?php

Route::post('UsuarioRegistrarClienteJson', 'UsuarioController@UsuarioRegistrarClienteJson');
Route::post('UsuarioAutenticarMovilJson','UsuarioController@UsuarioAutenticarMovilJson');

#region Categoria
Route::get('CategoriaListarJson','CategoriaController@CategoriaListarJson');
#endregion

#region Promocion
Route::get('PromocionListarCategoriaJson','PromocionController@PromocionListarCategoriaJson');
Route::get('PromocionDetalleJson','PromocionController@PromocionDetalleJson');
#endregion