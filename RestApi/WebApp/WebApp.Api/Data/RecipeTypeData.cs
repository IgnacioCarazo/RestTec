using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Threading.Tasks;
using WebApp.Api.Model;

/// <summary>
/// clase que maneja toda la data de RecipeType
/// </summary>
namespace WebApp.Api.Data
{
    public class RecipeTypeData
    {
        /// <summary>
        /// metodo para 
        /// </summary>
        /// <param name="recipeTypeList">lista de RecipeType que se desea agregar la base de datos</param>
        /// <returns>int siempre 0</returns>
        static public int writeData(List<RecipeType> recipeTypeList)
        {
            string strResultJson = JsonConvert.SerializeObject(recipeTypeList);
            File.WriteAllText(@"Data/recipeType.json", strResultJson);
            return 0;
        }
       
        /// <summary>
        /// Metodo para obtener todos los tipos de plato existentes
        /// </summary>
        /// <returns>Lista de RecipeType </returns>
        static public List<RecipeType> getData()
        {
            string readFile = File.ReadAllText(@"Data/recipeType.json");
            var typeList = JsonConvert.DeserializeObject<List<RecipeType>>(readFile);
            return typeList;
        }

        /// <summary>
        /// metodo para obtener un RecipeType especifico
        /// </summary>
        /// <param name="name">string del RecipeType a buscar</param>
        /// <returns>RecipeType</returns>
        static public RecipeType getTypeData(string name)
        {
            string readFile = File.ReadAllText(@"Data/recipeType.json");
            var typeList = JsonConvert.DeserializeObject<List<RecipeType>>(readFile);
            var _type = typeList.SingleOrDefault(x => x.name == name);
            return _type;
        }

        /// <summary>
        /// metodo para eliminar un RecipeType
        /// </summary>
        /// <param name="name">string del RecipeType a eliminar</param>
        /// <returns>nueva lista de RecipeType</returns>
        static public List<RecipeType> deleteData(string name)
        {
            string readFile = File.ReadAllText(@"Data/recipeType.json");
            var typeList = JsonConvert.DeserializeObject<List<RecipeType>>(readFile);
            var type = typeList.SingleOrDefault(x => x.name == name);
            typeList.Remove(type);
            string strResultJson = JsonConvert.SerializeObject(typeList);
            File.WriteAllText(@"Data/recipeType.json", strResultJson);
            return typeList;
        }
    }
}
