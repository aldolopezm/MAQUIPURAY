<?php

namespace App\Model;

use Illuminate\Support\Facades\DB;
use Illuminate\Database\Eloquent\Model;

class Promocion extends Model
{
    protected $table = "tbl_promocion";
    protected $primaryKey= "idPromocion";
    protected $fillable = [
        'idNegocio',
        'codigoPromocion',
        'slug',
        'nombre',
        'descripcion',
        'imagen',
        'precio',
    ];
    public $timestamps = false;

    public static function PromocionListarCategoria($idCategoria)
    {
        return DB::table('tbl_promocion as p')
        ->select(
            'p.idPromocion',
            'p.idNegocio',
            'p.codigoPromocion',
            'p.slug as slugPromocion',
            'p.nombre',
            'p.descripcion',
            'p.imagen',
            'p.precio',
            'n.idUsuario',
            'n.idCategoria',
            'n.slug as slugNegocio',
            'n.nombre as nombreNegocio',
            'n.descripcion as descripcionNegocio',
            'n.direccion',
            'n.telefono',
            'n.latitud',
            'n.longitud',
            'n.protocoloSeguridad',
            'n.estado'
        )
        ->join('tbl_negocio as n', 'n.idNegocio', 'p.idNegocio')
        ->where('n.idCategoria', $idCategoria)
        ->get();
    }

    public static function PromocionDetalle($idPromocion)
    {
        return DB::table('tbl_promocion as p')
            ->select(
                'p.idPromocion',
                'p.idNegocio',
                'n.nombre as nombreNegocio',
                'p.codigoPromocion',
                'p.slug',
                'p.nombre',
                'p.descripcion',
                'p.imagen',
                'p.precio'
            )
            ->join('tbl_negocio as n', 'n.idNegocio', 'p.idNegocio')
            ->get();
    }
}
