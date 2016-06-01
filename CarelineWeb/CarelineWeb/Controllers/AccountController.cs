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
    public class AccountController : ApiController
    {
        [MyBasicAuthenticationFilter]
        [HttpGet]
        public UserModel MobileGetUserData()
        {
            return DBOperations.GetUserDataByUserID(AccountContextHelper.GetContext().UserId);
        }

        public int Registration([FromBody] RegistrationModel model)
        {
            try
            {
                return DBOperations.RegisterAccount(model);
            }
            catch(Exception ex)
            {
                return 0;
            }
        }

        public UserModel SignIn(LoginModel model)
        {
            return DBOperations.GetUserByUsernamePassword(model.username, model.password);
        }
    }
}
