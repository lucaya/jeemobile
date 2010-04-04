package net.lucaya.jeemobile.wurfl.jaxb;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import net.lucaya.jeemobile.wurfl.jaxb.Authors.Author;
import net.lucaya.jeemobile.wurfl.jaxb.Contributors.Contributor;
import net.lucaya.jeemobile.wurfl.jaxb.Devices.Device;

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
			
		
			for (Device device : wurfl.getDevices().getDevice()) {
				System.out.println(device.getUserAgent());
			}

			for(Author author: wurfl.getVersion().getAuthors().getAuthor()) {
				System.out.format("%30s | %1s\n", author.getName(), author.getEmail());
			}
			
			System.out.println("------>" + wurfl.getDevices().getDevice().get(0).getId());
			
			for(Group group: wurfl.getDevices().getDevice().get(0).getGroup()) {
				System.out.format("%1s\n", group.getId());
			}
			
			for(Capability capability: wurfl.getDevices().getDevice().get(0).getGroup().get(0).getCapability()) {
				System.out.format("%30s | %1s\n", capability.getName(), capability.getValue());
			}
			
		}
		finally {
			wurflIS.close();
		}
	}
	
}
