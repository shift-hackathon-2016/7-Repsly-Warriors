using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using CarelineWebAPI.Models;
using System.Configuration;
using DBLayer;
using System.Data;

namespace CarelineWebAPI.Data
{
    public class DBOperations
    {
        internal static MobileUserModel MobileGetUserData(string username, string password)
        {
            MobileUserModel model = new MobileUserModel();

            using (DBConnector connector = new DBConnector("get_UserData", CommandType.StoredProcedure))
            {
                connector.Cmd.Parameters.AddWithValue("@Username", username);
                connector.Cmd.Parameters.AddWithValue("@Password", password);
                connector.Execute(DBOperation.GetReader);

                if(connector.Rdr.HasRows)
                {
                    connector.Rdr.Read();

                }
            }

            return model;
        }
    }
}