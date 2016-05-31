using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using CarelineWebAPI.Models;
using System.Configuration;
using DBLayer;
using System.Data;
using System.Data.SqlClient;

namespace CarelineWebAPI.Data
{
    public class DBOperations
    {
        internal static UserModel GetUserDataByUserID(int userId)
        {
            UserModel model = new UserModel();

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

        internal static CareReceiverModel GetCareReceiver(int Id, int accountId)
        {
            CareReceiverModel model = new CareReceiverModel();
            using (DBConnector connector = new DBConnector("get_CareReceiver", CommandType.StoredProcedure))
            {
                connector.Cmd.Parameters.AddWithValue("@UserID", Id);
                connector.Cmd.Parameters.AddWithValue("@AccountID", accountId);
                connector.Execute(DBOperation.GetReader);

                if (connector.Rdr.HasRows)
                {
                    model.UserId = Convert.ToInt32(connector.Rdr["IDUser"]);
                    model.UserRowId = (Guid)connector.Rdr["UserRowid"];
                    model.AccountId = Convert.ToInt32(connector.Rdr["AccountID"]);
                    model.AccountRowId = (Guid)connector.Rdr["AccountRowid"];
                    model.Name = connector.Rdr["Name"].ToString();
                    model.Address = connector.Rdr["Address"].ToString();
                    model.Manager = (bool)connector.Rdr["Manager"];
                    model.Avatar = connector.Rdr["Avatar"].ToString();
                    model.Username = connector.Rdr["Username"].ToString();
                    model.Password = connector.Rdr["Password"].ToString();
                    model.LastMovementDateTime = (DateTime)connector.Rdr["LastMovementDateTime"];
                }
            }

            return model;
        }

        internal static UserModel GetUserByUsernamePassword(string username, string password)
        {
            UserModel user = new UserModel();
            using (DBConnector connector = new DBConnector("get_UserByUsernamePassword", CommandType.StoredProcedure))
            {
                connector.Cmd.Parameters.AddWithValue("@Username", username);
                connector.Cmd.Parameters.AddWithValue("@Password", password);
                connector.Execute(DBOperation.GetReader);

                if (connector.Rdr.HasRows)
                {
                    user.UserId = Convert.ToInt32(connector.Rdr["IDUser"]);
                    user.UserRowId = (Guid)connector.Rdr["UserRowid"];
                    user.AccountId = Convert.ToInt32(connector.Rdr["AccountID"]);
                    user.AccountRowId = (Guid)connector.Rdr["AccountRowid"];
                    user.Name = connector.Rdr["Name"].ToString();
                    user.Address = connector.Rdr["Address"].ToString();
                    user.Manager = (bool)connector.Rdr["Manager"];
                    user.Avatar = connector.Rdr["Avatar"].ToString();
                }
            }

            return user;
        }

        internal static int RegisterAccount(RegistrationModel model)
        {
            using (DBConnector connector = new DBConnector("save_RegisterAccount", CommandType.StoredProcedure))
            {
                connector.Cmd.Parameters.AddWithValue("@Name", model.Name);
                connector.Cmd.Parameters.AddWithValue("@Email", model.Email);
                connector.Cmd.Parameters.AddWithValue("@Password", model.Password);

                SqlParameter IDParam = new SqlParameter("@Status", SqlDbType.Int);
                IDParam.Direction = ParameterDirection.InputOutput;
                IDParam.Value = 0;
                connector.Cmd.Parameters.Add(IDParam);

                connector.Execute(DBOperation.SaveWithOutput);

                return Convert.ToInt32(connector.Cmd.Parameters["@Status"].Value);
            }
        }

        internal static void SaveTrackingEvent(NewTrackingEventModel model, int userId, int accountId)
        {
            using (DBConnector connector = new DBConnector("save_TrackingEvent", CommandType.StoredProcedure))
            {
                connector.Cmd.Parameters.AddWithValue("@AccountID", accountId);
                connector.Cmd.Parameters.AddWithValue("@UserID", userId);
                connector.Cmd.Parameters.AddWithValue("@EventTypeID", model.EventTypeID);
                connector.Cmd.Parameters.AddWithValue("@EventDateTime", model.EventDateTime);
                connector.Cmd.Parameters.AddWithValue("@Latitude", model.Latitude);
                connector.Cmd.Parameters.AddWithValue("@Longitude", model.Longitude);
                connector.Cmd.Parameters.AddWithValue("@RelatedScheduleRowid", model.RelatedScheduleRowid);

                connector.Execute(DBOperation.Save);
            }
        }

        internal static void SaveMedicineConfirmation(MedicineConfirmationModel model, int userId)
        {
            using (DBConnector connector = new DBConnector("save_ScheduleConfirmation", CommandType.StoredProcedure))
            {
                connector.Cmd.Parameters.AddWithValue("@UserID", userId);
                connector.Cmd.Parameters.AddWithValue("@ScheduleRowid", model.ScheduleRowid);
                connector.Cmd.Parameters.AddWithValue("@ConfirmationDateTime", model.ConfirmationDateTime);

                connector.Execute(DBOperation.Save);
            }            
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