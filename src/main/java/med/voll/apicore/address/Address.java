package med.voll.apicore.address;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter

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
}
