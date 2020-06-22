<?php

namespace App\Http\Controllers;

use App\Model\Promocion;
use Illuminate\Http\Request;

class PromocionController extends Controller
{
    public function PromocionListarCategoriaJson(Request $request)
    {
        $data = "";
        $mensaje = "";
        try {
            $data = Promocion::PromocionListarCategoria($request->input('idCategoria'));
        } catch (QueryException $ex) {
            $mensaje = $ex->errorInfo;
        }
        return response()->json(['data'=>$data,'mensaje'=>$mensaje]);
    }
    public function PromocionDetalleJson(Request $request)
    {
        $data = "";
        $mensaje = "";
        try {
            $data = Promocion::PromocionDetalle($request->input('idPromocion'));
        } catch (QueryException $ex) {
            $mensaje = $ex->errorInfo;
        }
        return response()->json(['data'=>$data,'mensaje'=>$mensaje]);
    }
}
