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
            using (DBConnector connector = new DBConnector("get_UserByUsernamePassword", CommandType.StoredProcedure))
            {
                connector.Cmd.Parameters.AddWithValue("@Username", username);
                connector.Cmd.Parameters.AddWithValue("@Password", password);
                connector.Execute(DBOperation.GetReader);

                if (connector.Rdr.HasRows)
                {
                    AccountContext context = new AccountContext();

                    context.UserID = Convert.ToInt32(connector.Rdr["IDUser"]);
                    context.AccountID = Convert.ToInt32(connector.Rdr["AccountID"]);
                    context.Manager = (bool)connector.Rdr["Manager"];

                    AccountContextHelper.SetContext(context);

                    return true;
                }
            }

            return false;
        }
    }
}