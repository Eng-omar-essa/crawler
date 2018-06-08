/**
 * 
 */
package io.crawler.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.junit.Before;
import org.junit.Test;

import io.scalable.crawle.CrawlerOperation;

/**
 * @author Omar Issa
 *
 */
public class TestCrawlerOperation {
	final String GOOGLE_SEARCH_URL = "https://www.google.com/search";
	private List<String> filesScript;
	private Map<String, String> listOfUrl;
	private CrawlerOperation crawler;
	private Map<String, String> result;

	@Before
	public void befor() {

		filesScript = new ArrayList<>();
		listOfUrl = new HashMap<>();
		crawler = new CrawlerOperation();
		result = new HashMap<>();

	}

	/**
	 * Test extract result from search 
	 * @throws IOException
	 */
	@Test
	public void tesEtextractResultFromGoogleSerachByEnterKeyWord() throws IOException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter the search term.");
		String keyWord = scanner.nextLine();
		System.out.println("Please enter the number of results. Example: 5 10 20");
		int num = scanner.nextInt();
		scanner.close();
		Map<String, String> result = crawler.extractResultFromGoogleSerachByEnterKeyWord(keyWord, num);
		assertNotNull(result);

	}

	/**
	 * Test download pages from url
	 * @throws Exception
	 */
	@Test
	public void testDownloadPage() throws Exception {
		crawler.downloadPage(result);
		String path = "data/";
		boolean isDirectoryEmpty = false;
		File file = new File(path);
		if (file.isDirectory()) {
			if (file.list().length > 0) {
				isDirectoryEmpty = false;

			} else {

				isDirectoryEmpty = true;
			}
		}

		assertEquals(isDirectoryEmpty, true);
		assertEquals(file.list().length, listOfUrl.size());

	}

	/**
	 * Test get Js Lib from url
	 * @throws IOException
	 */

	@Test
	public void testgetTopJavaScriptLibaray() throws IOException {
		filesScript = new ArrayList<>();
		for (Map.Entry<String, String> entry : listOfUrl.entrySet()) {
			String url = entry.getValue();
			filesScript = crawler.getTopJavaScriptLibaray(url);
		}
		for (String fileName : filesScript) {
			System.out.println(fileName);
		}
		assertNotNull(filesScript);
	}

}
