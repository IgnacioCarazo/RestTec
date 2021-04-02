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
        /*
        List<Client> clients = new List<Client>()
        {
            new Client(){name="Andres",primaryLastName="Barrantes",secondLastName="Herrera",iD=123,email="andres@gmail.com",password="andres123",
                province="San Josee",celNum=8854,birthday="26/11/1996",canton="Central",district="Pavas" },
            new Client(){name="Gerardo",primaryLastName="Barboza",secondLastName="Leon",iD=345,email="gerardo@gmail.com",password="gerardo345",
                province="Cartado",celNum=8856,birthday="23/03/2001",canton="Central",district="Tres Rios" },
            new Client(){name="Veronica",primaryLastName="Pacheco",secondLastName="Venegas",iD=567,email="veronica@gmail.com",password="veronica567",
                province="Heredia",celNum=89875,birthday="3/05/1999",canton="Central",district="Heredia" },
            new Client(){name="Lucia",primaryLastName="Prada",secondLastName="Gucci",iD=789,email="lucias@gmail.com",password="lucia789",
                province="Alajuela",celNum=456576,birthday="9/2/2004",canton="Central",district="Trejos" }
        };
        */

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
            return Ok(_client);
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
            return Ok(client);
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

