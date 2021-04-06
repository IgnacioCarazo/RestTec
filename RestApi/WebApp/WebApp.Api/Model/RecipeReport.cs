using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebApp.Api.Model
{
    public class RecipeReport
    {
        public string recipeName { get; set; }
        public int profits { get; set; } = 0;
        public int sold { get; set; } = 0;
        public int totalStars { get; set; } = 0;
    }
}
