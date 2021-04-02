using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebApp.Api.Model
{
    public class Client
    {
    
        public int iD { get; set; }
        //nombre del cliente
        public string name { get; set; }
        //apellido del clente
        public string lastName { get; set; }
        //direccion del cliente
        public string direction { get; set; }
        //fecha de nacimiento
        public string birthday { get; set; }
        public int celNum { get; set; }
        //cedula del cliente
        //correo electronico del usuario
        public string email { get; set; }
        //contrasena del usuario
        public string password { get; set; }

    }
}
