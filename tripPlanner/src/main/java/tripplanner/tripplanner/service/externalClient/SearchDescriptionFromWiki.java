package tripplanner.tripplanner.service.externalClient;

import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tripplanner.tripplanner.model.Item;

@Service
public class SearchDescriptionFromWiki {

  private static final String HOST = "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=extracts&titles=";


  public void searchDescription(Item item) throws JSONException {

    // Send request to wiki API to retrieve events
    HttpHeaders headers = new HttpHeaders();

    HttpEntity<String> entity = new HttpEntity(headers);

    RestTemplate restTemplate = new RestTemplate();

    String url = HOST + item.getName();

    ResponseEntity<String> responseEntity =
        restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
    JSONObject jsonObject = new JSONObject(responseEntity.getBody());
    try {
      JSONObject queryInJson = jsonObject.getJSONObject("query");
      JSONObject page = queryInJson.getJSONObject("pages");
      Iterator result = page.keys();
      JSONArray jsonArray = new JSONArray();

      while (result.hasNext()) {
        String key = (String) result.next();
        jsonArray.put(page.get(key));
      }

      JSONObject res = jsonArray.getJSONObject(0);

      String s = res.getString("extract");
      s = s.substring(0, s.indexOf(".", s.indexOf(".") + 1));
      s = s.toLowerCase()
        .replaceAll("<p>", "") .trim();
      s = s.replaceAll("</p>", "").replaceAll("<b>", "").replaceAll("</b>", "").trim();
      s = s.replaceAll("\n", "");
      s = s.replaceAll("<i>", "").replaceAll("</i>", "");
      s = s.replaceAll("<span>", "").replaceAll("</span>", "");
      s = s.replaceAll("<sup>","").replaceAll("</sup>","");
      s = s.substring(s.indexOf(">")+1);
      s = s.substring(s.indexOf(">")+1);
      s = s.substring(0, 1).toUpperCase() + s.substring(1);
      if (s.length() > 255) {
        s = s.substring(0, 255);
      }
      System.out.println(s);
      item.setDescription(s);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
