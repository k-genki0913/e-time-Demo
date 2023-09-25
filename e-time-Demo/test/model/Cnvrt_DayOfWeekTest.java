package model;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class Cnvrt_DayOfWeekTest {
	
	Cnvrt_DayOfWeek sut;
	
	@Before
	public void setUp() throws Exception {
		sut = new Cnvrt_DayOfWeek();
	}
	
	@Test
	public void 引数が1だと日曜日が戻される() {
		String actual = sut.putBack(1);
		String expected = "日曜日";
		assertThat(actual, is(expected));
	}
	
	@Test
	public void 引数が2だと月曜日が戻される() {
		String actual = sut.putBack(2);
		String expected = "月曜日";
		assertThat(actual, is(expected));
	}
	
	@Test
	public void 引数が3だと火曜日が戻される() {
		String actual = sut.putBack(3);
		String expected = "火曜日";
		assertThat(actual, is(expected));
	}
	
	@Test
	public void 引数が4だと水曜日が戻される() {
		String expected = "水曜日";
		String actual = sut.putBack(4);
		assertThat(actual, is(expected));
	}
	
	@Test
	public void 引数が5だと木曜日が戻される() {
		String actual = sut.putBack(5);
		String expected = "木曜日";
		assertThat(actual, is(expected));
	}
	
	@Test
	public void 引数が6だと金曜日が戻される() {
		String actual = sut.putBack(6);
		String expected = "金曜日";
		assertThat(actual, is(expected));
	}
	
	@Test
	public void 引数が7だと土曜日が戻される() {
		String actual = sut.putBack(7);
		String expected = "土曜日";
		assertThat(actual, is(expected));
	}
	
	@Test
	public void 引数が0だと空文字が戻される() {
		String actual = sut.putBack(0);
		String expected = "";
		assertThat(actual, is(expected));
	}
	
	@Test
	public void 引数が8だと空文字が戻される() {
		String actual = sut.putBack(8);
		String expected = "";
		assertThat(actual, is(expected));
	}

}
