using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace CarelineWebAPI.Models
{
    public class RegistrationModel
    {
        public string Name { get; set; }
        public string Email { get; set; }
        public string Password { get; set; }
    }
}