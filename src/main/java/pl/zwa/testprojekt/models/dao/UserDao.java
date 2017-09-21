package pl.zwa.testprojekt.models.dao;


//interfejs dla okienka logowania i rejestracji
public interface UserDao {
    boolean login(String name, String password);
    boolean register(String name, String password);
    void removeUser(int id);
}
