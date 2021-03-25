  public class HelloWorldApp {
	
	public static void main(String[] args) {
		System.out.println("Hello World!");
		
		StringDemo demo = new StringDemo();

		//1 Получить длину строки
		demo.getLength("Get string length");

		//2 Сравнить 2 строки без учета регистра
		demo.compareStrings("Get string length","GeT StrinG Length");

		//3 Создать новую строку с помощью конструктора и занести ее в пул литералов
		demo.addToStringPool(new String("Get string length"));

		//4 Получить из строки массив символов
		demo.getArrayOfChars("Hello");

		//5 Получить из строки массив байтов
		demo.getArrayOfBytes("Hello");

		//6 Привести строку к верхнему регистру
		demo.getToUpperCase("Привести строку к верхнему регистру");

		//7 Найти первую позицию символа "a" в строке
		demo.findFirstPosition("Найти первую позицию символа а", 'а');

		//8 Найти последнюю позицию символа "а" в строке
		demo.findLastPostion("Найти последнюю позицию символа а",'а');

		//9 Проверить содержит ли строка слово "Sun"
		demo.isContained("Sunglasses", "Sun");

		//10 Проверить оканчивается ли строка на "Oracle"
		demo.isLastSubString(";fjlk; sa Oracle", "Oracle");

		//11 Проверить начинается ли строка на "Java"
		demo.isFirstSubString("Java; sdafjl", "Java");

		//12 Заменить все символы "а" в строке на символ "о"
		demo.changeChar("Заменить все символы а в строке на символ о",'а', 'o');

		//13 Получить подстроку с 44 символа по 90 символ
		demo.getSubString("Chicken that everyone adores. French created the most popular dish in Russia. It is called friсasse.");

		//14 Разбить строку по символу пробел (т.е. чтобы каждое слово было отдельным элементом массива)
		demo.splitString("Red Green Blue Orange Yellow");

		//15 Поменять последовательность символов в строке на обратную
		demo.changeOrder("I like bicycle");
		}
}
