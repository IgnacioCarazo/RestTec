using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebApp.Api.Model
{
    /// <summary>
    /// clase que modela la data del tipo de plato
    /// </summary>
    public class RecipeType
    {
        //string que indica el nombre del tipo de plato
        public string name { get; set; }
        //string que indica la descripcion del tipo de plato
        public string description { get; set; }
    }
}
