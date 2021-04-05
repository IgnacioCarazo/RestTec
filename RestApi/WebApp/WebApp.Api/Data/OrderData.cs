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
