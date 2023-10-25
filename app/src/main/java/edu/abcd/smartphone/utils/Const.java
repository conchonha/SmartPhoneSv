package edu.abcd.smartphone.utils;

import edu.abcd.smartphone.R;
import edu.abcd.smartphone.app.MyApplication;

public class Const {
    public static final String KEY_URL_IMAGE = "https://firebasestorage.googleapis.com/v0/b/smartphone-38f55.appspot.com/o/";
    public static final String IS_SAVE_PASS = "isSavePass";
    public static final String KEY_PRODUCT_RESPONSE = "productResponse";

    public static final String KEY_USER = "keyUser";

    public static final String KEY_NOTIFY = "keyNotify";
    public static final String KEY_FAVORITE = "keyFavorite";
    public static final String KEY_LANGUAGE = "keyLanguage";
    public static final String URI_LANGUAGE = "https://com.bookMovieTicket.start";
    public static final String WEB_PRIVACY = "https://mobileappsltd.com/apps/guess_the_movie_privacy.html";
    public static final String KEY_CINIME = "cinema";

    public static final String FANPAGE = "https://www.facebook.com/";
    public static final String POLICY = "http://webpolicy.org/";

    public static String getUrlImage(String url) {
        return MyApplication.application.getString(R.string.url_image, url);
    }
}
