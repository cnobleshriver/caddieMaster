package app;

public class Caddie {

    private String name;
    private String lastName;
    private String number;
    private Rank rank;
    private int daysSat;

    public Caddie() {}

    public Caddie(String name, String lastName, String number, Rank rank, int daysSat) {
        setName(name);
        setLastName(lastName);
        setNumber(number);
        setRank(rank);
        setDaysSat(daysSat);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLastName() {
        return this.lastName;
    }
    
    public void setLastName (String lastName) {
        this.lastName = lastName;
    }

    public Rank getRank() {
        return this.rank;
    }

    public void setRank (Rank rank) {
        this.rank = rank;
    }

    public int getDaysSat() {
        return this.daysSat;
    }

    public void setDaysSat(int daysSat) {
        this.daysSat = daysSat;
    }

    public String toString() {
        return "Caddie: " + this.name + " " + this.lastName + ", " + this.number + ", " + this.rank + ", Days Sat: " + this.daysSat;
    }
}
