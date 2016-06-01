using System;
using System.Data;
using System.Configuration;
using System.Linq;
using System.Web;
using System.Xml.Linq;
using System.Data.SqlClient;

namespace DBLayer
{
    /// <summary>
    /// Klasa koja služi za povezivanje na bazu podataka
    /// </summary>
    public class DBConnector : IDisposable
    {
        /// <summary>
        /// Svojstvo koje drži string za povezivanje 
        /// </summary>
        public string connString { get; set; }

        /// <summary>
        /// Svojstvo koje drži objekt za povezivanje
        /// </summary>
        /// <remarks>Objekt za povezivanje u sebi sadrži string za povezivanje te ostala svojstva potrebna za povezivanje</remarks>
        public SqlConnection Conn { get; set; }

        /// <summary>
        /// Svojstvo koje drži naredbu za povezivanje
        /// </summary>
        /// <remarks>
        /// Naredba u sebi sadrži objekt za povezivanje, 
        /// govori poslužitelju kakav je tip naredbe (Procedura, tekst ili dr.) te sve parametre koji se proslijeđuju bazi podataka tj poslužitelju
        /// </remarks>
        public SqlCommand Cmd { get; set; }
     
        /// <summary>
        /// Svojstvo koje drži DataSet objekt koji se puni iz baze podataka
        /// </summary>
        public DataSet DS { get; set; }

        /// <summary>
        /// Svojstvo koje drži DataReader objekt kreiran naredbom za čitanje iz baze podataka
        /// </summary>
        public SqlDataReader Rdr { get; set; }

        public DBConnector() { }

        public DBConnector(string Sql, CommandType SP_TX)
        { CreateCommand(Sql, SP_TX); }

        public void CreateCommand(string Sql, CommandType SP_TX)
        {
            connString = GetConnectionString();
            Conn = new SqlConnection(connString);
            Cmd = new SqlCommand(Sql, Conn);
            Cmd.CommandType = SP_TX;
        }

        public void Execute(DBOperation radnja)
        {
            try
            {
                if (radnja == DBOperation.Save)
                {
                    try
                    {
                        Conn.Open();
                        int result = Cmd.ExecuteNonQuery();
                        Cmd.Parameters.Clear();
                    }
                    catch (Exception ex)
                    {
                        throw;
                    }
                    finally
                    {
                        Conn.Close();
                    }
                }

                if (radnja == DBOperation.SaveWithOutput)
                {
                    try
                    {
                        Conn.Open();
                        int result = Cmd.ExecuteNonQuery();
                    }
                    catch (Exception ex)
                    {
                        Conn.Close();
                        throw;
                    }
                }

                if (radnja == DBOperation.GetDataSet)
                {
                    try
                    {
                        SqlDataAdapter adapter = new SqlDataAdapter(Cmd);
                        DS = new DataSet();
                        adapter.Fill(DS);
                    }
                    catch (Exception ex)
                    {
                        throw;
                    }
                    finally
                    {
                        Conn.Close();
                    }
                }
                if (radnja == DBOperation.GetReader)
                {
                    Conn.Open();
                    Rdr = Cmd.ExecuteReader(CommandBehavior.CloseConnection);
                    if (Rdr.HasRows)
                    {
                        Rdr.Read();
                    }
                }
                if (radnja == DBOperation.GetWhileReader)
                {
                    Conn.Open();
                    Rdr = Cmd.ExecuteReader(CommandBehavior.CloseConnection);
                }
            }
            catch
            {
                throw;
            }
        }

        public void Dispose()
        {
            if (Cmd != null)
            {
                Cmd.Parameters.Clear();
                Cmd.Dispose();
            }
            if (DS != null)
                DS.Dispose();

            if (Rdr != null)
            {
                Rdr.Close();
                Rdr.Dispose();
            }

            //if (Tran != null && Conn.State == ConnectionState.Open)
            //    Tran.Rollback();

            if (Conn.State == ConnectionState.Open)
            {
                Conn.Close();
                Conn.Dispose();
            }
        }

        public virtual string GetConnectionString()
        {
            return ConfigurationManager.ConnectionStrings["DefaultConnection"].ConnectionString;
        }
    }
}