package pl.zwa.testprojekt.models.dao;

import java.util.List;
//interfejs fla okienka main - kontakty

public interface ContactDao {
    List<String> getAllContactsName(String username);
    String getNumber(String contact);
    boolean addContact(String name, String number);
    boolean editContact(String name, String number);
    void removeContact(String name);



}
