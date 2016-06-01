using CarelineWebAPI.Data;
using CarelineWebAPI.Filters;
using CarelineWebAPI.Helpers;
using CarelineWebAPI.Models;
using DBLayer;
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
    public class EventsController : ApiController
    {
        public int NewTrackingEvent([FromBody] NewTrackingEventModel model)
        {
            try
            {
                DBOperations.SaveTrackingEvent(model, AccountContextHelper.GetContext().UserId, AccountContextHelper.GetContext().AccountId);
                return 1;
            }
            catch(Exception ex)
            {
                return 0;
            }
        }
    }
}
