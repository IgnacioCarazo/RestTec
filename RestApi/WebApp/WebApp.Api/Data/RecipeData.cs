using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Threading.Tasks;
using WebApp.Api.Model;

/// <summary>
/// clase que maneja toda la data de los platos
/// </summary>
namespace WebApp.Api.Data
{
    public class RecipeData
    {
        /// <summary>
        /// metodo para modificar los datos de las recetas 
        /// </summary>
        /// <param name="recipes">lista de recipe a agregar</param>
        /// <returns>int siempre 0</returns>
        static public int writeData(List<Recipe> recipes)
        {
            string strResultJson = JsonConvert.SerializeObject(recipes);
            File.WriteAllText(@"Data/recipe.json", strResultJson);
            return 0;
        }

        /// <summary>
        /// metodo para obtener todos los platos existentes
        /// </summary>
        /// <returns>lista Recipe</returns>
        static public List<Recipe> getRecipeData()
        {
            string readFile = File.ReadAllText(@"Data/recipe.json");
            var recipeList = JsonConvert.DeserializeObject<List<Recipe>>(readFile);
            return recipeList;
        }

        /// <summary>
        /// metodo para obtener un plato especifico segun su nombre
        /// </summary>
        /// <param name="name">string de la Recipe a obtener</param>
        /// <returns>Recipe que se busca</returns>
        static public Recipe getRecipeData(string name)
        {
            string readFile = File.ReadAllText(@"Data/recipe.json");
            var recipeList = JsonConvert.DeserializeObject<List<Recipe>>(readFile);
            var _recipe = recipeList.SingleOrDefault(x => x.recipeName == name);
            return _recipe;
        }

        /// <summary>
        /// metodo para verificar que no se repitan recetas
        /// </summary>
        /// <param name="recipeList">lista de Recipe</param>
        /// <param name="addRecipe">Recipe que se desea agregar</param>
        /// <returns>booleano que indica si se repite o no</returns>
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

        /// <summary>
        /// metodo para eliminar datos de un plato
        /// </summary>
        /// <param name="name">string del Recipe a eliminar </param>
        /// <returns>nueva lista de Recipe</returns>
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
