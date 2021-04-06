using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApp.Api.Data;
using WebApp.Api.Model;

/// <summary>
/// Controlador que maneja las peticiones y respuestas del tipo de platillo
/// </summary>
namespace WebApp.Api.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class RecipeTypeController : ControllerBase
    {
        /// <summary>
        /// metodo para obtener todos los tipos de platillos
        /// </summary>
        /// <returns>Lista de RecipeType en formato json</returns>
        [HttpGet]
        public IActionResult Gets()
        {
            var types = RecipeTypeData.getData();
            return Ok(types);
        }

        /// <summary>
        /// metodo para agregar un nuevo tipo de platillo
        /// </summary>
        /// <param name="type">RecipeType que se desea agregar </param>
        /// <returns>nueva lista de RecipeType en formato json</returns>
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

        /// <summary>
        /// metodo para actualizar un tipo de platillo
        /// </summary>
        /// <param name="typeList">Lista RecipeType nueva para actualizar</param>
        /// <returns>Lista nueva en formato json</returns>
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

        /// <summary>
        /// metodo para eliminar un RecipeType
        /// </summary>
        /// <param name="name">nombre del tipo de platillo que se desea eliminar</param>
        /// <returns>Nueva lista de RecipeType en formato json</returns>
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
