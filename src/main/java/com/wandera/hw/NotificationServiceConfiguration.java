package com.wandera.hw;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Splitter;
import com.wandera.hw.api.Notification;
import com.wandera.hw.service.DataStructureWrapper;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.*;

public class NotificationServiceConfiguration extends Configuration {
	@NotEmpty
	private String sampleData = "sampleData";
    private DataStructureWrapper dataStructureWrapper;

	//normally I would use jackson-dataformat-csv, but since I can't lets do it manually...
	public void loadSampleData(){
	    dataStructureWrapper = new DataStructureWrapper();
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();

        try (Scanner scanner = new Scanner(classloader.getResourceAsStream(sampleData), "UTF-8")) {
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                parseStringToNotification(scanner.nextLine());
            }
            if(scanner.ioException() != null){
                scanner.ioException().printStackTrace();
            }
        }
    }

    private void parseStringToNotification(String line){
        Splitter splitter = Splitter.on(',');
        List<String> list = splitter.splitToList(line);
        Notification notification = new Notification(list);
        dataStructureWrapper.addNotification(notification);
    }

	@JsonProperty
	public String getSampleData() {
		return sampleData;
	}

	@JsonProperty
	public void setSampleData(String sampleData) {
		this.sampleData = sampleData;
	}

	public DataStructureWrapper getDataStructureWrapper(){
	    return dataStructureWrapper;
    };
}
