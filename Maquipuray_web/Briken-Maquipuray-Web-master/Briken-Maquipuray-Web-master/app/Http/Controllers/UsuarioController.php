<?php

namespace App\Http\Controllers;

use DB;
use App\Model\Persona;
use App\Model\Usuario;
use Illuminate\Http\Request;
use Tymon\JWTAuth\Facades\JWTAuth;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\Hash;
use Illuminate\Database\QueryException;

class UsuarioController extends Controller
{
    public function UsuarioRegistrarClienteJson(Request $request)
    {
        $respuesta = false;
        $mensaje ="";
        $data = "";
        try {
            DB::beginTransaction();
            $persona = Persona::PersonaRegistrar($request);
            $req = new Request([
                'idPersona'=>$persona->idPersona,
                'email'=>$request->input('email'),
                'password'=>$request->input('password'),
            ]);
            $data = Usuario::UsuarioRegistrarCliente($req);
            $respuesta = true;
            $mensaje = "Se ha registrado exitosamente";
            DB::commit();
        } catch (QueryException $ex) {
            $mensaje = $ex->errorInfo;
            DB::rollback();            
        }
        return response()->json(['respuesta'=>$respuesta,'mensaje'=>$mensaje,'data'=>$data]);
    }

    public function UsuarioAutenticarWebJson(Request $request){
        $redirect = "";
        $respuesta = false;
        $email = $request->input('email');
        $password = $request->input('password');
        $usuario_data = Usuario::where('email', $email)->first();
        $token = "";

        if ($usuario_data != null) {
            if (Hash::check($password, $usuario_data->password)) {
                if ($usuario_data->activo != 1) {
                    $mensaje = "El usuario " . $usuario_data->Persona->nombre . ' ' . $usuario_data->Persona->apellido . ' ha sido bloqueado';
                } else {
                    $credenciales = $request->only('email', 'password');
                    if (!$token = JWTAuth::attempt($credenciales)) {
                        return response()->json(['error' => 'credenciales invalidas'], 400);
                    } else {
                        Auth::attempt(['email' => $usuario, 'password' => $password]);
                        $respuesta = true;
                        $mensaje = 'BIENVENIDO AL SISTEMA ' . $usuario_data->Persona->nombre . ' ' . $usuario_data->Persona->apellido;
                        $redirect = "Inicio";
                    }
                }
            } else {
                $mensaje = 'La contraseña ingresada es erronea';
            }
        } else {
            $mensaje = 'El usuario ingresado no existe en nuestros registros';
        }
        return response()->json(['respuesta' => $respuesta, 'mensaje' => $mensaje, 'redigirir' => $redirect, 'token' => $token]);
    }
    public function UsuarioAutenticarMovilJson(Request $request){
        $redirect = "";
        $respuesta = false;
        $email = $request->input('email');
        $password = $request->input('password');
        $usuario_data = Usuario::with(['persona'])->where('email', $email)->first();
        $token = "";

        if ($usuario_data != null) {
            if (Hash::check($password, $usuario_data->password)) {
                if ($usuario_data->estado != 1) {
                    $mensaje = "El usuario " . $usuario_data->Persona->nombre . ' ' . $usuario_data->Persona->apellido . ' ha sido bloqueado';
                } else {
                    $credenciales = $request->only('email', 'password');
                    if (!$token = JWTAuth::attempt($credenciales)) {
                        return response()->json(['error' => 'credenciales invalidas'], 400);
                    } else {
                        $respuesta = true;
                        $nombreApellido = $usuario_data->persona->nombre . ' ' . $usuario_data->persona->apellido;
                        $mensaje = 'BIENVENIDO AL SISTEMA' . $nombreApellido;
                        $redirect = "Inicio";
                        //Actualizar Token
                        // $usuario_data->token = $token;
                        // $usuario_data->save();
                    }
                }
            } else {
                $mensaje = 'La contraseña ingresada es erronea';
                $usuario_data = "";
            }
        } else {
            $mensaje = 'El usuario ingresado no existe en nuestros registros';
            $usuario_data = "";
        }
        return response()->json(['respuesta' => $respuesta, 'mensaje' => $mensaje, 'token' => $token,'data'=>$usuario_data]);
    }
}
