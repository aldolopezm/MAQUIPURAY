<?php

namespace App\Model;

use Illuminate\Support\Facades\DB;
use Illuminate\Database\Eloquent\Model;

class Categoria extends Model
{
    protected $table = "tbl_categoria";
    protected $primaryKey = "idCategoria";
    protected $fillable = [
        'slug',
        'nombre',
        'descripcion',
        'imagen',
        'fechaRegistro',
        'estado',
    ];
    public $timestamps = false;

    public static function CategoriaListar()
    {
        return DB::table('tbl_categoria')
            ->select(
                'idCategoria',
                'slug',
                'nombre',
                'descripcion',
                'imagen',
                'fechaRegistro',
                'estado'
            )
            ->get();
    }
}
