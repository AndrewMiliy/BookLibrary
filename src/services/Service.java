package services;

import repositories.BookRepository;
import repositories.UserRepository;

public class Service {
    public static final Service instance = new Service();

    public static Service getInstance() {
        return instance;
    }

    public BookRepository bookRepository;
    public UserRepository userRepository;

}
