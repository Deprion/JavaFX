package sample;

public class TransferData
{
    public static ProductData[] StringToData(String str)
    {
        if (!str.isEmpty())
        {
            String[] _str = str.split("\\. ");
            ProductData[] data = new ProductData[_str.length];
            for (int i = 0; i < _str.length; i++)
            {
                String[] tempS = _str[i].split(", ");
                try
                {
                    data[i] = new ProductData(Integer.parseInt(tempS[0]), tempS[1], Integer.parseInt(tempS[2]),
                            Integer.parseInt(tempS[3]));
                }
                catch (java.lang.NumberFormatException e)
                {
                    e.printStackTrace();
                }
            }
            return data;
        }
        return null;
    }
    public static String DataToString(ProductData[] data)
    {
        if (data.length != 0)
        {
            String[] str = new String[data.length];
            String regex = ", ";
            StringBuilder strToReturn = new StringBuilder();
            for (int i = 0; i < data.length; i++)
            {
                str[i] = data[i].GetIdentifier() + regex + data[i].GetCipher() + regex +
                        data[i].GetNumber() + regex + data[i].GetScore() + ". \n";
                strToReturn.append(str[i]);
            }
            return strToReturn.toString();
        }
        return null;
    }
}
