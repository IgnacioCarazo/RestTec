using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebApp.Api.Model
{
    /// <summary>
    /// clase que modela la data de los feedback
    /// </summary>
    public class FeedBack
    {
        //lista de strings que indican el nombre de los platos a los cuales se les hace feedback
        public List<string> recipeName { get; set; }
        //int que indica la cantidad de estrellas que tiene cada pedido segu el feedback
        public int recipeStars { get; set; } = 0;
        //string que indica la fecha en la que realizo el feedback
        public string feedBackDate { get; set; } = "";
        // int que indica el id del cliente que genero el feedback
        public int clientID { get; set; }
    }
}
