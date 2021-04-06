using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Threading.Tasks;
using WebApp.Api.Model;

/// <summary>
/// Clase que maneja toda la data de los pedidos
/// </summary>
namespace WebApp.Api.Data
{
    public class OrderData
    {
        /// <summary>
        /// metodo para escribir en la base de datos de los pedidos
        /// </summary>
        /// <param name="orders">lista de Order que se desean aregar</param>
        /// <returns>int siempre 0</returns>
        static public int writeData(List<Order> orders)
        {
            string strResultJson = JsonConvert.SerializeObject(orders);
            File.WriteAllText(@"Data/order.json", strResultJson);
            return 0;
        }

        /// <summary>
        /// metodo para obtener informacion de los pedidos
        /// </summary>
        /// <returns>lista Order en formato json</returns>
        static public List<Order> getOrderData()
        {
            string readFile = File.ReadAllText(@"Data/order.json");
            var orderList = JsonConvert.DeserializeObject<List<Order>>(readFile);
            return orderList;
        }
        
        /// <summary>
        /// metodo para obtener un pedido especifico segun su id
        /// </summary>
        /// <param name="id">int que indica el id del pedido a buscar</param>
        /// <returns>Order se busca</returns>
        static public Order getOrderData(int id)
        {
            string readFile = File.ReadAllText(@"Data/order.json");
            var orderList = JsonConvert.DeserializeObject<List<Order>>(readFile);
            var _order = orderList.SingleOrDefault(x => x.orderID == id);
            return _order;
        }

        /// <summary>
        /// metodo para eliminar un pedido
        /// </summary>
        /// <param name="id">id del pedido que se desea eliminar</param>
        /// <returns>Nueva lista de Order</returns>
        static public List<Order> deleteData(int id)
        {
            string readFile = File.ReadAllText(@"Data/order.json");
            var orderList = JsonConvert.DeserializeObject<List<Order>>(readFile);
            var order = orderList.SingleOrDefault(x => x.orderID == id);
            orderList.Remove(order);
            string strResultJson = JsonConvert.SerializeObject(orderList);
            File.WriteAllText(@"Data/order.json", strResultJson);
            return orderList;
        }

        /// <summary>
        /// metodo para manejar informacion de los pedidos como asignar chef, monto total, tiempo de finalizacion a los pedidos
        /// </summary>
        /// <param name="chef">string del nombre del chef que toma un pedido</param>
        /// <param name="orderID">int id del pedido que toma el chef</param>
        /// <returns>Order con su nueva informacion</returns>
        static public Order manageOrder(string chef, int orderID)
        {
            var order = OrderData.getOrderData(orderID);
            var orderList = OrderData.deleteData(orderID);
            order.chefName = chef;
            order.assigned = true;
            int totalAmount = 0;
            int chefTime = 0;
            foreach (var _order in orderList)
            {
                if (_order.chefName == chef)
                {
                    chefTime += 3;
                }
            }
            foreach (var recipe in order.recipeIncluded)
            {
                int recipeTime = 0;
                foreach (var ingredient in recipe.ingredients)
                {
                    recipeTime += ingredient.amount;
                }
                recipe.prepareTime = recipeTime + chefTime;
                totalAmount += recipe.price;
                DateTime orderDate = DateTime.Parse(order.orderTime);
                DateTime recipesTime = orderDate.AddMinutes(recipe.prepareTime);
                recipe.finishTime = recipesTime.ToString("HH:mm:ss");
            }
            order.totalAmount = totalAmount;
            orderList.Add(order);
            OrderData.writeData(orderList);
            return order;
        }

        /// <summary>
        /// metodo que indica si un int id se repite
        /// </summary>
        /// <param name="number"></param>
        /// <returns>booleano que indica si hay duplicado de id</returns>
        static public bool generateID(int number)
        {
            var orderList = OrderData.getOrderData();
            foreach (var order in orderList)
            {
                if (order.orderID == number)
                {
                    return false;

                }
            }
            return true;
        }

        /// <summary>
        /// metodo que genera un nuevo int aleatorio
        /// </summary>
        /// <returns>int aleatorio</returns>
        static public int randomNumber()
        {
            Random r = new Random();
            int id = r.Next(1, 10000);
            while (generateID(id)==false)
            {
                r = new Random();
                id = r.Next(1, 10000);
            }
            return id;
        }
    }
}
