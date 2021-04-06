using Microsoft.AspNetCore.Cors;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApp.Api.Data;
using WebApp.Api.Model;

/// <summary>
/// Controlador que maneja todas las peticions y respuestas solo platillos
/// </summary>
namespace WebApp.Api.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class RecipeController : ControllerBase
    {
        /// <summary>
        /// obtiene lista de todos los platos que existen
        /// </summary>
        /// <returns>Lista de Recipe en formato json</returns>
        [HttpGet]
        public IActionResult Gets()
        {
            var _recipes = RecipeData.getRecipeData();
            return Ok(_recipes);
        }


        /// <summary>
        /// obtiene un plato especifico segun su nombre
        /// </summary>
        /// <param name="name">string que indica el nombre de la receta que se desea obtener</param>
        /// <returns>Recipe en formato json</returns>
        [HttpGet("{name}")]
        public IActionResult Get(string name)
        {
            var recipe = RecipeData.getRecipeData(name);
            if (recipe == null)
            {
                return NotFound("No recipe found.");
            }
            return Ok(recipe);
        }

        /// <summary>
        /// guarda un nuevo plato
        /// </summary>
        /// <param name="recipe">Recipe que se desea guardar</param>
        /// <returns>lista de Recipe en formato json</returns>
        [HttpPost("addRecipe")]
        public IActionResult SaveRecipe(Recipe recipe)
        {
            var _recipes = RecipeData.getRecipeData();
            if (RecipeData.noDuplicated(_recipes, recipe) == false)
            {
                return NotFound("Recipe already exists");
            }
            _recipes.Add(recipe);
            if (_recipes.Count == 0)
            {
                return NotFound("No List Found.");
            }
            RecipeData.writeData(_recipes);
            return Ok(_recipes);
        }


        /// <summary>
        /// elimina un plato segun su nombre
        /// </summary>
        /// <param name="name">string que indica el nombre del plato que se desea eliminar</param>
        /// <returns>nueva lista de Recipe en formato json</returns>
        [HttpDelete("delete/{name}")]
        public IActionResult DeleteRecipe(string name)
        {
            var recipe = RecipeData.getRecipeData(name);
            if (recipe == null)
            {
                return NotFound("No recipe found.");
            }
            var _recipes = RecipeData.deleteData(name);
            if (_recipes.Count == 0)
            {
                return NotFound("No list found");
            }
            RecipeData.writeData(_recipes);
            return Ok(_recipes);
        }

        /// <summary>
        /// metodo para actualizar una receta
        /// </summary>
        /// <param name="recipeList">string que indica el nombre del plato que se desea actualizar</param>
        /// <returns>nuesta lista de Recipe en formato json</returns>
        [HttpPut("updateRecipe")]
        public IActionResult updateRecipe(List<Recipe> recipeList)
        {
            if (recipeList.Count == 0 || recipeList==null)
            {
                return NotFound("Error List");
            }
            RecipeData.writeData(recipeList);
            return Ok(recipeList);
        }
    }
}
