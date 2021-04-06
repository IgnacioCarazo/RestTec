using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebApp.Api.Model
{
    /// <summary>
    /// clase que modela la data de los reportes de platos
    /// </summary>
    public class RecipeReport
    {
        //string que indica el nombre del plato en el reporte
        public string recipeName { get; set; }
        //int que indica la cantidad de ganancias que ha generado el plato
        public int profits { get; set; } = 0;
        //int que indica la cantidad de platos vendidos
        public int sold { get; set; } = 0;
        //int que indica la cantidad de estrellas de un plato obtenidadas de los feedback
        public int totalStars { get; set; } = 0;
    }
}
