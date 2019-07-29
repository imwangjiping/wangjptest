package com.jwong.junit.test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.partitioningBy;
 
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
/**
 * 关于Java8中流的定义，操作的方法汇总
 * 
 * @author Liudong
 */
public class testStream {
	static List<Dish> menu = Arrays.asList(
			new Dish("pork",false,800,Dish.Type.MEAT),
			new Dish("beef",false,700,Dish.Type.MEAT),
			new Dish("chichken",false,400,Dish.Type.MEAT),
			new Dish("french fries",true,530,Dish.Type.OTHER),
			new Dish("rice",true,350,Dish.Type.OTHER),
			new Dish("season fruit",true,120,Dish.Type.OTHER),
			new Dish("pizza",true,550,Dish.Type.OTHER),
			new Dish("prawns",false,300,Dish.Type.FISH),
			new Dish("salmon",false,450,Dish.Type.FISH)
			);
	public static void useSteam() {
		List<String> ThreeHighCaloricDishNames = menu.stream()
										 .filter( d -> d.getCalories() > 300) //Java编译器会从上下文中推断出用什么函数式接口来配合lambda表达式，即可以推断出合适的lambda签名
										 .map( d -> d.getName())
										 .limit(3)
										 .collect(toList());
		//stream()：得到一个流
		//filter,map,limit对流的操作，返回另一个流
		//collect，返回一个结果
		
		//lambda使用的条件是函数式接口，而filter接受一个Predicted函数接口，Predicted函数式接口已经由系统帮我们实现，
		//这里的d.getCalories()是行为的实现过程中，我们使用的一个外部方法，和函数式接口无关；这里的d.getCalories()>300只是行为而已；
		//不可混淆以为Dish需要提供一个函数接口。。。；
		System.out.println(ThreeHighCaloricDishNames);
	}
	//limit, map
	public static void midleOperator() {
		List<String> names =
				menu.stream()
				.filter(d -> {
					System.out.println("filtering " + d.getName());
					return d.getCalories()>300;
				})
				.map(d -> {
					System.out.println("mapping " + d.getName());
					return d.getName();
				})
				.limit(3)
				.collect(toList());
		System.out.println(names);
	}
	//distinct
	public static void distincttest() {
		List<Integer> numbers = Arrays.asList(1,2,1,3,3,1,2,5,8,200);
		numbers.stream()
			   .filter( i -> i % 2 == 0)
			   .distinct()
			   .forEach(System.out::println);
 
	}
	//映射：map,map方法会接受一个函数作为参数，这个函数会被映射到每个元素上，并将其映射为一个新的元素；
	public static  void toMapping() {
		// TODO Auto-generated method stub
		List<String> dishName = menu.stream()
				.map(Dish::getName)
				.collect(toList());
	}
	public static void toMultipMapping() {
		List<String> words =Arrays.asList("hello","world");
		//使用map，返回的流是一个整体，即Stream<String[]>类型的，里面的元素为  0号元素为 hello，1号元素为world;
		List<String[]> Characters = words.stream()
				.map(word -> word.split(""))
				.distinct()
				.collect(toList());
		
		System.out.println(Characters);
		
		//使用flatMap,将数组映射成流的内容；
		List<String> uniqueCharacters = words.stream()
				.map(word -> word.split(""))
				.flatMap(Arrays::stream)
				.distinct()
				.collect(Collectors.toList());
		
		System.out.println(uniqueCharacters);	
	}
	//anyMatch方法返回一个boolean，检查行为至少匹配一个元素，是终端操作 ；
	//allMatch方法返回一个boolean，检查行为是否全部满足要求，是终端操作；
	//noneMath方法返回一个boolean,检查行为是否没有一个元素u，是终端操作；
	public static void anyMatch() {
		// TODO Auto-generated method stub
		if(menu.stream().anyMatch(Dish::isVegetarian)) {
			System.out.println("The menu is vegetarian friendsly");
		}
		if(menu.stream().allMatch(d -> d.getCalories() < 500)) {
			System.out.println("the all menu is lg 1000");
		}
		if(menu.stream().noneMatch(d -> d.getCalories() >=1000)) {
			System.out.println("no  match");
		}
	}
	//寻找第一个元素
	public static void findElement() {
		// TODO Auto-generated method stub
		List<Integer> number = Arrays.asList(1,2,3,4,5);
		Optional<Integer> firstSquareDivisibleByThree = 
				number.stream()
				.map(x -> x* x)
				.filter(x -> x % 3 == 0)
				.findFirst();
		System.out.println(firstSquareDivisibleByThree);
	}
	//归约： reduce操作将流流中所有元素反复结合起来，得到一个值；
	public static  void toSum() {
		// TODO Auto-generated method stub
		List<Integer> number = Arrays.asList(1,2,3,4,5);
		int  sum = number.stream().reduce(0, (a,b) -> a + b);
		System.out.println(sum);
	}
	//数值流
	//mapToInt会从没到菜中提取热量，并返回一个IntStream,然后可以调用IntStream接口中定义的sum方法；
	public static void valueStream() {
		int calories = menu.stream()
				.mapToInt(Dish::getCalories)
				.sum();
		
		//将数值流在转换会对象流
		IntStream inStream = menu.stream().mapToInt(Dish::getCalories);
		Stream<Integer> stream = inStream.boxed();
		
		System.out.println(calories);
	}
	//构建流
	public void constructStream() {
		//由值创建流
		Stream<String> stream = Stream.of("java " ,"c++","python","c","vb");
		stream.map(String::toUpperCase).forEach(System.out::println);
		//由数组创建流
		int [] numbers = {1,2,3,9,10};
		int sum = Arrays.stream(numbers).sum();
		//由文件生成流
		long uniqueWords = 0;
		try(Stream<String> lines = Files.lines(Paths.get("data.txt"),Charset.defaultCharset())){
			uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
					.distinct()
					.count();
		}catch(IOException e) {	
		}
		//无限流
		//iterate方法接受一个初始值，还有一个依次应用在每个产生的新值上的Lambda，
		Stream.iterate(0, n -> n+2)
		      .limit(10)
		      .forEach(System.out::println);
		//generate方法，接受一个Supplier<T> 类型的Lambda提供的新值；
		Stream.generate(Math::random)
		      .limit(10)
		      .forEach(System.out::println);
	}
	public enum CaloricLevel {DIET,NORMAL,FAT};
	//分组
	public static void toGroupingBy() {
		//一级分类
		Map<CaloricLevel,List<Dish>> dishesByCaloricLevel = menu.stream().collect(
				groupingBy(dish->{
					if(dish.getCalories() <= 400) return CaloricLevel.DIET;
					else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
					else return CaloricLevel.FAT;
				}));
		System.out.println(dishesByCaloricLevel);
		//多级分类
		Map<Dish.Type,Map<CaloricLevel,List<Dish>>> dishesByTypeCaloricLevel = 
				menu.stream().collect(
					groupingBy(Dish::getType,
							groupingBy(dish -> {
								if(dish.getCalories() <= 400) return CaloricLevel.DIET;
								else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
								else return CaloricLevel.FAT;
							})));
		System.out.println(dishesByTypeCaloricLevel);
	}
	//分类
	public static void classcify() {
		//分区：是分组的特殊情况，有一个谓词最为分类函数，分区函数返回一个布尔值，意味着得到的分组Map的键类型是boolean；最多可以分为两组
		Map<Boolean,List<Dish>> partitionedMenu = 
				menu.stream().collect(partitioningBy(Dish::isVegetarian));
		System.out.println(partitionedMenu);
	}
	public static void main(String[] args) {
		//testStream.useSteam();
		//testStream.midleOperator();
		//testStream.distincttest();
		//toMultipMapping();
		//anyMatch();
		//findElement();
		//toSum();
		//valueStream();
		//toGroupingBy();
		//classcify();
		menu.stream()
				.sorted(Comparator.comparing(Dish::getCalories))
				.collect(toList())
				.forEach(System.out::println);
	}
	
}
