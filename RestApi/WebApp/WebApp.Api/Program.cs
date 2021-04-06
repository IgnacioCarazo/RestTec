using Microsoft.AspNetCore.Hosting;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Logging;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebApp.Api
{
    public class Program
    {
        //metodo main para iniciar el programa
        public static void Main(string[] args)
        {
            CreateHostBuilder(args).Build().Run();
        }

        //metodo para crear un nuevo hostbuild y llamar a startup e iniciar todo el programa
        public static IHostBuilder CreateHostBuilder(string[] args) =>
            Host.CreateDefaultBuilder(args)
                .ConfigureWebHostDefaults(webBuilder =>
                {
                    webBuilder.UseStartup<Startup>();
                });
    }
}
