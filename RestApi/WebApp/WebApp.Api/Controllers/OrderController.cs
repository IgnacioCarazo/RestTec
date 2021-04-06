using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApp.Api.Data;
using WebApp.Api.Model;

/// <summary>
/// Controlador que maneja peticiones y respuestas de pedidos
/// </summary>
namespace WebApp.Api.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class OrderController : ControllerBase
    {

        /// <summary>
        /// Metodo para obtener todos pedidos guardos en la base de datos
        /// </summary>
        /// <returns> devuelve lista List<Order> en formato json</returns>
        [HttpGet]
        public IActionResult Gets()
        {
            var _orders = OrderData.getOrderData();
            return Ok(_orders);
        }
        
        /// <summary>
        /// Metodo para asignar un chef a un pedido especifico
        /// </summary>
        /// <param name="chef">string que indica nombre del chef que toma el pedido</param>
        /// <param name="orderID">int que indica el id de la orden a tomar</param>
        /// <returns>orden tomada por el chef en formato json</returns>
        [HttpGet("assign/{chef}/{orderID}")]
        public IActionResult assingOrder(string chef, int orderID)
        {
            var order = OrderData.manageOrder(chef,orderID);
            return Ok(order);
        }

        /// <summary>
        /// get de un pedido especifico segun el ID
        /// </summary>
        /// <param name="id">int id que indica cual pedido se quiere pedir</param>
        /// <returns>perdido con el id respectivo</returns>
        [HttpGet("{id}")]
        public IActionResult Get(int id)
        {
            var _order = OrderData.getOrderData(id);
            if(_order == null)
            {
                return NotFound("Null Order");
            }
            return Ok(_order);
        }

        /// <summary>
        /// Metodo para verificar si una orden ha sido tomada por un chef
        /// </summary>
        /// <param name="id">int que indica el id de la orden a buscar</param>
        /// <returns>string que indica si un pedido ha sido tomado o no</returns>
        [HttpGet("verify/{id}")]
        public IActionResult verifyOrder(int id)
        {
            var _order = OrderData.getOrderData(id);
            if (_order.assigned == false)
            {
                return Ok("False");
            }
            return Ok("True");
        }


        /// <summary>
        /// metodo que guarda un nuevo pedido
        /// </summary>
        /// <param name="_order">orden que se desea guardar en la base de datos</param>
        /// <returns>Order en formato json</returns>
        [HttpPost("addOrder")]
        public IActionResult SaveOrder(Order _order)
        {
            var orders = OrderData.getOrderData();
            DateTime actualTime = DateTime.Now;
            _order.date = actualTime.ToString("dd/MM/yyyy");
            _order.orderTime = actualTime.ToString("HH:mm:ss");
            _order.orderID = OrderData.randomNumber();
            orders.Add(_order);
            OrderData.writeData(orders);
            ReportData.clientReportData(_order.userID, _order.recipeIncluded.Count);
            ReportData.recipeReportData(_order.recipeIncluded);
            return Ok(_order);
        }

        /// <summary>
        /// elimina un pedido segun su numero de orden
        /// </summary>
        /// <param name="id">int del pedido que se quiere eliminar</param>
        /// <returns>string que indica si se elimino el pedido o no</returns>
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
 