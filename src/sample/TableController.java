package sample;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Map;

public class TableController
{
    public Map<String[], Integer> dict;

    public void AddElement(ProductData data)
    {
        dict.put(data.GetKey(), data.GetScore());
    }
    public void RemoveElement(ProductData data)
    {
        dict.remove(data.GetKey(), data.GetScore());
    }
}
