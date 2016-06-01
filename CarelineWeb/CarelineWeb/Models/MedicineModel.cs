using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace CarelineWebAPI.Models
{
    public class MedicineModel
    {
        public int MedicineID { get; set; }
        public string Name { get; set; }
        public string MedImg { get; set; }
        public string Description { get; set; }
        public string MedColor { get; set; }
        public string MedType { get; set; }
        public decimal Quantity { get; set; }
    }
}