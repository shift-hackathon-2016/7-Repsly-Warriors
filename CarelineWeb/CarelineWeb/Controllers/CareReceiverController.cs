using CarelineWebAPI.Data;
using CarelineWebAPI.Filters;
using CarelineWebAPI.Helpers;
using CarelineWebAPI.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Cors;

namespace CarelineWebAPI.Controllers
{
    [EnableCors(origins: "localhost", headers: "*", methods: "*")]
    [MyBasicAuthenticationFilter]
    public class CareReceiverController : ApiController
    {
        [HttpGet]
        public CareReceiverModel GetCareReceiver(int Id)
        {
            return DBOperations.GetCareReceiver(Id, AccountContextHelper.GetContext().AccountId);
        }

        [HttpPost]
        public int SaveCareReceiver([FromBody] CareReceiverModel model)
        {
            try
            {
                return DBOperations.SaveCareReceiver(model, AccountContextHelper.GetContext().AccountId);
            }
            catch (Exception ex)
            {
                return 0;
            }
        }

        public List<CareReceiverModel> GetList()
        {
            return DBOperations.GetCareReceiverList(AccountContextHelper.GetContext().AccountId);
        }
    }
}
