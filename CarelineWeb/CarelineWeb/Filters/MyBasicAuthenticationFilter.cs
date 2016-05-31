using CarelineWebAPI.Helpers;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Http.Controllers;

namespace CarelineWebAPI.Filters
{
    public class MyBasicAuthenticationFilter : BasicAuthenticationFilter
    {

        public MyBasicAuthenticationFilter()
        { }

        public MyBasicAuthenticationFilter(bool active)
            : base(active)
        { }


        protected override bool OnAuthorizeUser(string username, string password, HttpActionContext actionContext)
        {
            if (RequestValidationHelper.ValidateUser(username, password))
            {
                return true;
            }

            Challenge(actionContext);
            return false;
        }
    }
}