package net.lucaya.jeemobile.wurfl.jaxb;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import net.lucaya.jeemobile.wurfl.jaxb.ContributorsType.Contributor;
import net.lucaya.jeemobile.wurfl.jaxb.DevicesType.Device;

import org.junit.Test;

public class UnmarshallTest {

	@Test
	public void unmarshall() throws Exception {
		InputStream wurflIS = this.getClass().getClassLoader().getResourceAsStream("./wurfl.xml");
		try {
			String packageName = ObjectFactory.class.getPackage().getName();
			JAXBContext jc = JAXBContext.newInstance(packageName);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			Wurfl wurfl = (Wurfl) unmarshaller.unmarshal(wurflIS);
			
			
			DevicesType devices = wurfl.getDevices();
			for (Device device : devices.getDevice()) {
				System.out.println(device.getUserAgent());
			}
			
			for(Contributor contributor: wurfl.getVersion().getContributors().getContributor()) {
				System.out.format("%30s | %1s\n", contributor.getName(), contributor.getEmail());
			}
			
		}
		finally {
			wurflIS.close();
		}
	}
	
}
