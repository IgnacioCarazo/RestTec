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
    public class RecipeTypeController : ControllerBase
    {

         List<RecipeType> _types = new List<RecipeType>()
        {
            new RecipeType(){name="Desayuno",description="Disponible de 7:00 am a 10:00 am"},
            new RecipeType(){name="Almuerzo",description="Disponible de 11:00 am a 1:00 pm"},
            new RecipeType(){name="Cena",description="Disponible de 3:00 pm a 7:00 pm"}
        };

        [HttpGet]
        public IActionResult Gets()
        {
            var types = RecipeTypeData.getData();
            return Ok(types);
        }

        [HttpPost("newType")]
        public IActionResult SaveType(RecipeType type)
        {
            var _types = RecipeTypeData.getData();
            _types.Add(type);
            if (_types.Count == 0)
            {
                return NotFound("No List Found.");
            }
            RecipeTypeData.writeData(_types);
            return Ok(_types);
        }

        [HttpPut("updateType")]
        public IActionResult updateType(List<RecipeType> typeList)
        {
            if (typeList.Count == 0 || typeList == null)
            {
                return NotFound("Error List");
            }
            RecipeTypeData.writeData(typeList);
            return Ok(typeList);
        }

        [HttpDelete("delete/{name}")]
        public IActionResult DeleteType(string name)
        {
            var type = RecipeTypeData.getTypeData(name);
            if (type == null)
            {
                return NotFound("No Type found.");
            }
            var _types = RecipeTypeData.deleteData(name);
            if (_types.Count == 0)
            {
                return NotFound("No list found");
            }
            return Ok(_types);
        }

    }
}
