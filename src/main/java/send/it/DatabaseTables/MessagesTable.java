package send.it.DatabaseTables;


import jakarta.persistence.*;


@Entity
@Table(name = "messages")
public class MessagesTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "message")
    private String message;

    @Column(name = "abbreviation")
    private String abbreviation;

    @Column(name = "user_id")
    private int userId;


    public MessagesTable() {
    }

    public MessagesTable(String message, String abbreviation, int userId) throws Exception {
        if(!validateMessage(message)) {
            throw new Exception("Message is too long or too short");
        }
        if(!validateAbbreviation(abbreviation)) {
            throw new Exception("Abbreviation is too long or too short.");
        }

        this.message = message;
        this.abbreviation = abbreviation;
        this.userId = userId;
    }

    // Getters
    public int getId() {
        return id;
    }
    public String getMessage() {
        return message;
    }
    public String getAbbreviation() {
        return abbreviation;
    }

    // Setters
    public void setMessage(String message) {
        this.message = message;
    }
    public void setAbbreviation(String abbreviation){
        this.abbreviation = abbreviation;
    }

    public boolean validateMessage(String message) {
        if (message.length() <= 250  || message.length() > 0) {
            return true;
        }
        return false;
    }

    public boolean validateAbbreviation(String abbreviation){
        if(abbreviation.length() <= 5  || abbreviation.length() > 0) {
            return true;
        }
        return false;
    }

}
