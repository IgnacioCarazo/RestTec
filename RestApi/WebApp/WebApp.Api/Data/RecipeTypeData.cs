using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Threading.Tasks;
using WebApp.Api.Model;

namespace WebApp.Api.Data
{
    public class RecipeTypeData
    {

        static public int writeData(List<RecipeType> recipeTypeList)
        {
            string strResultJson = JsonConvert.SerializeObject(recipeTypeList);
            File.WriteAllText(@"Data/recipeType.json", strResultJson);
            return 0;
        }
       
        static public List<RecipeType> getData()
        {
            string readFile = File.ReadAllText(@"Data/recipeType.json");
            var typeList = JsonConvert.DeserializeObject<List<RecipeType>>(readFile);
            return typeList;
        }

        static public RecipeType getTypeData(string name)
        {
            string readFile = File.ReadAllText(@"Data/recipeType.json");
            var typeList = JsonConvert.DeserializeObject<List<RecipeType>>(readFile);
            var _type = typeList.SingleOrDefault(x => x.name == name);
            return _type;
        }

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
