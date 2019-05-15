import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class File {

  public static void main(String args[]) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Введите количество записей в телефонную книгу:");
    int numberOfRecords = scanner.nextInt();
    HashMap<Integer, String> names = new HashMap<>();
    HashMap<Integer, Integer> phones = new HashMap<>();
    try {
      OutputStream output = new FileOutputStream("file.txt"); // Создание текстового файла
      System.out.println("Введите телефон, затем имя");

      for (int i = 0; i < numberOfRecords; i++) {
        phones.put(i, scanner.nextInt());
        names.put(i, scanner.next());
      }

      for (int i = 0; i < numberOfRecords; i++) {
        char[] phone = phones.get(i).toString().toCharArray();
        for (int j = 0; j < phone.length; j++) {
          output.write(phone[j]);
        }
        output.write('-');
        char[] name = names.get(i).toCharArray();
        for (int j = 0; j < name.length; j++) {
          output.write(name[j]);
        }
        output.write('\n');

      }
      output.close();
      HashMap<Integer, List<Character>> answer = new HashMap<>();
      InputStream input = new FileInputStream("file.txt");
      int size = input.available();
      System.out.println("Введите первые цифры номеров, поиск которых осуществляется:");
      int f = scanner.nextByte();
      int s = scanner.nextByte();
      char first = (char)(f + '0');
      char second = (char)(s + '0');
      int counter = 0;
      char lastChar = ' ';
      for (int j = 0; j < size; j++) {
        List<Character> chars = new ArrayList<>();
        char newChar = (char)input.read();
        if ((newChar == first || newChar == second) && (j == 0 || lastChar == '\n')) {
          chars.add(newChar);
          while(newChar != '\n' && j < size) {
            j++;
            newChar = (char)input.read();
            chars.add(newChar);
          }
          answer.put(counter, chars);
          counter++;
        }
        lastChar = newChar;
      }
      input.close();

      for (int i = 0; i < answer.size(); i++) {
        List<Character> data = answer.get(i);
        for (int j = 0; j < data.size(); j++) {
          System.out.print(data.get(j));
        }
      }
    } catch(IOException e) {
      System.out.print("Exception");
    }
  }
}