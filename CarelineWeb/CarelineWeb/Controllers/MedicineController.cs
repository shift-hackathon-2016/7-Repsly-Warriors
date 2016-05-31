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
    public class MedicineController : ApiController
    {
        public int SaveMedicine([FromBody] MedicineModel model)
        {
            try
            {
                return DBOperations.SaveMedicine(model, AccountContextHelper.GetContext().AccountId);
            }
            catch
            {
                return 0;
            }
        }

        public MedicineModel GetMedicine(int Id)
        {
            return DBOperations.GetMedicine(Id, AccountContextHelper.GetContext().AccountId);
        }
    }
}
