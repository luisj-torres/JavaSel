package task2;

public class Adult extends Person{

    private String phoneNumber;
    private Boolean hasDriverLicense;

    Adult(String name,Integer age,String phoneNumber,Boolean hasDriverLicense){
        super(name,age);
        this.setAge(age);
        this.setName(name);
        this.phoneNumber = phoneNumber;
        this.hasDriverLicense = hasDriverLicense;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getHasDriverLicense() {
        return hasDriverLicense;
    }

    public void setHasDriverLicense(Boolean hasDriverLicense) {
        this.hasDriverLicense = hasDriverLicense;
    }
}
