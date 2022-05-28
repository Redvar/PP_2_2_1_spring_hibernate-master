package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(User.builder()
              .email("123@mail.ru")
              .firstName("Vasya")
              .lastName("Vasechkin")
              .car(Car.builder()
                      .model("Volvo")
                      .series(9)
                      .build())
              .build());
      userService.add(User.builder()
              .email("1234@mail.ru")
              .firstName("VasyaVasya")
              .lastName("Sidorov")
              .car(Car.builder()
                      .model("BMW")
                      .series(325)
                      .build())
              .build());
      userService.add(User.builder()
              .email("12345@mail.ru")
              .firstName("dasdasda")
              .lastName("dasdasd")
              .car(Car.builder()
                      .model("Sisuki")
                      .series(5)
                      .build())
              .build());
      userService.add(User.builder()
              .email("123456@mail.ru")
              .firstName("Petyeu")
              .lastName("DSAAS")
              .car(Car.builder()
                      .model("Lada")
                      .series(1234)
                      .build())
              .build());

      // пользователи с машинами
      for (User user : userService.listUsers()) {
         System.out.println(user);
      }

      // достать юзера, владеющего машиной по ее модели и серии
      System.out.println(userService.getUserByCar("BMW", 323));

      // нет такого юзера с такой машиной
      try {
         User notFoundUser = userService.getUserByCar("GAZ", 4211);
      } catch (NoResultException e) {
         System.out.println("User not found");
      }

      context.close();
   }
}
