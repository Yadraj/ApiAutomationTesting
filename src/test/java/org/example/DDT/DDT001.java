package org.example.DDT;

import org.example.utils.UtilExcel;
import org.testng.annotations.Test;

public class DDT001 {

    @Test(dataProvider = "getData",dataProviderClass = UtilExcel.class)
    public void test(String username , String password){

        System.out.println("username");
        System.out.println("password");

    }
}
