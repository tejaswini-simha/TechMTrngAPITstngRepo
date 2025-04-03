package com.techmapi.auto.utils;

public class Properties {

    private static final PropertiesReader propertiesReader = new PropertiesReader();
    public static final String BASE_URI = propertiesReader.getBaseUri();
    public static final String CREATE_USER_END_POINT = propertiesReader.createUser();
    public static final String UPDATE_USER_END_POINT = propertiesReader.updateUser();
    public static final String DELETE_USER_END_POINT = propertiesReader.deleteUser();
    public static final String GET_USER_END_POINT = propertiesReader.getUser();
    public static final String GET_USERS_ON_PAGE_END_POINT = propertiesReader.getUsersOnPageEndPOint();
}
