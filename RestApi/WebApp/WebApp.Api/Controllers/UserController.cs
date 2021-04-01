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
        
        List<User> users = new List<User>() { 
            new User(){name="Haziel",email="haziel@gmail.com",password="haziel123",access=true,credentials=true},
            new User(){name="Joseph",email="joseph@gmail.com",password="joseph123",access=false},
            new User(){name="Ignacio",email="ignacio@gmai.com",password="ignacio123",access=true},
            new User(){name="Yasuo",email="yasuo@gmail.com",password="yasuo123",access=false}
        };
        
 
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
