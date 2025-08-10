package playground.mapping.pg.rv1.one2one;

public record PhoneDTO(String number, String company, String technology) {

    public PhoneDTO(Phone phone) {
        this(phone.getNumber(), phone.getDetails().getProvider(), phone.getDetails().getTechnology());
    }
}
