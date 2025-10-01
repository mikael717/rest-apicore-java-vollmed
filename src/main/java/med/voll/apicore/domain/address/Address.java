package med.voll.apicore.domain.address;

import jakarta.persistence.Embeddable;

@Embeddable

public class Address {

    private String street;
    private String neighborhood;
    private String zipcode;
    private String number;
    private String complement;
    private String city;
    private String state;

    public Address() {
    }

    public Address(String street, String neighborhood, String zipcode, String number, String complement, String city, String state) {
        this.street = street;
        this.neighborhood = neighborhood;
        this.zipcode = zipcode;
        this.number = number;
        this.complement = complement;
        this.city = city;
        this.state = state;
    }

    public Address(DataAddress data) {
        this.street = data.street();
        this.neighborhood = data.neighborhood();
        this.zipcode = data.zipcode();
        this.number = data.number();
        this.complement = data.complement();
        this.city = data.city();
        this.state = data.state();
    }

    public String getStreet() {
        return street;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getNumber() {
        return number;
    }

    public String getComplement() {
        return complement;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public void updateInformation(DataAddress data) {
        if(data.street() != null){
            this.street = data.street();
        }
        if(data.neighborhood() != null){
            this.neighborhood = data.neighborhood();
        }
        if(data.zipcode() != null){
            this.zipcode = data.zipcode();
        }
        if(data.number() != null){
            this.number = data.number();
        }
        if(data.complement() != null){
            this.complement = data.complement();
        }
        if(data.city() != null){
            this.city = data.city();
        }
        if(data.state() != null){
            this.state = data.state();
        }
    }
}
