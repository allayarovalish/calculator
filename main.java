import java.util.*;

class Main{
    public static String calc(String input){
        try{
            String[] parts = input.split(" ");
            if (parts.length != 3)
                throw new IllegalArgumentException("Неверный формат ввода");
            boolean isRoman = isRoman(parts[0]) && isRoman(parts[2]);
            int num1 = convertToNumber(parts[0], isRoman);
            int num2 = convertToNumber(parts[2], isRoman);
            char operator = parts[1].charAt(0);

            int result = calculate(num1, num2, operator);
            return convertToOutputFormat(result, isRoman);

        }catch (NumberFormatException e){
            return "Числа должны быть одного формата";
        }catch (IllegalArgumentException e){
            return e.getMessage();
        }
    }
    private static boolean isRoman(String input){
        return input.matches("[IVXCDM]+");
    }
    private static int convertToNumber(String romanOrArabic, boolean isRoman){
        if (isRoman){
            return RomanToDecimal(romanOrArabic);
    } else{
        int num = Integer.parseInt(romanOrArabic);
        if (num<1 || num>10)
            throw new IllegalArgumentException("Число доджно быть от 1 до 10");
        return num;
    }
}
private static int RomanToDecimal(String roman){
    Map<Character, Integer> map = new HashMap<>();
    map.put('I',1);
    map.put('V',5);
    map.put('X',10);
    map.put('L',50);
    map.put('C',100);
    map.put('D',500);
    map.put('M',1000);
    int decimal = 0;
    int prevValue = 0;
    for(int i = roman.length() - 1; i>=0; i--){
        int currValue = map.get(roman.charAt(i));
        if (currValue<prevValue){
            decimal-=currValue;
        } else{
            decimal+=currValue;
        }
        prevValue = currValue;
    }
    return decimal;
}
private static int calculate(int num1, int num2, char operator){
        switch(operator){
        case '+':
        return num1+num2;
         case '-':
                 return num1 - num2;
         case '*':
                 return num1*num2;
         case '/':
                 if (num2==0)
        throw new IllegalArgumentException("Деление на 0 невозможно");
              return num1/num2;
default:
        throw new IllegalArgumentException("Неверная операция");
      }
              }
private static String convertToOutputFormat(int num, boolean isRoman){
    if (isRoman){
        if(num<=0)
            throw new IllegalArgumentException("Ошибка, должно быть положительное число");
        return DecimalToRoman(num);
    } else{
        return String.valueOf(num);
    }
}
private static String DecimalToRoman(int num){
    if (num<1)
        throw new IllegalArgumentException("Ошибка, должно быть положительное число");
    String[] romanSymbols = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};
    int[] decimalValues = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
    StringBuilder result = new StringBuilder();
    int i = 12;
    while (num>0){
        int div = num/decimalValues[i];
        num%=decimalValues[i];
        while(div-->0)
            result.append(romanSymbols[i]);
        i--;
    }
    return result.toString();
}
public static void main(String[] args){
    Scanner scanner = new Scanner(System.in);
    System.out.println("Введите выражение (в формате 1 + 2):");
    String input = scanner.nextLine();
    System.out.println(calc(input));
}
}
