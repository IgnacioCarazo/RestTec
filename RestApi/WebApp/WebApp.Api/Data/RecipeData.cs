using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Threading.Tasks;
using WebApp.Api.Model;

namespace WebApp.Api.Data
{
    public class RecipeData
    {
        //metodo para modificar los datos de las recetas 
        static public int writeData(List<Recipe> recipes)
        {
            string strResultJson = JsonConvert.SerializeObject(recipes);
            File.WriteAllText(@"Data/recipe.json", strResultJson);
            return 0;
        }

        //metodo para obtener todos los platos existentes
        static public List<Recipe> getRecipeData()
        {
            string readFile = File.ReadAllText(@"Data/recipe.json");
            var recipeList = JsonConvert.DeserializeObject<List<Recipe>>(readFile);
            return recipeList;
        }

        //metodo para obtener un plato especifico segun su nombre
        static public Recipe getRecipeData(string name)
        {
            string readFile = File.ReadAllText(@"Data/recipe.json");
            var recipeList = JsonConvert.DeserializeObject<List<Recipe>>(readFile);
            var _recipe = recipeList.SingleOrDefault(x => x.recipeName == name);
            return _recipe;
        }

        //metodo para verificar que no se repitan recetas
        static public bool noDuplicated(List<Recipe> recipeList, Recipe addRecipe)
        {
            foreach (var recipe in recipeList)
            {
                if (recipe.recipeName == addRecipe.recipeName)
                {
                    return false;
                }
            }
            return true;
        }

        //metodo para eliminar datos de un plato
        static public List<Recipe> deleteData(string name)
        {
            string readFile = File.ReadAllText(@"Data/recipe.json");
            var recipeList = JsonConvert.DeserializeObject<List<Recipe>>(readFile);
            var _recipe = recipeList.SingleOrDefault(x => x.recipeName == name);
            recipeList.Remove(_recipe);
            string strResultJson = JsonConvert.SerializeObject(recipeList);
            File.WriteAllText(@"Data/recipe.json", strResultJson);
            return recipeList;
        }
    }
}
