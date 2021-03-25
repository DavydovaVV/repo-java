/**
 * This is a class with methods for operations with String
 * 
 * @author DavydovaVV
 * @version 1.1 03/25/2021
 */

import java.util.Arrays;

public class StringDemo {

	/** 
	* 1 Получить длину строки
	*@param string is the string to get the length of
	*/
        public void getLength (String string) {
        	System.out.println("1 Получить длину строки \n" + "Длина строки = " + string.length());
	}
	
	/** 
	* 2 Сравнить 2 строки без учета регистра
	*@param string1 is the string to be compared
	*@param string2 is the string to be compared
	*/
        public void compareStrings (String string1, String string2) {
        	System.out.println("2 Сравнить 2 строки без учета регистра\n" + string1.toLowerCase().equals(string2.toLowerCase()));
	}
	
	/** 
	*3 Создать новую строку с помощью конструктора и занести ее в пул литералов
	*@param string is the string to be added to String Pool
	*/
        public void addToStringPool (String string) {
        	System.out.println("3 Создать новую строку с помощью конструктора и занести ее в пул литералов");
		string.intern();
        	System.out.println("Строка:\n " + string + "\n внесена в пул строк");
	}
	
	/** 
	*4 Получить из строки массив символов
	*@param string is the string to get array of chars of
	*/
        public void getArrayOfChars (String string) {
		char[] charArray = new char[string.length()];
		System.out.println("4 Получить из строки массив символов, строка: " + string);
		for (int i = 0; i < string.length(); i++) {
		    charArray[i] = string.charAt(i);
		    System.out.println ("Элемент массива № " + i + " = " + charArray[i]);
		}
        //char[] charArray = hello.toCharArray();
        //System.out.println ("4 Получить из строки массив символов, строка: ");
        //System.out.println ("Строка " + hello + " в виде массива символов: " + Arrays.toString(charArray));
	}
	
	/** 
	*5 Получить из строки массив байтов
	*@param string is the string to get array of bytes of
	*/
	public void getArrayOfBytes (String string) {
		byte[] byteArray = string.getBytes();
		System.out.println("5 Получить из строки массив байтов");
		System.out.println ("Строка " + string + " в виде массива байтов: " + Arrays.toString(byteArray));
	}
	
	/** 
	*6 Привести строку к верхнему регистру
	*@param string is the string to be brought to upper case
	*/
	public void getToUpperCase (String string) {
        	System.out.println ("6 Привести строку к верхнему регистру\n" + string.toUpperCase());
	}
	
	/** 
	*7 Найти первую позицию символа "a" в строке
	*@param string is the string to be tested
	*@param a is the char to be found its frist occurance in the string
	*/
        public void findFirstPosition (String string, char a) {
		System.out.println ("7 Найти первую позицию символа \"a\" в строке\n" + string);
		int indexFirst = string.indexOf("а");
		if (indexFirst == -1) System.out.println("Символ \"a\" не найден");
		else System.out.println ("Позиция символа \"а\" = " + indexFirst);
	}
	
	/** 
	*8 Найти последнюю позицию символа "а" в строке
	*@param string is the string to be tested
	*@param a is the char to be found its last occurance in the string
	*/
        public void findLastPostion (String string, char a) {
		System.out.println ("8 Найти последнюю позицию символа " + a + " в строке\n" + string);
		int indexLast = string.lastIndexOf("а");
		if (indexLast == -1) System.out.println ("Символ " + a + " не найден");
		else System.out.println ("Позиция символа " + a + " = " + indexLast);
        }
	
	/** 
	*9 Проверить содержит ли строка слово "Sun"
	*@param string is the string to be tested
	*@param subString is the string to be found in the string
	*/
        public void isContained (String string, String subString) {
		boolean foundIt = false;
		int max = string.length() - subString.length();

		
		for (int i = 0; i < max; i++) {
		    int n = subString.length();
		    int j = 0;
		    int k = 0;
		    while (n-- != 0) {
			if (subString.charAt(j++) != string.charAt(k++)) {
			    continue;
			}
		    }
		    foundIt = true;
		    break;
		}

		System.out.println ("9 Проверить содержит ли строка слово " + subString);
		System.out.println ("Строка содержит слово " + subString + " " + foundIt);
		//System.out.println ("Строка содержит слово " + subString + ": " + string.contains(subString));
	}
	
	/** 
	*10 Проверить оканчивается ли строка на "Oracle"
	*@param string is the string to be tested
	*@param subString is the string to be verified if it ends the string
	*/
        public void isLastSubString (String string, String subString) {

		System.out.println ("10 Проверить оканчивается ли строка на " + subString);
		System.out.println (string.endsWith(subString));
	}
	
	/** 
	*11 Проверить начинается ли строка на "Java"
	*@param string is the string to be tested
	*@param subString is the string to be verified if it starts the string
	*/
        public void isFirstSubString (String string, String subString){

		System.out.println ("11 Проверить начинается ли строка на " + subString);
		System.out.println (string.startsWith(subString));
	}
	
	/** 
	*12 Заменить все символы "а" в строке на символ "о"
	*@param string is the string to be modified
	*@param target is the char to be found and replaced in the string
	*@param replacement is the char to be inserted instead of target in the string
	*/
        public void changeChar (String string, char target, char replacement) {
		System.out.println ("12 Заменить все символы " + target + " в строке на символ " + replacement);
		System.out.println (string.replace(target, replacement));
	}
	
	/** 
	*13 Получить подстроку с 44 символа по 90 символ
	*@param string is the string to be tested with method substring() 
	*/
        public void getSubString (String string) {
		System.out.println ("13 Получить подстроку с 44 символа по 90 символ");
		if (string.length() < 90) System.out.println("Длина строки меньше 90 символов");
		else System.out.println (string.substring(44,90));
	}
	
	/** 
	*14 Разбить строку по символу пробел (т.е. чтобы каждое слово было отдельным элементом массива)
	*@param string is the string to get an array by splitting with " "
	*/
        public void splitString (String string) {
		String[] colors = string.split(" ");

		System.out.println ("14 Разбить строку по символу пробел (т.е. чтобы каждое слово было отдельным элементом массива)");
		for (String color : colors) {
		    System.out.println(color);
		}
	}
	
	/** 
	*15 Поменять последовательность символов в строке на обратную
	*@param string is the string to be reversed
	*/
        public void changeOrder (String string) {
		char[] reverse = new char[string.length()];
		int j = 0;
		for (int i = string.length()-1; i >= 0; i--) {
		    reverse[i] = string.charAt(j++);
		}

		System.out.println ("15 Поменять последовательность символов в строке на обратную");
		System.out.println (Arrays.toString(reverse));
	}
}
	
	
