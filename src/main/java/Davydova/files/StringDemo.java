import java.util.Arrays;

public class StringDemo {


    public static void main(String[] args) {

        //1 Получить длину строки
        String str1 = "Get string length";
        System.out.println("1 Получить длину строки \n" + "Длина строки = " + str1.length());

        //2 Сравнить 2 строки без учета регистра
        String str2 = "GeT StrinG Length";
        System.out.println("2 Сравнить 2 строки без учета регистра\n" + str1.toLowerCase().equals(str2.toLowerCase()));

        //3 Создать новую строку с помощью конструктора и занести ее в пул литералов
        String str3 = new String("Get string length");
        String str4 = str3.intern();
        System.out.println("3 Создать новую строку с помощью конструктора и занести ее в пул литералов");

        if (str1==str4) {
            System.out.println("Строка занесена в пул литералов");
        }else System.out.print("Строка не занесена в пул литералов");

        //4 Получить из строки массив символов
        String hello = "Hello";
        char[] charArray = new char[hello.length()];
        System.out.println("4 Получить из строки массив символов, строка: " + hello);
        for (int i = 0; i < hello.length(); i++) {
            charArray[i] = hello.charAt(i);
            System.out.println ("Элемент массива № " + i + " = " + charArray[i]);
        }
        //char[] charArray = hello.toCharArray();
        //System.out.println ("4 Получить из строки массив символов, строка: ");
        //System.out.println ("Строка " + hello + " в виде массива символов: " + Arrays.toString(charArray));

        //5 Получить из строки массив байтов
        byte[] byteArray = hello.getBytes();
        System.out.println("5 Получить из строки массив байтов");
        System.out.println ("Строка " + hello + " в виде массива байтов: " + Arrays.toString(byteArray));

        //6 Привести строку к верхнему регистру
        System.out.println ("6 Привести строку к верхнему регистру\n" + str1.toUpperCase());

        //7 Найти первую позицию символа "a" в строке
        String str5 = "Найти первую позицию символа а";
        System.out.println ("7 Найти первую позицию символа \"a\" в строке\n" + str5);
        int indexFirst = str5.indexOf("а");
        if (indexFirst == -1) {
            System.out.println("Символ \"a\" не найден");
        } else {
            System.out.println ("Позиция символа \"а\" = " + indexFirst);
        }

        //8 Найти последнюю позицию символа "а" в строке
        String str6 = "Найти последнюю позицию символа а";
        System.out.println ("8 Найти последнюю позицию символа \"a\" в строке\n" + str6);
        int indexLast = str6.lastIndexOf("а");
        if (indexLast == -1) {
            System.out.println ("Символ \"a\" не найден");
        }else{
            System.out.println ("Позиция символа \"а\" = " + indexLast);
        }

        //9 Проверить содержит ли строка слово "Sun"
        String str7 = "Sunglasses";
        String subString = "Sun";
        boolean foundIt = false;
        int max = str7.length() - subString.length();

        test:
        for (int i = 0; i < max; i++) {
            int n = subString.length();
            int j = 0;
            int k = 0;
            while (n-- != 0) {
                if (subString.charAt(j++) != str7.charAt(k++)) {
                    continue test;
                }
            }
            foundIt = true;
            break test;
        }

        System.out.println ("9 Проверить содержит ли строка слово \"Sun\"");
        System.out.println ("Строка содержит слово \"Sun\": " + foundIt);
        //System.out.println ("Строка содержит слово \"Sun\": " + sunglasses.contains(subString));

        //10 Проверить оканчивается ли строка на "Oracle"
        String endsOracle = ";fjlk; sa Oracle";

        System.out.println ("10 Проверить оканчивается ли строка на \"Oracle\"");
        System.out.println (endsOracle.endsWith("Oracle"));

        //11 Проверить начинается ли строка на "Java"
        String startsJava = "Java; sdafjl";

        System.out.println ("11 Проверить начинается ли строка на \"Java\"");
        System.out.println (startsJava.startsWith("Java"));

        //12 Заменить все символы "а" в строке на символ "о"
        String changeMyA = "Замени в фразе все а на о";
        System.out.println ("12 Заменить все символы \"а\" в строке на символ \"о\"");
        System.out.println (changeMyA.replace("а","о"));

        //13 Получить подстроку с 44 символа по 90 символ
        String str8 = "Chicken that everyone adores. French created the most popular dish in Russia. It is called friсasse.";
        System.out.println ("13 Получить подстроку с 44 символа по 90 символ");
        if (str8.length() < 90) System.out.println("Длина строки меньше 90 символов");
        else System.out.println (str8.substring(44,90));

        //14 Разбить строку по символу пробел (т.е. чтобы каждое слово было отдельным элементом массива)
        String str9 = "Red Green Blue Orange Yellow";
        String[] colors = str9.split(" ");

        System.out.println ("14 Разбить строку по символу пробел (т.е. чтобы каждое слово было отдельным элементом массива)");
        for (String color : colors) {
            System.out.println(color);
        }

        //15 Поменять последовательность символов в строке на обратную
        String directOrder = "I like bicycle";
        char[] reverse = new char[directOrder.length()];
        int j = 0;
        for (int i = directOrder.length()-1; i >= 0; i--) {
            reverse[i] = directOrder.charAt(j++);
        }

        System.out.println ("15 Поменять последовательность символов в строке на обратную");
        System.out.println (Arrays.toString(reverse));
        
    }
}
	
	