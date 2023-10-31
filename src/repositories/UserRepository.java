package repositories;

import models.UserModel;

public class UserRepository {
    ElasticArray users= new ElasticArray();


    private int usersCount;

    public void add(UserModel user) {
        users.add(user);
    }
    public void remove(UserModel user) {
        users.remove(user);
    }

    public boolean isExistsByEmail(String email) {
        users.find( x -> Boolean.parseBoolean(email));
        return ;
    }

    public UserModel[] findAll() {
        users.findAll();

    }
}
