package com.example;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.opencsv.CSVParser;
import com.opencsv.CSVReader;

import pl.zankowski.iextrading4j.api.stocks.Quote;
import pl.zankowski.iextrading4j.client.IEXCloudClient;
import pl.zankowski.iextrading4j.client.IEXCloudTokenBuilder;
import pl.zankowski.iextrading4j.client.IEXTradingApiVersion;
import pl.zankowski.iextrading4j.client.IEXTradingClient;
import pl.zankowski.iextrading4j.client.rest.request.stocks.QuoteRequestBuilder;

public class IexTradingTest {

	public static void main(String[] args) throws IOException {
		
		final IEXCloudClient iexTradingClient = IEXTradingClient.create(IEXTradingApiVersion.IEX_CLOUD_BETA_SANDBOX,
				new IEXCloudTokenBuilder().withPublishableToken("Tpk_18dfe6cebb4f41ffb219b9680f9acaf2")
						.withSecretToken("Tsk_3eedff6f5c284e1a8b9bc16c54dd1af3").build());
		// Apple Inc.
		final Quote quote = iexTradingClient.executeRequest(new QuoteRequestBuilder().withSymbol("AAPL").build());
		System.out.println(quote);
		
		
//		List<List<String>> records = new ArrayList<List<String>>();
//		CSVReader csvReader = new CSVReader(
//				new FileReader("/Users/ywkim/workspace/skbdc-bda-2019/data/nasdaq-listed-symbols.csv"),
//				CSVParser.DEFAULT_SEPARATOR, CSVParser.DEFAULT_QUOTE_CHARACTER, 1);
//		String[] values = null;
//
//		while ((values = csvReader.readNext()) != null) {
//			records.add(Arrays.asList(values));
//		}
//
//		for (List<String> r : records) {
//			getStockPrice(r.get(0));
//
//		}
	}

	static void getStockPrice(String symbol) {
		final IEXCloudClient iexTradingClient = IEXTradingClient.create(IEXTradingApiVersion.IEX_CLOUD_BETA_SANDBOX,
				new IEXCloudTokenBuilder().withPublishableToken("Tpk_18dfe6cebb4f41ffb219b9680f9acaf2")
						.withSecretToken("Tsk_3eedff6f5c284e1a8b9bc16c54dd1af3").build());
		final Quote quote = iexTradingClient.executeRequest(new QuoteRequestBuilder().withSymbol(symbol).build());
		System.out.println(quote);
	}

}
