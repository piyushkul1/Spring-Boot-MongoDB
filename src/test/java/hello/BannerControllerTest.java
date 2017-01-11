package hello;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.math.BigInteger;
import java.net.URL;
import java.security.SecureRandom;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BannerControllerTest {

	@LocalServerPort
	private int port;

	private URL base;

	private SecureRandom random = new SecureRandom();

	String randomId = null;

	@Autowired
	private TestRestTemplate template;

	@Before
	public void setUp() throws Exception {
		randomId = new BigInteger(130, random).toString(32);
	}

	@Test
	public void testController() throws Exception {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		this.base = new URL("http://localhost:" + port + "/banner/" + randomId + "/click");

		// POST REQUEST #1
		JSONObject request1 = new JSONObject();
		request1.put("cost", 500);
		HttpEntity<String> entity1 = new HttpEntity<String>(request1.toString(), headers);
		template.exchange(base.toString(), HttpMethod.POST, entity1, String.class);

		// POST REQUEST #2
		JSONObject request2 = new JSONObject();
		request2.put("cost", 300);
		HttpEntity<String> entity2 = new HttpEntity<String>(request2.toString(), headers);
		template.exchange(base.toString(), HttpMethod.POST, entity2, String.class);

		// GET REQUEST
		ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
		assertThat(Integer.parseInt(response.getBody()), equalTo(800));

	}
}
