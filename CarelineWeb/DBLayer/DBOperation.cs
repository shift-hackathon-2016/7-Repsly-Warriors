using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace DBLayer
{
    public enum DBOperation
    {
        Save,
        SaveWithOutput,
        GetReader,
        GetWhileReader,
        GetDataSet
    }
}
