using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace CarelineWebAPI.Models
{
    public class MobileUserModel
    {
        public Guid UserRowId { get; set; }
        public Guid AccountRowId { get; set; }
        public string Name { get; set; }
        public string Address { get; set; }
        public bool Manager { get; set; }
        public string Avatar { get; set; }
    }
}