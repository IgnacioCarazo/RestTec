using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Threading.Tasks;
using WebApp.Api.Model;

namespace WebApp.Api.Data
{
    public class ClientData
    {
         //metodo para modificar (agregar/eliminar/actualizar) los datos de los clientes
        static public int writeData(List<Client> clients)
        {
            string strResultJson = JsonConvert.SerializeObject(clients);
            File.WriteAllText(@"Data/client.json", strResultJson);
            return 0;
        }
        //metodo que obtiene la data guardada de un cliente utilizando su ID
        static public Client getClientData(int id)
        {
            string readFile = File.ReadAllText(@"Data/client.json");
            var clientList = JsonConvert.DeserializeObject<List<Client>>(readFile);
            var _client = clientList.SingleOrDefault(x => x.iD == id);
            return _client;
        }
        //metodo para obtener todos los clientes registrados
        static public List<Client> getClientData()
        {
            string readFile = File.ReadAllText(@"Data/client.json");
            var clientList = JsonConvert.DeserializeObject<List<Client>>(readFile);
            return clientList;
        }
        //metodo para comprobar si la contrasena y el email brindados por un cliente es correcto
        static public Client getLoginData(string email,string password)
        {
            string readFile = File.ReadAllText(@"Data/client.json");
            var clientList = JsonConvert.DeserializeObject<List<Client>>(readFile);
            var _client = clientList.SingleOrDefault(x =>x.email == email);
            if (_client == null)
            {
                return _client;
            }
            if (_client.password != password)
            {
                _client = null;
            }
            return _client;
        }

        //metodo para eliminar un nuevo cliente
        static public List<Client> deleteData(int id)
        {
            string readFile = File.ReadAllText(@"Data/client.json");
            var clientList = JsonConvert.DeserializeObject<List<Client>>(readFile);
            var _client = clientList.SingleOrDefault(x => x.iD == id);
            clientList.Remove(_client);
            string strResultJson = JsonConvert.SerializeObject(clientList);
            File.WriteAllText(@"Data/client.json", strResultJson);
            return clientList;
        }

        //verifica que no se pueda agregar 2 usuarios con el mismo id
        static public bool noDuplicated(List<Client> clientList, Client addClient)
        {
            foreach(var client in clientList)
            {
                if(client.iD==addClient.iD || client.email == addClient.email)
                {
                    return false;
                }
            }
            return true;
        }        
    }
}
