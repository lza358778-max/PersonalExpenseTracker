package expense;

import java.io.IOException;

public interface Reportable {

    void exportCSV() throws IOException;

}