package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ProductData
{
    private IntegerProperty Identifier;
    private StringProperty Cipher;
    private IntegerProperty Number;
    private IntegerProperty Score;
    private String[] Key;

    public int GetIdentifier()
    {
        return Identifier.get();
    }
    public IntegerProperty GetIdentifierProperty()
    {
        return Identifier;
    }
    public String GetCipher()
    {
        return Cipher.get();
    }
    public StringProperty GetCipherProperty()
    {
        return Cipher;
    }
    public int GetNumber()
    {
        return Number.get();
    }
    public IntegerProperty GetNumberProperty()
    {
        return Number;
    }
    public int GetScore()
    {
        return Score.get();
    }
    public IntegerProperty GetScoreProperty()
    {
        return Score;
    }
    public String[] GetKey()
    {
        return Key;
    }
    public void SetIdentifier(int identifier)
    {
        Identifier.set(identifier);
    }
    public void SetCipher(String cipher)
    {
        Cipher.set(cipher);
    }
    public void SetNumber(int number)
    {
        Number.set(number);
    }
    public void SetScore(int score)
    {
        Score.set(score);
    }
    public void SetKey(String[] key)
    {
        Key = key;
    }

    public ProductData(int identifier, String cipher, int number, int score)
    {
        Identifier = new SimpleIntegerProperty(identifier);
        Cipher = new SimpleStringProperty(cipher);
        if (number < 0) Number = new SimpleIntegerProperty(0);
        else Number = new SimpleIntegerProperty(Math.min(number, 4));
        if (score < 0) Score = new SimpleIntegerProperty(0);
        else Score = new SimpleIntegerProperty(Math.min(score, 100));
        Key = new String[] {String.valueOf(identifier), cipher, String.valueOf(number)};
    }
    public ProductData(ProductData data)
    {
        Identifier = new SimpleIntegerProperty(data.GetIdentifier());
        Cipher = new SimpleStringProperty(data.GetCipher());
        if (data.GetNumber() < 0) Number = new SimpleIntegerProperty(0);
        else Number = new SimpleIntegerProperty(Math.min(data.GetNumber(), 4));
        if (data.GetScore() < 0) Score = new SimpleIntegerProperty(0);
        else Score = new SimpleIntegerProperty(Math.min(data.GetScore(), 100));
        Key = new String[] {String.valueOf(data.GetIdentifier()), data.GetCipher(), String.valueOf(data.GetNumber())};
    }
}
