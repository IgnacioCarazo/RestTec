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
    public class RecipeController : ControllerBase
    {

        /*
        static Ingredient onion = new Ingredient { name = "onion", amount = 3 };
        static Ingredient tomatoe = new Ingredient { name = "tomatoe", amount = 4 };
        static Ingredient rice = new Ingredient { name = "rice", amount = 1 };
        static Ingredient egg = new Ingredient { name = "egg", amount = 2 };
        static Ingredient bean = new Ingredient { name = "bean", amount = 1 };
        static Ingredient bacon = new Ingredient { name = "baron", amount = 5 };
        static Ingredient steak = new Ingredient { name = "steak", amount = 1 };
        static Ingredient chicken = new Ingredient { name = "chicken", amount = 2 };

        static RecipeType breakfast = new RecipeType { description = "Disponible de 7:00 am hasta 10:00 am", name = "desayuno" };
        static RecipeType lunch = new RecipeType { description = "Disponible de 11:00 am hasta 1:00 pm", name = "almuerzo" };
        static RecipeType dinner = new RecipeType { description = "Disponible de 3:00 pm hasta 7:00 am", name = "cena" };
       

        List<Recipe> recipes = new List<Recipe>() {
            new Recipe(){recipeName="Gallo Pinto",price=2200,calories=300,finishTime="10:00",
                ingredients=new List<Ingredient>{onion,rice,bean,egg,bacon},prepareTime=-1,type=breakfast,
                imagePath="https://www.recetascostarica.com/base/stock/Post/2-image/2-image_web.jpg0"},
             new Recipe(){recipeName="Casado",price=3400,calories=700,finishTime="12:00",
                ingredients=new List<Ingredient>{rice,bean,steak,tomatoe},prepareTime=-1,type=lunch,
             imagePath="https://i.pinimg.com/originals/15/c2/24/15c22439b29413b081b8e9f459f7a354.jpg"},
              new Recipe(){recipeName="Arroz con Pollo",price=2200,calories=300,finishTime="4:00",
                ingredients=new List<Ingredient>{chicken,rice,tomatoe},prepareTime=-1,type=dinner,
              imagePath="https://images-gmi-pmc.edge-generalmills.com/8f518e2c-ad62-4480-b6e1-cdf10a9f6c08.jpg"},
        };
        
        */

        //obtiene lista de todos los platos que existen
        [HttpGet]
        public IActionResult Gets()
        {
            var _recipes = RecipeData.getRecipeData();
            return Ok(_recipes);
        }


        //obtiene un plato especifico segun su nombre
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

        //guarda un nuevo plato
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


        //elimina un plato segun su nombre
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

        //metodo para actualizar una receta
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
