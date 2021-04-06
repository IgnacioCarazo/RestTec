using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Threading.Tasks;
using WebApp.Api.Model;

/// <summary>
/// Clase que maneja toda la data de los clientes
/// </summary>
namespace WebApp.Api.Data
{
    public class ClientData
    {
        /// <summary>
        /// metodo para modificar (agregar/eliminar/actualizar) los datos de los clientes
        /// </summary>
        /// <param name="clients">Lista de Client que se desea escribir en la base de datos</param>
        /// <returns>int siempre 0</returns>
        static public int writeData(List<Client> clients)
        {
            string strResultJson = JsonConvert.SerializeObject(clients);
            File.WriteAllText(@"Data/client.json", strResultJson);
            return 0;
        }
        /// <summary>
        /// metodo que obtiene la data guardada de un cliente utilizando su ID
        /// </summary>
        /// <param name="id">int que indica el id de cliente</param>
        /// <returns>Client en formato json</returns>
        static public Client getClientData(int id)
        {
            string readFile = File.ReadAllText(@"Data/client.json");
            var clientList = JsonConvert.DeserializeObject<List<Client>>(readFile);
            var _client = clientList.SingleOrDefault(x => x.iD == id);
            return _client;
        }

        /// <summary>
        /// metodo para obtener todos los clientes registrados
        /// </summary>
        /// <returns>Lista de Client en la base de datos</returns>
        static public List<Client> getClientData()
        {
            string readFile = File.ReadAllText(@"Data/client.json");
            var clientList = JsonConvert.DeserializeObject<List<Client>>(readFile);
            return clientList;
        }

        /// <summary>
        /// metodo para comprobar si la contrasena y el email brindados por un cliente es correcto
        /// </summary>
        /// <param name="email">string email del cliente que se desea comprobar</param>
        /// <param name="password">string contrasena del cliente</param>
        /// <returns>Client en formato json</returns>
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

        /// <summary>
        /// metodo para eliminar un nuevo cliente
        /// </summary>
        /// <param name="id">int id del Cliente que se desea eliminar</param>
        /// <returns>Nueva lista de Client en formato json</returns>
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

        /// <summary>
        /// verifica que no se pueda agregar 2 usuarios con el mismo id
        /// </summary>
        /// <param name="clientList">lista de Client</param>
        /// <param name="addClient">Client cliente que sea desea agregar</param>
        /// <returns>Booleano que indica si existe duplicado o no</returns>
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
        
        /// <summary>
        /// metodo para editar informacion de un cliente existente
        /// </summary>
        /// <param name="client">cliente que se desea editar</param>
        /// <returns>lista Client</returns>
        static public List<Client> updateClient(Client client)
        {
            var clientList = getClientData();
            var _client = clientList.SingleOrDefault(x => x.iD == client.iD);
            clientList.Remove(_client);
            clientList.Add(client);
            return clientList;
        }
    }
}
