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

        internal static List<ScheduleModel> GetUserScheduleList(int userId)
        {
            List<ScheduleModel> modelList = new List<ScheduleModel>();

            using (DBConnector connector = new DBConnector("get_UserActiveScheduleList", CommandType.StoredProcedure))
            {
                connector.Cmd.Parameters.AddWithValue("@UserID", userId);
                connector.Execute(DBOperation.GetWhileReader);

                if (connector.Rdr.HasRows)
                {
                    while (connector.Rdr.Read())
                    {
                        ScheduleModel model = new ScheduleModel();

                        model.ScheduleId = Convert.ToInt32(connector.Rdr["IDSchedule"]);
                        model.ScheduleRowid = (Guid)connector.Rdr["ScheduleRowid"];
                        model.ScheduleDateTime = (DateTime)connector.Rdr["ScheduleDateTime"];
                        model.Note = connector.Rdr["Note"].ToString();
                        model.Active = (bool)connector.Rdr["Active"];
                        model.AudioFile = connector.Rdr["AudioFile"].ToString();

                        modelList.Add(model);
                    }
                }

            }

            foreach(var model in modelList)
            {
                model.ScheduleItemList = GetScheduleItems(model.ScheduleId);
            }

            return modelList;
        }

        private static List<ScheduleItem> GetScheduleItems(int scheduleId)
        {
            List<ScheduleItem> itemList = new List<ScheduleItem>();

            using (DBConnector connector = new DBConnector("get_UserScheduleItemList", CommandType.StoredProcedure))
            {
                connector.Cmd.Parameters.AddWithValue("@ScheduleID", scheduleId);
                connector.Execute(DBOperation.GetWhileReader);

                if (connector.Rdr.HasRows)
                {
                    while (connector.Rdr.Read())
                    {
                        ScheduleItem item = new ScheduleItem();

                        item.MedicineRowId = (Guid)connector.Rdr["MedicineRowId"];
                        item.ScheduleItemRowId = (Guid)connector.Rdr["ScheduleItemRowId"];
                        item.ItemName = connector.Rdr["ItemName"].ToString();
                        item.Quantity = Convert.ToDecimal(connector.Rdr["Quantity"]);

                        itemList.Add(item);
                    }
                }

            }

            return itemList;
        }
    }
}