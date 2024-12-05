package ch.bbw.m320.restintro;

public class Country {
    int id;
    String name;
    String officialname;
    long population;
    double area;
    double nationaldebt;
    double gdppercapita;

    public int getId(){
        return this.id;
    }

    Country(int id, String name, long population, double area, double nationaldebt, double gdppercapita) {
        this.id = id;
        this.name = name;
        this.population = population;
        this.area = area;
        this.nationaldebt = nationaldebt;
        this.gdppercapita = gdppercapita;
    }
}
