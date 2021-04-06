using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebApp.Api.Model
{
    /// <summary>
    /// clase que modela la data de los usuarios admin y chef
    /// </summary>
    public class User
    {       
        //nombre del usuario
        public string name { get; set; }
        //correo electronico del usuario
        public string email { get; set; }
        //contrasena del usuario
        public string password { get; set; }
        //booleano que indica si tiene acceso a modificar platillos
        public bool access { get; set; }
        //indica si los credenciales coinciden o si el usuario existe
        public bool credentials { get; set; } = true;
    }
}
