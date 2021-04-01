using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebApp.Api.Model
{
    public class Client : User
    {
        //celular del cliente
 
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

    }
}
