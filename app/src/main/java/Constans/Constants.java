package Constans;

import Model.User;

public class Constants {
    /*  Current User
    * If Current User is Null means it is guest account
    *
    * */

    //User Part
    public static User currentUser=null;
    public static String companyID=null;






    /*MYSQL Database*/
    //Login Part
    public static final String LOGIN_URL="http://mirzemehdi.alwaysdata.net/taptour/login.php";
    public static final String KEY_USERTEXT="user";
    public static final String KEY_USERNAME="username";
    public static final String KEY_PASSWORD="password";
    public static final String KEY_USERID="id";
    public static final String KEY_USERTYPE="type";
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

    //Add FavoriteList Part

    public static final String ADDFAVORITESLIST_URL="http://mirzemehdi.alwaysdata.net/taptour/addtofavorites.php";
    public static final String KEY_FAVORITES_ID="id";
    public static final String KEY_FAVORITES_USERID="userId";
    public static final String KEY_FAVORITES_TOURID="tourId";

    //Add BookingsList Part

    public static final String ADDBOOKINGSLIST_URL="http://mirzemehdi.alwaysdata.net/taptour/addtobookings.php";
    public static final String KEY_BOOKINGS_ID="id";
    public static final String KEY_BOOKINGS_TOURID="tourId";
    public static final String KEY_BOOKINGS_COMPANYID="companyId";
    public static final String KEY_BOOKINGS_USERID="userId";
    public static final String KEY_BOOKINGS_NUMBERPEOPLE="numberPeople";

    //Favorite ToursList

    public static final String FAVORITETOURLIST_URL="http://mirzemehdi.alwaysdata.net/taptour/favoritetours.php";
    public static final String ISFAVORITE_URL="http://mirzemehdi.alwaysdata.net/taptour/isfavorite.php";


    public static final String BOOKINGSLIST_URL="http://mirzemehdi.alwaysdata.net/taptour/bookingforcompany.php";



}
