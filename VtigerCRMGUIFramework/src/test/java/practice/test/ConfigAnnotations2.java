package practice.test;

import org.testng.annotations.Test;

public class ConfigAnnotations2 extends BaseClass {

	@Test
	public void createContact() {
		System.out.println("Create Contact & verify");
	}

	@Test
	public void createContactWithDate() {
		System.out.println("Create Contact With Date & verify");
	}

}
