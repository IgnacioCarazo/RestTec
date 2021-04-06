using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApp.Api.Data;
using WebApp.Api.Model;

/// <summary>
/// Clase que maneja las peticiones y respuestas de los Clientes
/// </summary>
namespace WebApp.Api.Controllers
{
    [Route("api/[controller]")]
    [ApiController]

    
    
    public class ClientController : ControllerBase
    {
      
        /// <summary>
        /// get de todos los clientes que existen
        /// </summary>
        /// <returns> Devuelve lista de clientes en formato json </returns>
        [HttpGet]
        public IActionResult Gets()
        {
            var _clients = ClientData.getClientData();
            return Ok(_clients);
        }

        //get de un cliente especifico segun el ID 
        
        /// <summary>
        /// Obtiene un cliente especifico segun su ID
        /// </summary>
        /// <param name="id"> int que indica el id del cliente que se desea buscar </param>
        /// <returns> Objeto cliente en formato json </returns>
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
        /// <summary>
        /// metodo para verificar el acceso de un cliente
        /// </summary>
        /// <param name="email">string que indica el email del cliente</param>
        /// <param name="password">string que indica la contrasena del cliente</param>
        /// <returns>Cliente en formato json el cual tiene correo y contrasena enviados y estos coiciden</returns>
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

        /// <summary>
        /// guarda un nuevo cliente
        /// </summary>
        /// <param name="client">Cliente que se desea guarda en la base de datos</param>
        /// <returns>retorna el cliente guardo en formato json</returns>
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
            ClientData.writeData(_clients);
            return Ok(client);
        }

        /// <summary>
        /// metodo para eliminar un cliente segun su id
        /// </summary>
        /// <param name="id">int que indica el id del cliente que desea eliminar de la base de datos</param>
        /// <returns>string que indica que el cliente ha sido eliminado</returns>
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

        /// <summary>
        /// metodo para editar un client existente
        /// </summary>
        /// <param name="client">client nuevo</param>
        /// <returns>string que indica el cambio</returns>
        [HttpPut("update")]
        public IActionResult updateClient(Client client)
        {
            var clientList = ClientData.updateClient(client);
            ClientData.writeData(clientList);
            return Ok("Client Updated");
        }
    }
}

