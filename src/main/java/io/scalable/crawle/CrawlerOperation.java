/**
 * 
 */
package io.scalable.crawle;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author Omar Issa
 *
 */
public class CrawlerOperation {
	public static final String GOOGLE_SEARCH_URL = "https://www.google.com/search";
	private List<String> filesScript;
	private Map<String, String> listOfUrl;
	
	/**
	 * This Method to get List of Url searched by google
	 * @param keyWord
	 * @param num
	 * @return Map Of String
	 * @throws IOException
	 */

	public Map<String, String> extractResultFromGoogleSerachByEnterKeyWord(String keyWord, int num) throws IOException {

		listOfUrl = new HashMap<>();
		String searchURL = GOOGLE_SEARCH_URL + "?q=" + keyWord + "&num=" + num;

		Document doc = Jsoup.connect(searchURL).userAgent("Mozilla/5.0").get();

		Elements results = doc.select("h3.r > a");

		for (Element result : results) {
			String linkHref = result.attr("href");
			String url = linkHref.substring(7, linkHref.indexOf("&"));
			String title = result.text();
			System.out.println("Text::" + title + ", URL::" + url);
			listOfUrl.put(title, url);

		}

		return listOfUrl;
	}
	
	/**
	 * This method used to download pages form url . All files will saved at data folder
	 * @param listOfUrl
	 * @throws Exception
	 */

	public void downloadPage(Map<String, String> listOfUrl) throws Exception {

		for (Map.Entry<String, String> entry : listOfUrl.entrySet()) {
			String url = entry.getValue();
			String fileName = entry.getKey();
			System.out.println(url);
			System.out.println(fileName);
			final Response response = Jsoup.connect(url).userAgent("Mozilla/5.0").execute();
			final Document doc = response.parse();
			String path = "data/";
			final File f = new File(path + fileName + ".html");
			FileUtils.writeStringToFile(f, doc.outerHtml() + ".html", "UTF-8");
		}

	}

	/**
	 * This method used to extract java script libs . return list of js files
	 * @param url
	 * @return list of String 
	 * @throws IOException
	 */
	public List<String> getTopJavaScriptLibaray(String url) throws IOException {
		filesScript = new ArrayList<>();
		Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0").get();
		Elements results = doc.select("script");

		for (Element result : results) {
			String linkHref = result.attr("src");

			System.out.println(linkHref);

			filesScript.add(linkHref);

		}
		return filesScript;

	}

}
