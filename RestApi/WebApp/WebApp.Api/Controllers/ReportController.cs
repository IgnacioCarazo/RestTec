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
    public class ReportController : ControllerBase
    {
        /*
        List<ClientReport> creports = new List<ClientReport>() { 
            new ClientReport(){clientID=123,clientName="haziel",recipeCount=3},
        };
        
        List<FeedBack> feeds = new List<FeedBack>() { 
            new FeedBack(){feedBackDate="",clientID=12,recipeName=new List<string>(){ "Pollo","Gallo Pinto"},recipeStars=3 }
        
        };
        
        List<RecipeReport> RecipeReport = new List<RecipeReport>() {
            new RecipeReport{ recipeName="Gallo Pinto",profits=2500,sold=4,totalStars=12}
        }; 
        */

        [HttpGet("client")]
        public IActionResult clientReportGet()
        {
            var _creports = ReportData.getClientReportData();
            return Ok(_creports);
        }

        [HttpGet("profits")]
        public IActionResult recipeProfitsGets()
        {
            var _recipeReports = ReportData.getRecipeReportData();
            _recipeReports = _recipeReports.OrderByDescending(amount => amount.profits).ToList();
            return Ok(_recipeReports);
        }

        [HttpGet("sold")]
        public IActionResult recipeSoldGets()
        {
            var _recipeReports = ReportData.getRecipeReportData();
            _recipeReports = _recipeReports.OrderByDescending(amount => amount.sold).ToList();
            return Ok(_recipeReports);
        }

        [HttpPost("feedBack")]
        public IActionResult SaveFeedBack(FeedBack feedBack)
        {

            var feedBackList = ReportData.getFeedBackData();
            feedBackList.Add(feedBack);
            ReportData.writeFeedBackData(feedBackList);
            ReportData.recipeFeedBackData(feedBack);
            return Ok("FeedBack Saved");
        }

        [HttpGet("stars")]
        public IActionResult feedGets()
        {
            var _recipeReports = ReportData.getRecipeReportData();
            _recipeReports = _recipeReports.OrderByDescending(stars => stars.totalStars).ToList();
            return Ok(_recipeReports);
        }

    }
}
