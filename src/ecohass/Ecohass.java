package ecohass;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Jaime
 */
public class Ecohass {

    //
    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        //Process p = Runtime.getRuntime().exec("C:\\xampp\\xampp_start");
        Login i = new Login();
        i.setVisible(true);
    }

}
