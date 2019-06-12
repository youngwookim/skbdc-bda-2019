package com.example;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import com.example.avro.IexTrading;
import com.opencsv.CSVParser;
import com.opencsv.CSVReader;

import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import pl.zankowski.iextrading4j.api.stocks.Quote;
import pl.zankowski.iextrading4j.client.IEXApiClient;
import pl.zankowski.iextrading4j.client.IEXTradingClient;
import pl.zankowski.iextrading4j.client.rest.request.stocks.QuoteRequestBuilder;

public class KafkaMessageGen {
	public static final String SYMBOLS = "/Users/ywkim/workspace/skbdc-bda-2019/data/nasdaq-listed-symbols.csv";

	public static void main(String[] args) throws IOException {
		List<List<String>> records = new ArrayList<List<String>>();
		CSVReader csvReader = new CSVReader(new FileReader(SYMBOLS), CSVParser.DEFAULT_SEPARATOR,
				CSVParser.DEFAULT_QUOTE_CHARACTER, 1);
		String[] values = null;
		while ((values = csvReader.readNext()) != null) {
			records.add(Arrays.asList(values));
		}

		String brokers = "localhost:9092";
		String registry = "http://localhost:8081";
		String topic = "iextrading";

		Properties producerProps = new Properties();
		producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokers);
		producerProps.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, registry);
		producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.common.serialization.StringSerializer");
		producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
				"io.confluent.kafka.serializers.KafkaAvroSerializer");

		final Producer<String, IexTrading> producer = new KafkaProducer<String, IexTrading>(producerProps);
		final Callback callback = new MyProducerCallback();

		try {
			for (List<String> r : records) {
				String symbol = r.get(0);
				System.out.println(String.format("Symbol=%s", symbol));
				final IEXApiClient iexTradingClient = IEXTradingClient.create();

				IexTrading iex = new IexTrading();
				iex.setSymbol(symbol);
				try {
					final Quote quote = iexTradingClient
							.executeRequest(new QuoteRequestBuilder().withSymbol(symbol).build());
					if (quote != null) {
						System.out.println(quote);
						iex.setCompanyName(quote.getCompanyName());
						iex.setPrimaryExchange(quote.getPrimaryExchange());
						iex.setSector(quote.getSector());
						iex.setCalculationPrice(quote.getCalculationPrice());
						iex.setOpen(quote.getOpen().doubleValue());
						iex.setOpenTime(quote.getOpenTime().longValue());
						iex.setClose(quote.getClose().doubleValue());
						iex.setCloseTime(quote.getCloseTime());
						iex.setHigh(quote.getHigh().doubleValue());
						iex.setLow(quote.getLow().doubleValue());
						iex.setLatestPrice(quote.getLatestPrice().doubleValue());
					}
				} catch (Exception e) {
					// ignore unknown symbol or network errors
					e.printStackTrace();
				}

				ProducerRecord<String, IexTrading> producerRecord = new ProducerRecord<String, IexTrading>(topic, iex);
				try {
					producer.send(producerRecord, callback);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		} finally {
			producer.flush();
			producer.close(5, TimeUnit.SECONDS);
		}
	}

	private static class MyProducerCallback implements Callback {
		public void onCompletion(RecordMetadata metadata, Exception exception) {
			System.out.println("#### received callback [" + metadata + "], exception: [" + exception + "]");
		}

	}
}
