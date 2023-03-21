package BookCartPS1;

import com.utils.ExcelReader;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.utils.Generic.*;
import static com.utils.DataPath.*;
import static helper.BookCartHelper.getBookStoreAssertions;

public class ProblemStatement1 {
    @DataProvider(name = "dataProvider")
    public Object[][] bookCartAssignment() throws IOException {
        return ExcelReader.getData(PATH_FOR_BOOKCART_EXCEL, SHEETNAME_FOR_PS1_BOOKCART);
    }

    @Test(dataProvider = "dataProvider")
    public void BookCartAssignment1(Object testCase, Object postUrl, Object bookId, Object getUrl, Object deleteUrl, Object expectedStatus) {
        String postBookCartUrl = postUrl + (String) bookId;
        deleteApiCall(deleteUrl, expectedStatus);
        postApiCall(postBookCartUrl, expectedStatus);
        Response response =getApiCall(getUrl, expectedStatus);
        getBookStoreAssertions(response,bookId);
        deleteApiCall(deleteUrl, expectedStatus);
    }
}

