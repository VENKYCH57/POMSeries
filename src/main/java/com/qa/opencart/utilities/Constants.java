package com.qa.opencart.utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Constants {

	public static final int DEFAULT_TIME_OUT = 5;

	public static final String LOGIN_PAGE_TITLE = "Account Login";
	public static final String LOGIN_PAGE_URL = "https://naveenautomationlabs.com/opencart/index.php?route=account/login";
    
	public static final String ACCOUNT_PAGE_TITLE = "My Account";
	
	public static final List<String> EXP_ACCOUNTSPAGE_HEADER_LIST= new ArrayList<String>(Arrays.asList("My Account","My Orders","My Affiliate Account","Newsletter"));

	public static final int SAMSUNG_GALAXY_TAB_IMG_COUNT = 7;

	public static final String REG_FILL_SUCCESS_MESSAGE = "Your Account Has Been Created!";

	public static final String REGISTER_TEST_DATA_SHEET_PATH = "./src/tests/resources/testdata/register.xlsx";

	public static final String REGISTER_SHEET_NAME = "register";
}
