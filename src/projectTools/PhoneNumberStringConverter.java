/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectTools;

import javafx.util.StringConverter;

/**
 *
 * @author taleb
 */
public class PhoneNumberStringConverter extends StringConverter<String> {

    @Override
    public String toString(String object) {

        return object.replaceAll("\\s", "");
    }

    @Override
    public String fromString(String string) {
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= string.length(); i++) {
            builder.append(string.charAt(i-1));
            if (i%2==0) {
                builder.append(" ");
            }
        }
        return builder.toString();
    }

}
