using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Threading.Tasks;
using WebApp.Api.Model;

/// <summary>
/// clase que mmaneja toda la dta de los usuario admin y chef
/// </summary>
namespace WebApp.Api.Data
{
    public class UserData
    {
        /// <summary>
        /// metodo para escribir informacion sobre usuarios en la base de datos
        /// </summary>
        /// <param name="users">lista de User que se desea agregar</param>
        /// <returns>int siempre 0</returns>
        static public int writeData(List<User> users)
        {
            string strResultJson = JsonConvert.SerializeObject(users);
            File.WriteAllText(@"Data/user.json", strResultJson);
            return 0;
        }
        
        /// <summary>
        /// metodo para verificar si coinciden los credenciales de un chef
        /// </summary>
        /// <param name="email">string email del chef</param>
        /// <param name="password">strig contrasena del chef</param>
        /// <returns>User</returns>
        static public User chefLogin(string email, string password)
        {
            string readFile = File.ReadAllText(@"Data/user.json");
            var userList = JsonConvert.DeserializeObject<List<User>>(readFile);
            var _user = userList.SingleOrDefault(x => x.email == email);
            if (_user == null)
            {
                return _user;
            }
            if (_user.password != password || _user.access==true)
            {
                _user = null;
            }
            return _user;
        }

        /// <summary>
        /// metodo para verificar si coinciden los credenciales de un admin
        /// </summary>
        /// <param name="email">string email del amdmin</param>
        /// <param name="password">strig contrasena del admin</param>
        /// <returns>User</returns>
        static public User adminLogin(string email, string password)
        {
            string readFile = File.ReadAllText(@"Data/user.json");
            var userList = JsonConvert.DeserializeObject<List<User>>(readFile);
            var _user = userList.SingleOrDefault(x => x.email == email);
            if (_user == null)
            {
                return _user;
            }
            if (_user.password != password || _user.access == false)
            {
                _user = null;
            }
            return _user;
        }
    }
}
