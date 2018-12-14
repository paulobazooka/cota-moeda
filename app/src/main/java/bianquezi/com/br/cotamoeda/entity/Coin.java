package bianquezi.com.br.cotamoeda.entity;

/**
 *      author: Paulo Sérgio do Nascimento
 *      date: 13/12/2018
 * */
public class Coin {
    String name;
    Double buy;
    Double sell;
    Double variation;


    public Coin() {
    }


    public Coin(String name, Double buy, Double sell, Double variation) {
        this.name = name;
        this.buy = buy;
        this.sell = sell;
        this.variation = variation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBuy() {
        return buy;
    }

    public void setBuy(Double buy) {
        this.buy = buy;
    }

    public Double getSell() {
        return sell;
    }

    public void setSell(Double sell) {
        this.sell = sell;
    }

    public Double getVariation() {
        return variation;
    }

    public void setVariation(Double variation) {
        this.variation = variation;
    }
}
