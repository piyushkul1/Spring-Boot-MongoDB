package hello;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class BannerController {

	@Autowired
	private BannerRepository repository;

	@RequestMapping(value = "/banner/{id}/click", method = RequestMethod.POST)
	public String postCost(@PathVariable String id, @RequestBody String requestBody) throws Exception {
		Integer cost = extractCost(requestBody);
		Banner banner = repository.findByBannerId(id);
		if (null == banner) {
			repository.save(new Banner(id, cost));
		} else {
			banner.updateCost(cost);
			repository.save(banner);
		}
		return "Post complete!";
	}

	@RequestMapping(value = "/banner/{id}/click", method = RequestMethod.GET)
	public String getCost(@PathVariable String id) {
		return repository.findByBannerId(id).getCost().toString();
	}

	@SuppressWarnings("unchecked")
	private Integer extractCost(String requestBody) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = mapper.readValue(requestBody, Map.class);
		Integer cost = Integer.parseInt(map.get("cost").toString());
		return cost;
	}

}
