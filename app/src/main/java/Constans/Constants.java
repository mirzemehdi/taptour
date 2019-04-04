package Constans;

import Model.User;

public class Constants {
    /*  Current User
    * If Current User is Null means it is guest account
    *
    * */
    public static User currentUser=null;


    //Login Part
    public static final String LOGIN_URL="http://mirzemehdi.alwaysdata.net/taptour/login.php";
    public static final String KEY_USERNAME="username";
    public static final String KEY_PASSWORD="password";

    public static final String RESULT_TEXT="result";
    public static final String RESULT_SUCCESS="success";
    public static final String RESULT_FAIL="fail";

    //TourList Part

    public static final String TOURLIST_URL="http://mirzemehdi.alwaysdata.net/taptour/tours.php";
    public static final String KEY_TOUR_ID="tour_id";
    public static final String KEY_TOUR_NAME="tour_name";
    public static final String KEY_TOUR_PRICE="tour_price";
    public static final String KEY_TOUR_IMAGELINK="tour_imageLink";
    public static final String KEY_TOUR_COMPANYID="company_id";
    public static final String KEY_TOUR_COMPANYNAME="companyName";





}
