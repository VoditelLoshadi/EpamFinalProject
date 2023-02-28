package ua.epam.elearn.selection.committee.model.services.util;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class SHA256PasswordEncoder implements PasswordEncoder{
    @Override
    public String encode(String password) {
        return Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();
    }

}
