package com.javatrain.conronovirustracker.services;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import com.javatrain.conronovirustracker.models.LocationStats;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CoronovirusDataService {

    private static String VIRUS_DATA_VALUE = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_daily_reports_us/01-01-2021.csv";

    private List<LocationStats> allstats = new ArrayList<>();
    

    public List<LocationStats> getAllstats() {
        return allstats;
    }


    public void setAllstats(List<LocationStats> allstats) {
        this.allstats = allstats;
    }


    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchVirusData() throws IOException, InterruptedException {

        List<LocationStats> newStats = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(VIRUS_DATA_VALUE))
                        .build();

        HttpResponse<String> httpresponse = client.send(request, HttpResponse.BodyHandlers.ofString());

        StringReader csvBodyReader = new StringReader(httpresponse.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
        for (CSVRecord record : records) {

            LocationStats locationstat = new LocationStats();
            locationstat.setState(record.get("Province_State"));
            locationstat.setCountry(record.get("Country_Region"));
            locationstat.setTotalDeaths((Integer.parseInt(record.get("Deaths"))));
            

            newStats.add(locationstat);
            

        }

    this.allstats=newStats;

    }

    
}
