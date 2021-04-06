using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApp.Api.Data;
using WebApp.Api.Model;

/// <summary>
/// controlador que maneja las peticiones de los usuarios
/// </summary>
namespace WebApp.Api.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class UserController : ControllerBase
    {
        /// <summary>
        /// metodo para verificar si el usuario es un chef y si los credenciales coinciden
        /// </summary>
        /// <param name="email">string email del chef</param>
        /// <param name="password">string contresena del email</param>
        /// <returns>User en formato json</returns>
        [HttpGet("chef/{email}/{password}")]
        public IActionResult chefAutorization(string email, string password)
        {
            var _user = UserData.chefLogin(email, password);
            if (_user == null)
            {
                _user = new User() { name = "", email = "", access = false, credentials = false, password = "" };
            }
            return Ok(_user);
        }
        
        /// <summary>
        /// metodo para verificar si el usuario es un admin y si los credenciales coinciden
        /// </summary>
        /// <param name="email">string del email del usuario admin</param>
        /// <param name="password">contrasena del email admin</param>
        /// <returns>User en formato json</returns>
        [HttpGet("admin/{email}/{password}")]
        public IActionResult adminAutorization(string email, string password)
        {
            var _user = UserData.adminLogin(email, password);
            if (_user == null)
            {
                _user = new User() {name="",email = "", access = false, credentials = false, password = "" };
            }
            return Ok(_user);
        }
        
    }
        
}
