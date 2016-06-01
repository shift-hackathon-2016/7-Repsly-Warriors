using CarelineWebAPI.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace CarelineWebAPI.Helpers
{
    public class AccountContextHelper
    {
        public static AccountContext GetContext()
        {
            //if (HttpContext.Current.Session["AccountContext"] != null)
            //{
            //    return (AccountContext)HttpContext.Current.Session["AccountContext"];
            //}

            //return new AccountContext();
            return MyGlobals.accountContext;
        }

        public static void SetContext(AccountContext accContext)
        {
            //HttpContext context = HttpContext.Current;
            //context.Session.Add("AccountContext", accContext);

            MyGlobals.accountContext = accContext;
        }
    }


}