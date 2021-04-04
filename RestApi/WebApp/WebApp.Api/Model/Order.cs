using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebApp.Api.Model
{
    public class Order
    {
        //indica la fecha en la que se realizo el pedido
        public string date { get; set; }
        //hora en la que se ordeno el pedido
        public string orderTime { get; set; }
        //monto total del pedido
        public int totalAmount { get; set; }
        //cedula del usuario a quien pertenece el pedido
        public int userID { get; set; }
        //booleano que indica si el pedido ha sido asginado o no
        public bool assigned { get; set; }
        //indica numero para identificar un pedido
        public int orderID { get; set; }
        //platos incluidos en el pedido
        public List<Recipe> recipeIncluded { get; set; }
        //nombre del chef que tomo el pedido para prepararlo
        public string chefName { get; set; }
    }
}
