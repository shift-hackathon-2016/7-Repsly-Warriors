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

namespace CarelineWebAPI.Controllers
{
    [MyBasicAuthenticationFilter]
    public class ScheduleController : ApiController
    {
        public List<ScheduleModel> GetScheduleForMobileUser()
        {
            return DBOperations.GetUserScheduleList(AccountContextHelper.GetContext().UserID);
        }
    }
}
