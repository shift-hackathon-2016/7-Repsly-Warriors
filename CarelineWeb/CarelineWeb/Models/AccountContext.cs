using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace CarelineWebAPI.Models
{
    public class AccountContext
    {
        public int UserId { get; set; }
        public int AccountId { get; set; }
        public bool Manager { get; set; }
    }
}