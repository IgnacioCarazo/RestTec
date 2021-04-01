using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Threading.Tasks;
using WebApp.Api.Model;

namespace WebApp.Api.Data
{
    public class OrderData
    {
        static public int writeData(List<Order> orders)
        {
            string strResultJson = JsonConvert.SerializeObject(orders);
            File.WriteAllText(@"Data/order.json", strResultJson);
            return 0;
        }

        static public List<Order> getOrderData()
        {
            string readFile = File.ReadAllText(@"Data/order.json");
            var orderList = JsonConvert.DeserializeObject<List<Order>>(readFile);
            return orderList;
        }

        static public Order getOrderData(int id)
        {
            string readFile = File.ReadAllText(@"Data/order.json");
            var orderList = JsonConvert.DeserializeObject<List<Order>>(readFile);
            var _order = orderList.SingleOrDefault(x => x.orderID == id);
            return _order;
        }

        internal static bool noDuplicated(List<Order> orders, Order addOrder)
        {
            foreach (var order in orders)
            {
                if (order.orderID == addOrder.orderID)
                {
                    return false;
                }
            }
            return true;
        }

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
    }
}
