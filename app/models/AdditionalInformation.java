package models;

import io.ebean.Finder;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class AdditionalInformation extends baseModel {
    private String dificulty;
    private Integer guests;
    private String price;
    private String kal;

    //@OneToOne(mappedBy = "additionalInformation")
    //public Recipe recipe;

    private static final Finder<Long, AdditionalInformation> find = new Finder<>(AdditionalInformation.class);

    public String getDificulty() { return dificulty; }
    public void setDificulty(String dificulty) { this.dificulty = dificulty; }
    public Integer getGuests() { return guests; }
    public void setGuests(Integer guests) { this.guests = guests; }
    public String getPrice() { return price; }
    public void setPrice(String price) { this.price = price; }
    public String getKal() { return kal; }
    public void setKal(String kal) { this.kal = kal; }

    public AdditionalInformation createAdditionalInformation() {
        this.save();
        return this;
    }

    public AdditionalInformation updateAdditionalInformation() {
        this.update();
        return this;
    }

    public static AdditionalInformation findById(Long id) {
        return find.byId(id);
    }

    public void merge(AdditionalInformation additionalInformation) {
        this.dificulty = additionalInformation.dificulty == null ? this.dificulty : additionalInformation.dificulty;
        this.guests = additionalInformation.guests == null ? this.guests : additionalInformation.guests;
        this.price = additionalInformation.price == null ? this.price : additionalInformation.price;
        this.kal = additionalInformation.kal == null ? this.kal : additionalInformation.kal;
    }

    public Boolean deleteAdditionalInformation(Long id) {
        AdditionalInformation additionalInformation = find.byId(id);
        return additionalInformation.delete();
    }
}
