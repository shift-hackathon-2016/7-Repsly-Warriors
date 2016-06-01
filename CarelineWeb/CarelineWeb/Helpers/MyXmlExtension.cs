using System;
using System.Collections;
using System.Collections.Generic;
using System.Data.SqlTypes;
using System.Linq;
using System.Reflection;
using System.Web;
using System.Xml.Linq;

namespace CarelineWebAPI.Helpers
{
    public static class MyXmlExtension
    {
        public static XElement CreateXml(this object model)
        {
            if (model == null)
                return null;

            if (model.GetType().IsGenericType)
            {
                XElement xmlElements = new XElement("ItemList");
                var listT = (IEnumerable)model;
                ConvertList(xmlElements, listT);

                return xmlElements;
            }
            else
            {
                XElement xmlElements = new XElement(model.GetType().Name);
                foreach (PropertyInfo property in model.GetType().GetProperties())
                {
                    if (property.PropertyType.IsGenericType)//GetInterface("IEnumerable") != null
                    {
                        XElement xmlProperty = new XElement(property.Name);
                        var listT = (IEnumerable)property.GetValue(model, BindingFlags.Default, null, null, null);
                        ConvertList(xmlProperty, listT);
                        xmlElements.Add(xmlProperty);
                    }
                    else
                    {
                        XElement xmlProperty = new XElement(property.Name, property.GetValue(model, BindingFlags.Default, null, null, null));
                        xmlElements.Add(xmlProperty);
                    }
                }

                return xmlElements;
            }
        }

        private static void ConvertList(XElement xmlNode, IEnumerable listT)
        {
            if (listT != null)
                foreach (var item in listT)
                {
                    XElement ListObject;
                    if (item.GetType().Name == "String")
                    {
                        ListObject = new XElement("Item", item);
                    }
                    else
                    {
                        ListObject = new XElement(item.GetType().Name);
                        foreach (PropertyInfo itemProperty in item.GetType().GetProperties())
                        {
                            var listItem = new XElement(itemProperty.Name,
                                itemProperty.GetValue(item, BindingFlags.Default, null, null, null));

                            ListObject.Add(listItem);
                        }
                    }

                    xmlNode.Add(ListObject);
                }
        }

        public static SqlXml CreateSqlXml(this object model)
        {
            if (model == null)
                return new SqlXml();

            return new SqlXml(model.CreateXml().CreateReader());
        }
    }
}