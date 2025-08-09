package playground.mapping.pg.rv1.n21;

public record PhoneDTO(String number, String name) {

    public PhoneDTO(Phone phone) {
        this(phone.getNumber(), merge(phone.getPerson().getFirstname(), phone.getPerson().getFirstname()));
    }

    private static String merge(String firstName, String lastName) {
        return String.join(" ", firstName, lastName);
    }
}
