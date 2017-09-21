package pl.zwa.testprojekt.models.dao.impl;

import pl.zwa.testprojekt.models.MysqlConnector;
import pl.zwa.testprojekt.models.dao.ContactDao;

import java.util.List;

public class ContactDaoImpl implements ContactDao{

    private MysqlConnector connector = MysqlConnector.getInstance();


    //TODO Uzupełnij zapytanie SQL getAllContactsName join user zalogowany i jego kontakty!!!
    @Override
    public List<String> getAllContactsName(String username) {
        return null;
    }

    @Override
    public String getNumber(String contact) {
        return null;
    }

    @Override
    public boolean addContact(String name, String number) {
        return false;
    }

    @Override
    public boolean editContact(String name, String number) {
        return false;
    }

    @Override
    public void removeContact(String name) {

    }
}
