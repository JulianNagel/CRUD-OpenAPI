package ch.bbw.m320.restintro;

// so much boilerplate code: should be converted to a record (or use Lombock)
public class Country {
    private int id;
    private String name;
    private String officialName;
    private long population;
    private double area;
    private double gdp;
    private double lifeExpectancy;

    public Country() {
    }

    public Country(int id, String name, String officialName, long population, double area, double gdp, double lifeExpectancy) {
        this.id = id;
        this.name = name;
        this.officialName = officialName;
        this.population = population;
        this.area = area;
        this.gdp = gdp;
        this.lifeExpectancy = lifeExpectancy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOfficialName() {
        return officialName;
    }

    public void setOfficialName(String officialName) {
        this.officialName = officialName;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getGdp() {
        return gdp;
    }

    public void setGdp(double gdp) {
        this.gdp = gdp;
    }

    public double getLifeExpectancy() {
        return lifeExpectancy;
    }

    public void setLifeExpectancy(double lifeExpectancy) {
        this.lifeExpectancy = lifeExpectancy;
    }
}
