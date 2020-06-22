<?php

namespace App\Model;

use Illuminate\Http\Request;
use Illuminate\Database\Eloquent\Model;

class Persona extends Model
{
    protected $table = "tbl_persona";
    protected $primaryKey = "idPersona";
    protected $fillable = [
        'nombre',
        'apellido',
        'telefono',
        'email',
    ];
    public $timestamps = false;

    public static function PersonaRegistrar(Request $request){
        $data = new Persona();
        $data->nombre = $request->input('nombre');
        $data->apellido = $request->input('apellido');
        $data->telefono = $request->input('telefono');
        $data->email = $request->input('email');
        $data->save();
        return $data;
    }
}
