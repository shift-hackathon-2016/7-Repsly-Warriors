using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace CarelineWebAPI.Models
{
    public class NewTrackingEventModel
    {
        public int EventTypeID { get; set; }
        public DateTime EventDateTime { get; set; }
        public float Latitude { get; set; }
        public float Longitude { get; set; }
        public Guid RelatedScheduleRowid { get; set; }
    }
}