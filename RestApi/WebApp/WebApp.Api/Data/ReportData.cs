using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Threading.Tasks;
using WebApp.Api.Model;

/// <summary>
/// clase que maneja toda la data de los reportes
/// </summary>
namespace WebApp.Api.Data
{
    public class ReportData
    {
        /// <summary>
        /// metodo para escribir data del reporte de los clientes en la base de datos
        /// </summary>
        /// <param name="creportList">lista ClientReport que se desea agregar</param>
        /// <returns>int siempre 0</returns>
        static public int writeClientReportData(List<ClientReport> creportList)
        {
            string strResultJson = JsonConvert.SerializeObject(creportList);
            File.WriteAllText(@"Data/clientReport.json", strResultJson);
            return 0;
        }

        /// <summary>
        /// metodo para agregar informacion a los reportes de los clientes
        /// </summary>
        /// <param name="id">int que indica el id del cliente</param>
        /// <param name="recipeCount">cantidad de platos que ha comprado el cliente</param>
        /// <returns>ClientReport</returns>
        static public ClientReport clientReportData(int id,int recipeCount)
        {
            string readFile = File.ReadAllText(@"Data/clientReport.json");
            var creportList = JsonConvert.DeserializeObject<List<ClientReport>>(readFile);
            var creport = creportList.SingleOrDefault(x => x.clientID == id);
            if (creport == null)
            {
                creport = new ClientReport() { clientID = id, clientName = ClientData.getClientData(id).name, recipeCount = recipeCount };
                creportList.Add(creport);
                writeClientReportData(creportList);
                return creport;
            }
            creportList.Remove(creport);
            creport.recipeCount = creport.recipeCount + recipeCount;
            creportList.Add(creport);
            writeClientReportData(creportList);
            return creport;
        }

        /// <summary>
        /// metodo para obtener todos los reportes de los clientes
        /// </summary>
        /// <returns>lista de ClientReport</returns>
        static public List<ClientReport> getClientReportData()
        {
            string readFile = File.ReadAllText(@"Data/clientReport.json");
            var creportList = JsonConvert.DeserializeObject<List<ClientReport>>(readFile);
            creportList = creportList.OrderByDescending(count => count.recipeCount).ToList();
            return creportList;
        }


        /// <summary>
        /// metodo para escribir data sobre reportes de recetas
        /// </summary>
        /// <param name="recipeReports">lista de RecipeReport que se desea agregar</param>
        /// <returns>int siempre 0</returns>
        static public int writeRecipeReportData(List<RecipeReport> recipeReports)
        {
            string strResultJson = JsonConvert.SerializeObject(recipeReports);
            File.WriteAllText(@"Data/recipeReport.json", strResultJson);
            return 0;
        }

        /// <summary>
        /// metodo para agregar informacon a los reportes de platos
        /// </summary>
        /// <param name="recipeIncluded">lista de Recipe</param>
        /// <returns>lista de RecipeReport</returns>
        static public List<RecipeReport> recipeReportData(List<Recipe> recipeIncluded)
        {
            string readFile = File.ReadAllText(@"Data/recipeReport.json");
            var reports = JsonConvert.DeserializeObject<List<RecipeReport>>(readFile);
            foreach (var recipe in recipeIncluded)
            {
                var recipeReport = reports.SingleOrDefault(x => x.recipeName == recipe.recipeName);
                if (recipeReport == null)
                {
                    reports.Add(new RecipeReport() { recipeName = recipe.recipeName, profits = recipe.price, sold = 1, totalStars = 0 });
                }
                else
                {
                    reports.Remove(recipeReport);
                    recipeReport.profits = recipeReport.profits + recipe.price;
                    recipeReport.sold = recipeReport.sold + 1;
                    reports.Add(recipeReport);
                }
            }
            writeRecipeReportData(reports);
            return reports;
        }

        /// <summary>
        /// metodo para obtener reportes de platos
        /// </summary>
        /// <returns>lista de RecipeReport </returns>
        static public List<RecipeReport> getRecipeReportData()
        {
            string readFile = File.ReadAllText(@"Data/recipeReport.json");
            var reportList = JsonConvert.DeserializeObject<List<RecipeReport>>(readFile);
            return reportList;
        }

        /// <summary>
        /// metodo para escribir data de feedback en la base de datos
        /// </summary>
        /// <param name="feedbacks">lista de feedback a agregar</param>
        /// <returns>int siempre 0</returns>
        static public int writeFeedBackData(List<FeedBack> feedbacks)
        {
            string strResultJson = JsonConvert.SerializeObject(feedbacks);
            File.WriteAllText(@"Data/feedBack.json", strResultJson);
            return 0;
        }

        /// <summary>
        /// metodo para obtener informacion de los feedback
        /// </summary>
        /// <returns>lsta de FeedBack</returns>
        static public List<FeedBack> getFeedBackData()
        {
            string readFile = File.ReadAllText(@"Data/feedback.json");
            var feedbacks = JsonConvert.DeserializeObject<List<FeedBack>>(readFile);
            return feedbacks;
        }

        /// <summary>
        /// metodo para agregar informacion sobre reportes de feedback
        /// </summary>
        /// <param name="feedback">feedback que se desea obtener informacion</param>
        /// <returns>lista de ReportRecipe</returns>
        static public List<RecipeReport> recipeFeedBackData(FeedBack feedback)
        {
            var reports = getRecipeReportData();
            foreach (var report in reports)
            {
                foreach(var recipe in feedback.recipeName)
                {
                    if(recipe == report.recipeName)
                    {
                        report.totalStars = report.totalStars + feedback.recipeStars;
                    }
                }
            }
            writeRecipeReportData(reports);
            return reports;
        }
    }
}
