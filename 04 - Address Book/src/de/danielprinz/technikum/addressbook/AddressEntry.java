package de.danielprinz.technikum.addressbook;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by el17x002 on 27.09.2018.
 */
public class AddressEntry implements Serializable {

    private static final DateFormat FORMAT_DATE_CONVERSION = new SimpleDateFormat("dd.MM.yyyy", Locale.GERMANY);
    private static final DateFormat FORMAT_STRING_CONVERSION = new SimpleDateFormat("E, dd.MM.yyyy", Locale.GERMANY);

    private String firstName, lastName, address, phone;
    private Date birthDate;



    public AddressEntry(String firstName, String lastName, String address, String phone, String birthDate) throws ParseException {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.birthDate = FORMAT_DATE_CONVERSION.parse(birthDate);
    }


    public static DateFormat getFORMAT() {
        return FORMAT_DATE_CONVERSION;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthDate() {
        return FORMAT_STRING_CONVERSION.format(birthDate);
    }

    public String getBirthDateRaw() {
        return FORMAT_DATE_CONVERSION.format(birthDate);
    }

    public Date getBirthDateAsDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }


    @Override
    public String toString() {
        return "AddressEntry{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
