using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace CarelineWebAPI.Models
{
    public class GeolocationModel
    {
        public int UserID { get; set; }
        public string Name { get; set; }
        public string Avatar { get; set; }
        public DateTime MaxDate { get; set; }
        public float Latitude { get; set; }
        public float Longitude { get; set; }
    }
}