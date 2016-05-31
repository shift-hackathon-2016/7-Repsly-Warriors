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
        internal static MobileUserModel GetUserDataByUserID(int userId)
        {
            MobileUserModel model = new MobileUserModel();

            using (DBConnector connector = new DBConnector("get_UserDataByUserID", CommandType.StoredProcedure))
            {
                connector.Cmd.Parameters.AddWithValue("@UserID", userId);
                connector.Execute(DBOperation.GetReader);

                if (connector.Rdr.HasRows)
                {
                    model.UserRowId = (Guid)connector.Rdr["UserRowid"];
                    model.AccountRowId = (Guid)connector.Rdr["AccountRowid"];
                    model.Name = connector.Rdr["Name"].ToString();
                    model.Address = connector.Rdr["Address"].ToString();
                    model.Manager = (bool)connector.Rdr["Manager"];
                    model.Avatar = connector.Rdr["Avatar"].ToString();
                }
            }

            return model;
        }
    }
}