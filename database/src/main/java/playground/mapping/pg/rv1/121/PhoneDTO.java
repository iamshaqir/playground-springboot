package playground.mapping.pg.rv1;

public record PhoneDTO(String number, String company, String technology) {

    public PhoneDTO(playground.mapping.pg.rv1.Phone phone) {
        this(phone.getNumber(), phone.getDetails().getProvider(), phone.getDetails().getTechnology());
    }
}
