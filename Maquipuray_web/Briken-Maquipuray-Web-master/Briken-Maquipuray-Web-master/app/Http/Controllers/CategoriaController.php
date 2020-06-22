<?php

namespace App\Http\Controllers;

use App\Model\Categoria;
use Illuminate\Http\Request;
use Illuminate\Database\QueryException;

class CategoriaController extends Controller
{
    public function CategoriaListarJson()
    {
        $data = "";
        $mensaje ="";
        try {
            $data = Categoria::CategoriaListar();
        } catch (QueryException $ex) {
            $mensaje = $ex->errorInfo;
        }
        return response()->json(['data'=>$data,'mensaje'=>$mensaje]);
    }
}
