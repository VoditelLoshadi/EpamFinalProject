package ua.epam.elearn.selection.committee.model.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ua.epam.elearn.selection.committee.model.services.util.SHA256PasswordEncoder;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class PasswordEncoderTest {

    private SHA256PasswordEncoder encoder = new SHA256PasswordEncoder();
    private String password;
    private String hashedPassword;

    public PasswordEncoderTest(String password, String hashedPassword) {
        this.password = password;
        this.hashedPassword = hashedPassword;
    }

    @Test
    public void shouldReturnCorrectSum() {
        assertEquals(encoder.encode(password), hashedPassword);
    }


    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"qwerty12345", "f6ee94ecb014f74f887b9dcc52daecf73ab3e3333320cadd98bcb59d895c52f5"},
                {"Password123", "008c70392e3abfbd0fa47bbc2ed96aa99bd49e159727fcba0f2e6abeb3a9d601"},
                {"helloPassword", "6493c155a5aead203bce3b16714aed30885472ed9d202a23cb3f24ef20f7bb0d"},
                {"epamSystems", "0ce761974f60048865e921d4faa5b078235ddd24d0de1a6e2b8bedc6b9c76d2f"},
                {"13579pass", "1fa4d208378373a6f6740f4053c61b80a343e1fc39612a24d3f0200113c74098"}
        });
    }

}
