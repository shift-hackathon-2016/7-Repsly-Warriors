using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace CarelineWebAPI.Models
{
    public class ScheduleHistory
    {
        public string Avatar { get; set; }
        public string UserName { get; set; }
        public string MedicineName { get; set; }
        public DateTime MedicineDateTime { get; set; }
        public bool Active { get; set; }
    }
}