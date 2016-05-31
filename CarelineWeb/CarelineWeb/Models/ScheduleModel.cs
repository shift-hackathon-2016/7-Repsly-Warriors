using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace CarelineWebAPI.Models
{
    public class ScheduleModel
    {
        public int ScheduleId { get; set; }
        public Guid ScheduleRowid { get; set; }
        public DateTime ScheduleDateTime { get; set; }
        public bool Active { get; set; }
        public List<ScheduleItem> ScheduleItemList { get; set; }
        public string AudioFile { get; set; }
        public string Note { get; set; }
    }

    public class ScheduleItem
    {
        public Guid ScheduleItemRowId { get; set; }
        public Guid MedicineRowId { get; set; }
        public string ItemName { get; set; }
        public decimal Quantity { get; set; }
    }
}