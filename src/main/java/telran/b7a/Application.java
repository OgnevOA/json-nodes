package telran.b7a;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class Application {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = loadStringFromJson("src/main/java/telran/data/1.json");
		JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, Element.class);
		ArrayList<Element> map = mapper.readValue(jsonData, type);
//		System.out.println(sortFile(map));
		mapper.writeValue(Paths.get("src/main/java/telran/data/sorted.json").toFile(), sortFile(map));
	}

	private static String loadStringFromJson(String path) throws Exception {
		return new String(Files.readAllBytes(Paths.get(path)));
	}

	private static List<List<Element>> sortFile(List<Element> map) {
		List<Element> firstElements = map.stream().filter(e -> e.getPre_requisite() == null)
				.collect(Collectors.toList());
		return firstElements.stream().map(e -> getElementChain(e, map)).collect(Collectors.toList());
	}

	private static List<Element> getElementChain(Element element, List<Element> map) {
		List<Element> res = new ArrayList<>();
		res.add(element);
		Element prevElement = getNextElement(map, element);
		while (prevElement != null) {
			res.add(prevElement);
			prevElement = getNextElement(map, prevElement);
		}
		return res;
	}

	private static Element getNextElement(List<Element> map, Element element) {
		return map.stream().filter(e -> element.getUid().equals(e.getPre_requisite())).findFirst().orElse(null);
	}
}
