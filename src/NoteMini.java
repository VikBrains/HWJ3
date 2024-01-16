import model.Parsers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class NoteMini {

    private static final int RequiredDataQty = 6;

    public static void trycatblock() {
        try {
            readUserData();
        } catch (InputMismatchException e) {
            System.out.println("Неверный формат входных данных: " + e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println("Неверный формат даты рождения: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат номера телефона: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Неверное обозначение пола: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Ошибка при работе с файлом: " + e.getMessage());
        }
    }

    static void readUserData() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите данные (Фамилия Имя Отчество Дата рождения Номер телефона Пол):");
        String input = scanner.nextLine();

        String[] data = input.split(" ");
        if (data.length != RequiredDataQty) {
            throw new InputMismatchException("Неверное количество данных");
        }

        String lastName = data[0];
        String firstName = data[1];
        String middleName = data[2];
        LocalDate birthDate = Parsers.parseBirthDate(data[3]);
        long phoneNumber = Parsers.parsePhoneNumber(data[4]);
        char gender = Parsers.parseGender(data[5]);

        String fileName = lastName +".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            String userData = "<" + lastName + ">" + "<" + firstName + ">" + "<" + middleName + ">" + "<" + birthDate + ">" + "<" +
                    phoneNumber + ">" + "<" + gender + ">";
            writer.write(userData);
            writer.newLine();
        }
        catch (IOException e){
            throw new IOException("Запись в файл не удалась. Проверьте путь и контакт" + e);
        }
        System.out.println("Данные успешно записаны в файл " + fileName);
    }
}
