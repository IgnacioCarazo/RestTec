using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebApp.Api.Model
{
    //Clase que indica
    public class User
    {       
        //correo electronico del usuario
        public string email { get; set; }
        //contrasena del usuario
        public string password { get; set; }
        //booleano que indica si tiene acceso a modificar platillos
        public bool access { get; set; }
    }
}
