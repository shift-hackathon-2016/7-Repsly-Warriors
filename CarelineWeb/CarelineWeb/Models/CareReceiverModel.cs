using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace CarelineWebAPI.Models
{
    public class CareReceiverModel : UserModel
    {
        public string Note { get; set; }
        public DateTime LastMovementDateTime { get; set; }
        public string Username { get; set; }
        public string Password { get; set; }
    }
}