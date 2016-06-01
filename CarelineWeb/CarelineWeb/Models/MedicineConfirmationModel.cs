using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace CarelineWebAPI.Models
{
    public class MedicineConfirmationModel
    {
        public Guid ScheduleItemRowid { get; set; }
        public DateTime ConfirmationDateTime { get; set; }
    }
}