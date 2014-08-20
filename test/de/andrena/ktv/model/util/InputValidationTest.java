package de.andrena.ktv.model.util;

import static de.andrena.ktv.model.util.InputValidation.validate;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class InputValidationTest {

	@Test
	public void testSimpleNameInput() throws Exception {
		assertThat(validate("Gallien"), is(true));
	}

	@Test
	public void testNullName() throws Exception {
		assertThat(validate(null), is(false));
	}

	@Test
	public void testEmptyName() throws Exception {
		assertThat(validate(""), is(false));
	}

	@Test
	public void testSpecialCharacters() throws Exception {
		assertThat(validate("Heinz:Hofmann"), is(false));
	}

	@Test
	public void testUmlaute() throws Exception {
		assertThat(validate("Jürgen"), is(true));
	}

}
