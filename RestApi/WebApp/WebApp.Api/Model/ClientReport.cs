using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebApp.Api.Model
{
    /// <summary>
    /// clase que modela la data de los reportes de los clientes
    /// </summary>
    public class ClientReport
    {
        //string que indica el nombre del cliente del reporte
        public string clientName { get; set; }
        //int que indica el id del cliente del reporte
        public int clientID { get; set; }
        // int que indica cuantos platos ha comprado el cliente
        public int recipeCount { get; set; }

    }
}
