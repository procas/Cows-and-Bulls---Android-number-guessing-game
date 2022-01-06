package thenewboston.com.dynamic_2;

/**
 * Created by HP on 28-01-2017.
 */
public class Scores {

    private int _id;
    private int _score;
    private String _date;
    private float _star;

    public Scores(int score, String date, float star)
    {
        _score=score;
        _date=date;
        _star=star;

    }
    public void set_id()
    {
       this._id=_id;
    }
    public void set_stars()
    {
        this._star=_star;
    }
    public void set_score()
    {
        this._score=_score;
    }
    public void set_date()
    {
        this._date=_date;
    }
    public int get_id()
    {
        return _id;
    }
    public int get_score()
    {
        return _score;
    }
    public float get_star()
    {
        return _star;
    }
    public String get_date()
    {
        return _date;
    }
}
