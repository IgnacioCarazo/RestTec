using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebApp.Api.Model
{
    public class Recipe
    {
        //indica el nombre del plato
        public string recipeName { get; set; }
        //preio del plato
        public int price { get; set; }
        //cantidad de calorias del plato
        public int calories { get; set; }
        //tiempo de preparacion
        public int prepareTime { get; set; }
        //lista de los ingredientes basicos 
        public List<Ingredient> ingredients { get; set; }
        //hora estimada de finalizacion
        public string finishTime { get; set; }
        //tipo plato
        public RecipeType type { get; set; }
    }
}
