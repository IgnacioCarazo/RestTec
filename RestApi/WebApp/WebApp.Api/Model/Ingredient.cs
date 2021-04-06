using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebApp.Api.Model
{
    /// <summary>
    /// clase que modela la data de los ingredientes
    /// </summary>
    public class Ingredient
    {
        //string que indica el nombre del ingrediente
        public string name { get; set; }
        //int que indica la cantidad del ingrediente
        public int amount { get; set; }

    }
}
