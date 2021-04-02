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
    public class ClientController : ControllerBase
    {
        //get de todos los clientes que existen
        [HttpGet]
        public IActionResult Gets()
        {
            var _clients = ClientData.getClientData();
            return Ok(_clients);
        }

        //get de un cliente especifico segun el ID 
        [HttpGet("{id}")]
        public IActionResult Get(int id)
        {
            var _client = ClientData.getClientData(id);
            if (_client == null)
            {
                return NotFound("No client found.");
            }
            return Ok(_client);
        }

        //metodo para verificar el acceso de un cliente
        [HttpGet("login/{email}/{password}")]
        public IActionResult Autorization(string email, string password)
        {
            var _client = ClientData.getLoginData(email, password);
            if (_client == null)
            {
                return NotFound("No client found or Access denied");
            }
            return Ok("Access granted");
        }

        //guarda un nuevo cliente
        [HttpPost("newClient")]
        public IActionResult SaveClient(Client client)
        {
            var _clients = ClientData.getClientData();
            if (ClientData.noDuplicated(_clients,client)==false)
            {
                return NotFound("Email or ID already exists");
            }
            _clients.Add(client);
            if (_clients.Count == 0)
            {
                return NotFound("No List Found.");
            }
            return Ok("Successful Registration");
        }
        
       //metodo para eliminar un cliente segun su id
        [HttpDelete("delete/{id}")]
        public IActionResult DeleteClient(int id)
        {
            var client = ClientData.getClientData(id);
            if (client == null)
            {
                return NotFound("No client found.");
            }
            var _clients = ClientData.deleteData(id);
            if (_clients.Count == 0)
            {
                return NotFound("No list found");
            }
            return Ok("Cliente Deleted Successully");
        }
    }
}

