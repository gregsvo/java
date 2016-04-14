package Email_DAO;

import Email_DTO.Email;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author apprentice
 */
public class EmailDAO {

    private Map<Integer, Email> emailMap = new HashMap<>();
    final String DELIMETER = "::";
    final String FILENAME = "Decision_Desk_Email_List.txt";
    private Integer nextId = 1;

    private int getNextID() {
        nextId++;
        return nextId;
    }

    public EmailDAO() {
        {

            List<Email> emailList = decode();

            for (Email i : emailList) {
                emailMap.put(i.getId(), i);

                if (i.getId() > nextId) {
                    nextId = i.getId() + 1;
                }

            }
        }
    }

    public Email create(Email email) {
        email.setId(getNextID());
        emailMap.put(email.getId(), email);
        encode();
        return email;
    }

    public List<Email> read() {
        List<Email> emailList = new ArrayList<>(emailMap.values());
        return emailList;
    }

    public Email getEmailById(int id) {
        return emailMap.get(id);

    }

    public List<Email> getEmailList() {
        List<Email> emailList = new ArrayList<>(emailMap.values());
        return emailList;
    }

    public void update(Integer id, Email email) {
        emailMap.replace(id, email);
        encode();
    }

    // DECODE     
    public List<Email> decode() {
        List<Email> emailList = new ArrayList<>();

        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(FILENAME)));

            while (sc.hasNextLine()) {

                String currentLine = sc.nextLine();
                String[] values = currentLine.split(DELIMETER);

                Email email = new Email();

                email.setId(Integer.parseInt(values[0]));
                email.setAddress(values[1]);
                email.setStatus(Integer.parseInt(values[2]));
                email.setLastUpdated(LocalDate.parse(values[3]));

                emailList.add(email);

            }

        } catch (FileNotFoundException ex) {

        }
        return emailList;
    }

    // ENCODE
    public void encode() {

        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FILENAME)));
            List<Email> emailList = getEmailList();
            String line = "";
            for (Email email : emailList) {

                line = email.getId() + DELIMETER
                        + email.getAddress() + DELIMETER
                        + email.getStatus() + DELIMETER
                        + email.getLastUpdated() + DELIMETER;

                out.println(line);
                out.flush();
            }

            out.close();
        } catch (IOException e) {

        }

    }

    public Email searchByEmail(String search) {

        List<Email> emailList = new ArrayList<>(emailMap.values());
        Email result = null;

        for (Email email : emailList) {
            if (email.getAddress().equals(search)) {
                result = email;
            }

        }
        return result;
    }

    public List<Email> listSubscribed() {

        List<Email> emailList = new ArrayList<>(emailMap.values());
        List<Email> subscribedList = emailList.stream()
                .filter(s -> s.getStatus() == Email.SUBSCRIBED)
                .collect(Collectors.toList());

        return subscribedList;
    }

    public List<Email> listResubscribed() {
        List<Email> emailList = new ArrayList<>(emailMap.values());
        List<Email> resubscribedList = emailList.stream()
                .filter(s -> s.getStatus() == Email.RESUBSCRIBED)
                .collect(Collectors.toList());

        return resubscribedList;
    }

    public List<Email> listUnsubscribed() {
        List<Email> emailList = new ArrayList<>(emailMap.values());
        List<Email> unsubscribedList = emailList.stream()
                .filter(s -> s.getStatus() == Email.UNSUBSCRIBED)
                .collect(Collectors.toList());

        return unsubscribedList;
    }
}
