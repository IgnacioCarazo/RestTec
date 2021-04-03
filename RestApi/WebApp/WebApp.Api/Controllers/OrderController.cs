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
        //get de todos los pedidos que existen
        [HttpGet]
        public IActionResult Gets()
        {
            var _orders = OrderData.getOrderData();
            return Ok(_orders);
        }
        
        //get de un pedido especifico segun el ID del cliente
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
 