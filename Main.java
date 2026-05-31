import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Main {
    public static void main(String[] args) {

        System.out.println("Найдите в списке целых чисел 3-е наибольшее число (пример: 5 2 10 9 4 3 10 1 13 => 10)");
        Stream<Integer> intStream = Stream.of(5, 2, 10, 9, 4, 3, 10, 1, 13);
        System.out.println(
                intStream.sorted(Comparator.reverseOrder())
                        .skip(2)
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("ошибка")));
        System.out.println();

        System.out.println("Найдите в списке целых чисел 3-е наибольшее «уникальное» число (пример: 5 2 10 9 4 3 10 1 13 => 9, в отличие от прошлой задачи здесь разные 10 считает за одно число)");
        Stream<Integer> intStream2 = Stream.of(5, 2, 10, 9, 4, 3, 10, 1, 13);
        System.out.println(
                intStream2.distinct()
                        .sorted(Comparator.reverseOrder())
                        .skip(2)
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("ошибка")));
        System.out.println();

        System.out.println("Имеется список объектов типа Сотрудник (имя, возраст, должность), необходимо получить список имен 3 самых старших сотрудников с должностью «Инженер», в порядке убывания возраста");
        record Employee(String name, int age, String position) {};

        List<Employee> listSource = List.of(
                new Employee("Алексей", 21, "Инженер"),
                new Employee("Вася", 25, "Рабочий"),
                new Employee("Петя", 20, "Директор"),
                new Employee("Вася", 35, "Рабочий"),
                new Employee("Ася", 45, "Рабочий"),
                new Employee("Марина", 25, "Рабочий"),
                new Employee("Динар", 60, "Инженер"),
                new Employee("Вова", 30, "Инженер"),
                new Employee("Инна", 40, "Инженер"));
        listSource.stream().filter(e -> "Инженер".equals(e.position))
                .sorted(Comparator.comparingInt((Employee e) -> e.age).reversed())
                .limit(3)
                .forEach(System.out::println);
        System.out.println();

        System.out.println("Имеется список объектов типа Сотрудник (имя, возраст, должность), посчитайте средний возраст сотрудников с должностью «Инженер»");
        System.out.println(
                listSource.stream().filter(e -> "Инженер".equals(e.position))
                        .mapToInt((Employee e) -> e.age)
                        .average()
                        .orElse(0.0));

        System.out.println("Найдите в списке слов самое длинное");
        Stream<String> sourceStrStream= Stream.of("1", "333", "666666", "777777","55555", "2222");
        System.out.println(sourceStrStream
                .max(Comparator.comparingInt(String::length))
                .orElse("Не удалось найти!!"));
        System.out.println();

        System.out.println("Имеется строка с набором слов в нижнем регистре, разделенных пробелом. Постройте хеш-мапы, в которой будут хранится пары: слово - сколько раз оно встречается во входной строке");
        HashMap<String, Integer> map =
                Arrays.stream("слово1 слово2 слово3 слово2 слово3 слово3".split(" "))
                        .collect(Collectors.toMap(
                                s -> s,
                                s -> 1,
                                Integer::sum,
                                HashMap::new));
        System.out.println(map);
        System.out.println();

        System.out.println("Отпечатайте в консоль строки из списка в порядке увеличения длины слова, если слова имеют одинаковую длины, то должен быть сохранен алфавитный порядок");
        sourceStrStream = Stream.of("в", "б", "а", "вв", "Бб", "аа", "Ввв", "ббб", "ааа");
        System.out.println(sourceStrStream
                .sorted(Comparator
                        .comparingInt(String::length)
                        .thenComparing(String::compareToIgnoreCase)
                ).toList());
        System.out.println();

        System.out.println("Имеется массив строк, в каждой из которых лежит набор из 5 слов, разделенных пробелом, найдите среди всех слов самое длинное, если таких слов несколько, получите любое из них");
        String[] strArray = {"            w11 w12 w13 w14 w15", "w21    w22 w23   w24 w25Long1", "w31 w32Long2     w33 w34 w35"};

        System.out.println(Arrays.stream(strArray)
                .flatMap(line -> Arrays.stream(line.trim().split(" +")))
                .max(Comparator.comparingInt(String::length))
                .orElse("Не найдено!"));
    }
}
