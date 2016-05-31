using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace CarelineWebAPI.Models
{
    public class AccountContext
    {
        public int UserID { get; set; }
        public int AccountID { get; set; }
        public bool Manager { get; set; }
    }
}