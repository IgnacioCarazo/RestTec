using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApp.Api.Data;
using WebApp.Api.Model;

namespace WebApp.Api.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class OrderController : ControllerBase
    {
        /*
        static Ingredient onion = new Ingredient { name = "onion", amount = 3 };
        static Ingredient tomatoe = new Ingredient { name = "tomatoe", amount = 4 };
        static Ingredient rice = new Ingredient { name = "rice", amount = 1 };
        static Ingredient egg = new Ingredient { name = "egg", amount = 2 };
        static Ingredient bean = new Ingredient { name = "bean", amount = 1 };
        static Ingredient bacon = new Ingredient { name = "baron", amount = 5 };
        static Ingredient steak = new Ingredient { name = "steak", amount = 1 };
        static Ingredient chicken = new Ingredient { name = "chicken", amount = 2 };

        static RecipeType breakfast = new RecipeType { description = "Disponible de 7:00 am hasta 10:00 am", name = "desayuno" };
        static RecipeType lunch = new RecipeType { description = "Disponible de 11:00 am hasta 1:00 pm", name = "almuerzo" };
        static RecipeType dinner = new RecipeType { description = "Disponible de 3:00 pm hasta 7:00 am", name = "cena" };

        static Recipe gallopinto = new Recipe() { recipeName = "Gallo Pinto",price = 2200,calories = 300,finishTime = "10:00",
                ingredients = new List<Ingredient> { onion, rice, bean, egg, bacon },prepareTime = -1,type = breakfast,
                imagePath = "https://www.recetascostarica.com/base/stock/Post/2-image/2-image_web.jpg0"};
        static Recipe casado = new Recipe() { recipeName = "Casado",price = 3400,calories = 700,finishTime = "12:00",
                ingredients = new List<Ingredient> { rice, bean, steak, tomatoe },prepareTime = -1,type = lunch,
             imagePath = "https://i.pinimg.com/originals/15/c2/24/15c22439b29413b081b8e9f459f7a354.jpg"};
        static Recipe arrozPollo = new Recipe() { recipeName = "Arroz con Pollo",price = 2200,calories = 300,finishTime = "4:00",
                ingredients = new List<Ingredient> { chicken, rice, tomatoe },prepareTime = -1,type = dinner,
              imagePath = "https://images-gmi-pmc.edge-generalmills.com/8f518e2c-ad62-4480-b6e1-cdf10a9f6c08.jpg"};

        List<Order> orders = new List<Order>() {
            new Order(){
                orderID=123,userID=432,totalAmount=0,orderTime="",recipeIncluded=new List<Recipe>{casado},assigned=false,chefName="",date=""
            },
            new Order(){
                orderID=456,userID=4645,totalAmount=0,orderTime="",recipeIncluded=new List<Recipe>{arrozPollo,gallopinto},assigned=false,chefName="",date=""
            },
            new Order(){
                orderID=789,userID=5453,totalAmount=0,orderTime="",recipeIncluded=new List<Recipe>{arrozPollo,casado,gallopinto},assigned=false,chefName="",date=""
            }
        };
        
        */

        //get de todos los pedidos que existen
        [HttpGet]
        public IActionResult Gets()
        {
            var _orders = OrderData.getOrderData();
            return Ok(_orders);
        }
        

        [HttpGet("assign/{chef}/{orderID}")]
        public IActionResult assingOrder(string chef, int orderID)
        {
            var order = OrderData.manageOrder(chef,orderID);
            return Ok(order);
        }
        
        //get de un pedido especifico segun el ID
        [HttpGet("{id}")]
        public IActionResult Get(int id)
        {
            var _order = OrderData.getOrderData(id);
            if (_order == null)
            {
                return NotFound("No order found.");
            }
            return Ok(_order);
        }
        
        
        //guarda un nuevo pedido
        [HttpPost("addOrder")]
        public IActionResult SaveOrder(Order _order)
        {
            var orders = OrderData.getOrderData();
            if (OrderData.noDuplicated(orders,_order) == false)
            {
                return NotFound("OrderID already exists");
            }
            DateTime actualTime = DateTime.Now;
            _order.date = actualTime.ToString("dd/MM/yyyy");
            _order.orderTime = actualTime.ToString("HH:mm:ss");
            orders.Add(_order);
            if (orders.Count == 0)
            {
                return NotFound("No List Found.");
            }

            OrderData.writeData(orders);
            return Ok("Order Added Successfully");
        }
        
        //elimina un pedido segun su numero de orden
        [HttpDelete("delete/{id}")]
        public IActionResult DeleteOrder(int id)
        {
            var order = OrderData.getOrderData(id);
            if (order == null)
            {
                return NotFound("No order found.");
            }
            var orders = OrderData.deleteData(id);
            if (orders.Count == 0)
            {
                return NotFound("No list found");
            }
            return Ok("Order Finished");
        }
        
    }

    
}
 