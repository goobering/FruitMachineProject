package example.codeclan.com.fruitmachine.models;

/**
 * Created by user on 07/07/2017.
 */

public class Symbol
{
    private int id;
    private String imageSetName;
    private String imageName;
    private String imageLocation;
    private int score;

    public Symbol()
    {

    }

    public Symbol(String imageSetName, String imageName, String imageLocation, int score)
    {
        this.imageSetName = imageSetName;
        this.imageName = imageName;
        this.imageLocation = imageLocation;
        this.score = score;
    }

    public Symbol(int id, String imageSetName, String imageName, String imageLocation, int score)
    {
        this.id = id;
        this.imageSetName = imageSetName;
        this.imageName = imageName;
        this.imageLocation = imageLocation;
        this.score = score;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getImageSetName()
    {
        return imageSetName;
    }

    public String getImageLocation()
    {
        return imageLocation;
    }

    public void setImageLocation(String imageLocation)
    {
        this.imageLocation = imageLocation;
    }

    public void setImageSetName(String imageSetName)
    {
        this.imageSetName = imageSetName;
    }

    public String getImageName()
    {
        return imageName;
    }

    public void setImageName(String imageName)
    {
        this.imageName = imageName;
    }

    public int getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        this.score = score;
    }
}
