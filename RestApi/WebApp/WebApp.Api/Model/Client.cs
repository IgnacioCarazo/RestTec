using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;


namespace WebApp.Api.Model
{
    /// <summary>
    /// clase que modela la data de los clientes
    /// </summary>
    public class Client
    {
        //int que indica id del cliente
        public int iD { get; set; }
        //nombre del cliente
        public string name { get; set; }
        //primer apellido del clente
        public string primaryLastName {get; set; }
        //segundo apellido del cliente
        public string secondLastName { get; set; }
        //*** direccion del cliente
        public string province { get; set; }
        public string canton { get; set; }
        public string district { get; set; }
        //***
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
