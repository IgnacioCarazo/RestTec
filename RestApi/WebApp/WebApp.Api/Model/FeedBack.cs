using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebApp.Api.Model
{
    public class FeedBack
    {
        public List<string> recipeName { get; set; }
        public int recipeStars { get; set; } = 0;
        public string feedBackDate { get; set; } = "";
        public int clientID { get; set; }
    }
}
