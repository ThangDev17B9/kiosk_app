package com.example.apptestkztek.api;

public class Constant {
    //IP config
    public static final String changeIp = "ChangeIP?/";
    public static final String ip = "IP=";
    public static final String subnetMask = "/SubnetMask=";
    public static final String defaultGateWay = "/DefaultGateWay=";
    public static final String hostMac = "/HostMac=";


    // setup date time
    public static final String setDateTime = "SetDateTime?/";
    public static final String getDateTime = "GetDateTime?/";


    // input status
    public static final String getAllInputState = "GetAllInputState?/";
    public static final String getInputState = "GetInputState?/Input=";


    // version device
    public static final String getFirmwareVersion = "GetFirmwareVersion?/";


    // download user
    public static final String downUserCard = "DownloadUser?/";
    public static final String userID = "UserID=";
    public static final String lenCard = "/LenCard=";
    public static final String card = "/Card=";
    public static final String pin = "/Pin=";
    public static final String mode = "/Mode=";
    public static final String timeZone = "/TimeZone=";
    public static final String door = "/Door=";


    // delete user by id
    public static final String deleteUserID = "DeleteUser?/UserID=";
    // get user by id
    public static final String getUserID = "GetUser?/UserID=";
    // get all user
    public static final String getAllUser = "GetAllUser?/";
    public static final String autoDetect = "AutoDetect?";
}
