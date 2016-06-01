using CarelineWebAPI.Data;
using CarelineWebAPI.Models;
using DBLayer;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;

namespace CarelineWebAPI.Helpers
{
    public class RequestValidationHelper
    {
        public static bool ValidateUser(string username, string password)
        {
            UserModel user = DBOperations.GetUserByUsernamePassword(username, password);

            if (user.UserId > 0)
            {
                AccountContext context = new AccountContext();

                context.UserId = user.UserId;
                context.AccountId = user.AccountId;
                context.Manager = user.Manager;

                AccountContextHelper.SetContext(context);

                return true;
            }

            return false;
        }
    }
}