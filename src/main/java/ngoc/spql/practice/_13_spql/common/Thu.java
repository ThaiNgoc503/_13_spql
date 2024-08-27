package ngoc.spql.practice._13_spql.common;


import ngoc.spql.practice._13_spql.dao.SinhVienDAOInterface;
import ngoc.spql.practice._13_spql.entity.SinhVien;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Scanner;

@Configuration
public class Thu {

    @Bean
    public CommandLineRunner getRunner(@Qualifier("sinhVienDAO") SinhVienDAOInterface sinhVienDAOInterface) {
        return run -> {
            while (true) {
                borderTop("Menu", MENU_LIST);
                printMenu(MENU_LIST);
                System.out.println("Choice one: ");
                int choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 1: {
                        saveSV(sinhVienDAOInterface);
                        break;
                    }
                    case 2: {
                        findSVByID(sinhVienDAOInterface);
                        break;
                    }
                    case 3: {
                        SinhVien sv = findSVByName(sinhVienDAOInterface);
                        if (sv == null) {
                            System.out.println("Not Found Name");
                        } else {
                            System.out.println(sv.getName() + " " + sv.getAge() + " " + sv.getId());
                        }
                        break;
                    }
                    case 4: {
                        List<SinhVien> sv = searchName(sinhVienDAOInterface);
                        if (sv == null) {
                            System.out.println("Not Found Name");
                        } else {
                            for (SinhVien s: sv) {
                                System.out.println(s.getName() + " " + s.getAge() + " " + s.getId());
                            }
                        }
                        break;
                    }
                }
            }
        };
    }

    private final Scanner sc = new Scanner(System.in);

    public final String[] MENU_LIST = {"1. Save", "2. Find By Id", "3. Find By Full Name", "4. Search Name", "5. Exit"};

    public int getMaxItem(String[] item) {
        int max = item[0].length();
        for (int i = 1; i < item.length; i++) {
            if (item[i].length() > max) {
                max = item[i].length();
            }
        }
        return max % 2 == 0 ? max : max + 9;
    }

    public void border(int max) {
        System.out.println("");
        for (int i = 1; i <= max + 2; i++) {
            System.out.print("-");
        }
        System.out.println("");
    }

    public void borderTop(String title, String[] item) {
        border(getMaxItem(item));
        System.out.print(spaceTop(title.length(), item, title));
        border(getMaxItem(item));
    }


    public String spaceTop(int titleNumber, String[] item, String title) {
        int space = getMaxItem(item);
        return "-" + " ".repeat(space / 2 + titleNumber / 2 - titleNumber) + title + " ".repeat(space / 2 - titleNumber / 2) + "-";
    }

    public String spaceBody(int titleNumber, String[] item, String title) {
        int space = getMaxItem(item);
        return "-" + title + " ".repeat(space - titleNumber) + "-";
    }

    public void printMenu(String[] menu) {
        for (int i = 0; i < menu.length; ++i) {
            System.out.print(spaceBody(menu[i].length(), menu, menu[i]));
            border(getMaxItem(menu));
        }
    }


    public void saveSV(SinhVienDAOInterface sinhVienDAOInterface) {
        System.out.println("Student name: ");
        String name = sc.nextLine();
        System.out.println("Student age: ");
        int age = sc.nextInt();
        SinhVien sinhVien = new SinhVien(name, age);
        sinhVienDAOInterface.save(sinhVien);
    }

    public void findSVByID(SinhVienDAOInterface sinhVienDAOInterface) {
        System.out.println("Student ID: ");
        String id = sc.nextLine();
        SinhVien sinhVien = new SinhVien(id);
        SinhVien sv = sinhVienDAOInterface.findByName(sinhVien.getName());
        if (sv == null) {
            System.out.println("Not found id");
        } else {
            System.out.println(sv.getName() + " " + sv.getAge());
        }

    }

    public SinhVien findSVByName(SinhVienDAOInterface sinhVienDAOInterface) {
        System.out.println("Student Name: ");
        String name = sc.nextLine();
        SinhVien sinhVien = new SinhVien(name);
        SinhVien sv = sinhVienDAOInterface.findByName(sinhVien.getName());
        return sv;
    }

    public List<SinhVien> searchName(SinhVienDAOInterface sinhVienDAOInterface) {
        System.out.println("Student Name: ");
        String name = sc.nextLine();
        List<SinhVien> sv = sinhVienDAOInterface.searchSinhVien(name);
        return sv;
    }

}