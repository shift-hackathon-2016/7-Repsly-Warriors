using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace CarelineWebAPI.Models
{
    public class MedicineConfirmationModel
    {
        public Guid ScheduleRowid { get; set; }
        public DateTime ConfirmationDateTime { get; set; }
    }
}