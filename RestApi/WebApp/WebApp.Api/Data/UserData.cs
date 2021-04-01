using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Threading.Tasks;
using WebApp.Api.Model;

namespace WebApp.Api.Data
{
    public class UserData
    {

        static public int writeData(List<User> users)
        {
            string strResultJson = JsonConvert.SerializeObject(users);
            File.WriteAllText(@"Data/user.json", strResultJson);
            return 0;
        }
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
