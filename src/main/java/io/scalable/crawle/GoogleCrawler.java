/**
 * 
 */
package io.scalable.crawle;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Omar Issa
 *
 */
public class GoogleCrawler {
	
	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter the search term.");
		String searchTerm = scanner.nextLine();
		System.out.println("Please enter the number of results. Example: 5 10 20");
		int num = scanner.nextInt();
		scanner.close();
		
		CrawlerOperation crawler = new CrawlerOperation();
		Map<String, String> listOfURL = crawler.extractResultFromGoogleSerachByEnterKeyWord(searchTerm, num);
		
		crawler.downloadPage(listOfURL);
		for (String keys : listOfURL.keySet()) {
			System.out.println(keys);
		}

		for (Map.Entry<String, String> entry : listOfURL.entrySet()) {
			String url = entry.getValue();
			System.out.println(url);
			crawler.getTopJavaScriptLibaray(url);
		}
	}
}
