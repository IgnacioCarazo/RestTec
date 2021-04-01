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
    public class UserController : ControllerBase
    {
 
        [HttpGet("chef/{email}/{password}")]
        public IActionResult chefAutorization(string email, string password)
        {
            var _user = UserData.chefLogin(email, password);
            if (_user == null)
            {
                return NotFound("Not found or Access denied");
            }
            return Ok("Access granted");
        }
        
        [HttpGet("admin/{email}/{password}")]
        public IActionResult adminAutorization(string email, string password)
        {
            var _user = UserData.adminLogin(email, password);
            if (_user == null)
            {
                return NotFound("Not found or Access denied");
            }
            return Ok("Access Granted");
        }
        
    }
        
}
