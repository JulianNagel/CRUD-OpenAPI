package ch.bbw.m320.restintro;

import java.math.BigDecimal;

public class Country {
    int id;
    String name;
    String officialname;
    long population;
    double area; //square kilometers
    double nationaldebt; //in billion
    double gdppercapita;

    Country(int id, String name, String officialname, long population, double area, double nationaldebt, double gdppercapita){
        this.id = id;
        this.name = name;
        this.officialname = officialname;
        this.population = population;
        this.area = area;
        this.nationaldebt = nationaldebt;
        this.gdppercapita = gdppercapita;
    }
}
