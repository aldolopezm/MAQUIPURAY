<?php

namespace App\Model;

use Carbon\Carbon;
use App\Model\Persona;
use Illuminate\Http\Request;
use Tymon\JWTAuth\Contracts\JWTSubject;
use Illuminate\Notifications\Notifiable;
use Illuminate\Contracts\Auth\MustVerifyEmail;
use Illuminate\Foundation\Auth\User as Authenticatable;

class Usuario extends Authenticatable implements JWTSubject
{
    use Notifiable;

    protected $table = "tbl_usuario";
    protected $primaryKey = "idUsuario";
    protected $fillable = [
        'idPersona',
        'idTipoUsuario',
        'email',
        'password',
        'token',
        'verificado',
        'fechaRegistro',
        'estado',
    ];
    public $timestamps = false;

    public function Persona()
    {
        return $this->belongsTo(Persona::class, 'idPersona');
    }

    public static function UsuarioRegistrarCliente(Request $request)
    {
        $data = new Usuario();
        $data->idPersona = $request->input('idPersona');
        $data->idTipoUsuario = 2;
        $data->email = $request->input('email');
        $data->password = bcrypt($request->input('password'));
        $data->verificado = 1;
        $data->fechaRegistro = Carbon::now()->toDateTimeString();
        $data->estado = 1;
        $data->save();
        return $data;
    }


    public function getJWTIdentifier()
    {
        return $this->getKey();
    }
    public function getJWTCustomClaims()
    {
        return [];
    }
}
