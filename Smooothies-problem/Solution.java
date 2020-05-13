import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Solution {

	static Map<String, List<String>> smoothieMap = new LinkedHashMap<String, List<String>>();

	public static void main(String args[]) {

		smoothieMap.put("Classic", Arrays.asList("strawberry", "banana", "pineapple", "mango", "peach", "honey"));
		smoothieMap.put("Freezie",
				Arrays.asList("blackberry", "blueberry", "black currant", "grape juice", "frozen yogurt"));
		smoothieMap.put("Greenie", Arrays.asList("green apple", "lime", "avocado", "spinach", "ice", "apple juice"));
		smoothieMap.put("Just Desserts", Arrays.asList("banana", "ice cream", "chocolate", "peanut", "cherry"));

		System.out.println(ingredients("Classic,-strawberry,-peanut"));

	}

	public static List ingredients(String order) {

		if (order.isEmpty())
			throw new IllegalArgumentException();

		List<String> smoothieData = getTokens(order);

		String menuOption = smoothieData.get(0);

		List<List> list = smoothieMap.entrySet().stream().filter(mapData -> menuOption.equals(mapData.getKey()))
				.map(Map.Entry::getValue).collect(Collectors.toList());

		smoothieData.remove(0); // Removing menu data from the list and we can use the same list for ingredients

		List<String> mainMenuList = (List<String>) list.stream().flatMap(List::stream).collect(Collectors.toList());
		
		
		List<String> incredientsList = smoothieData.stream().parallel().map(item -> {
			if (item.contains("-")) {
				item = item.substring(1);
			}

			return item;
		}).collect(Collectors.toList());

		
		
		
		List<String> findOddOneIngredientFromOrder = incredientsList.stream()
				.filter(o1 -> mainMenuList.stream().noneMatch(o2 -> o2.equals(o1))).collect(Collectors.toList());

		
//		if (!findOddOneIngredientFromOrder.isEmpty())
//			throw new IllegalArgumentException();
//		
		System.out.println("Filtering and preparing  order");

		List<String> orderDetails = mainMenuList.stream().filter(x -> !incredientsList.contains(x))
				.collect(Collectors.toList());

		return orderDetails;

	}

	public static List<String> getTokens(String tokenString) {
		List<String> tokens = new ArrayList<>();
		StringTokenizer tokenizer = new StringTokenizer(tokenString, ",");
		while (tokenizer.hasMoreElements()) {
			tokens.add(tokenizer.nextToken());
		}
		return tokens;
	}

}