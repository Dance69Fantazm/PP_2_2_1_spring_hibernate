package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        Car car = new Car("Tesla Cybertruck", 2023);
        Car car1 = new Car("Mercedes-Benz AMG GT",2020);
        Car car2 = new Car("Nissan Qashqai", 2020);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru"));
        userService.add(new User("User5", "Lastname5", "user5@mail.ru", car));
        userService.add(new User("User6", "Lastname6", "user6@mail.ru", car2));
        userService.add(new User("User7", "Lastname7", "user7@mail.ru", car1));
        userService.add(new User("User8", "Lastname8", "user8@mail.ru", car));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
            if (user.getCar() != null) {
                System.out.println("Модель авто = " + user.getCar().getModel() + ", Год выпуска = " + user.getCar().getSeries());
            } else {
                System.out.println("В таблице нет users имеющие автомобиль!");
            }
            System.out.println();
        }

        User userWithCar = userService.getUserByCar("Tesla Cybertruck", 2023);
        if (userWithCar != null) {
            System.out.println("User с автомобилем: " + userWithCar.getFirstName() + " " + userWithCar.getLastName());
        } else {
            System.out.println("User не найден с указанным автомобилем. Перепроверь данные!");
        }

        context.close();
    }
}
