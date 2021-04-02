import java.util.Map;
import java.util.TreeMap;

/**
 * @author DavydovaVV
 * version 1.0 04/02/2021
 */
public class User {
    private String fio;
    Role role;

    /**
     * Конструктор класса User, в которой производится инициализация полей fio, role
     * @param fio
     * @param role
     */
    public User(String fio, Role role) {
        this.fio = fio;
        this.role = role;
    }

    /**
     * Попривествовать объект класса User
     * @param user объект класса User
     */
    public void greetUser(User user) {
        Map<Role, String> map = new TreeMap<>();
        map.put(Role.ADMIN, "Приветствуем " + user + " с ролью " + user.role);
        map.put(Role.MODERATOR, "Приветствуем " + user + " с ролью " + user.role);
        map.put(Role.USER, "Приветствуем " + user + " с ролью " + user.role);
    }

    /**
     * Переопределение метода toString()
     * @return возвращает объект в виде строки
     */
    @Override
    public String toString() {
        return fio;
    }
}
enum Role {ADMIN, USER, MODERATOR}
