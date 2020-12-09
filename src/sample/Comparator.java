package sample;

public class Comparator implements java.util.Comparator<ProductData>
{
    @Override
    public int compare(ProductData first, ProductData second)
    {
        if (first.GetScore() < second.GetScore()) return -1;
        else if (first.GetScore() > second.GetScore()) return 1;
        else
        {
            if (first.GetNumber() < second.GetScore()) return 1;
            else if ((first.GetNumber() < second.GetScore())) return -1;
            else return 0;
        }
    }
}
