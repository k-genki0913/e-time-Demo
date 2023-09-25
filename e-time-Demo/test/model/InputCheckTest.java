package model;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class InputCheckTest {
	
	InputCheck sut;
	
	@Before
	public void setUp() throws Exception{
		sut = new InputCheck();
	}
	
	@Test
	public void 引数が9999999999の文字列の場合True(){
		boolean actual = sut.inputUserIDCheck("9999999999");
		boolean expected = true;
		assertThat(actual, is(expected));
	}
	
	@Test
	public void 引数が123456789文字列の場合False() {
		boolean actual = sut.inputUserIDCheck("123456789");
		boolean expected = false;
		assertThat(actual, is(expected));
	}

}
