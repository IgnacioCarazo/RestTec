using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApp.Api.Data;
using WebApp.Api.Model;

/// <summary>
/// Controllador que maneja las peticiones y respuestas sobre los Reportes
/// </summary>
namespace WebApp.Api.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ReportController : ControllerBase
    {
        /// <summary>
        /// obtiene lista de clientes con mas pedidos
        /// </summary>
        /// <returns>lista de ClientReport en formato json</returns>
        [HttpGet("client")]
        public IActionResult clientReportGet()
        {
            var _creports = ReportData.getClientReportData();
            return Ok(_creports);
        }

        /// <summary>
        /// obtiene lista de pedidos con mas ganancias
        /// </summary>
        /// <returns>lista de RecipeReport en formato json</returns>
        [HttpGet("profits")]
        public IActionResult recipeProfitsGets()
        {
            var _recipeReports = ReportData.getRecipeReportData();
            _recipeReports = _recipeReports.OrderByDescending(amount => amount.profits).ToList();
            return Ok(_recipeReports);
        }

        /// <summary>
        /// obtiene lista de pedidos con mas Ventas
        /// </summary>
        /// <returns>lista de RecipeReport en formato json</returns>
        [HttpGet("sold")]
        public IActionResult recipeSoldGets()
        {
            var _recipeReports = ReportData.getRecipeReportData();
            _recipeReports = _recipeReports.OrderByDescending(amount => amount.sold).ToList();
            return Ok(_recipeReports);
        }

        /// <summary>
        /// metodo para agregar un nuevo feedback a la base de datos
        /// </summary>
        /// <param name="feedBack">feedBack que se desea agregar</param>
        /// <returns>string que indica si se puedo guardar el feedback</returns>
        [HttpPost("feedBack")]
        public IActionResult SaveFeedBack(FeedBack feedBack)
        {

            var feedBackList = ReportData.getFeedBackData();
            feedBackList.Add(feedBack);
            ReportData.writeFeedBackData(feedBackList);
            ReportData.recipeFeedBackData(feedBack);
            return Ok("FeedBack Saved");
        }

        /// <summary>
        /// metodo para obtener lista de pedidos con mejores feedbacksM
        /// </summary>
        /// <returns>lista de RecipeReport en formato json </returns>
        [HttpGet("stars")]
        public IActionResult feedGets()
        {
            var _recipeReports = ReportData.getRecipeReportData();
            _recipeReports = _recipeReports.OrderByDescending(stars => stars.totalStars).ToList();
            return Ok(_recipeReports);
        }

    }
}
