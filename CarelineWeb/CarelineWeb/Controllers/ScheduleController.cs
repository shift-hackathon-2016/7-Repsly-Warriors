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
            return DBOperations.GetUserScheduleList(AccountContextHelper.GetContext().UserId);
        }

        [HttpPost]
        public int MedicineConfirmation([FromBody] MedicineConfirmationModel model)
        {
            try
            {
                DBOperations.SaveMedicineConfirmation(model, AccountContextHelper.GetContext().UserId);
                return 1;
            }
            catch (Exception ex)
            {
                return 0;
            }
        }
    }
}
