package com.example.apptestkztek.domain.api;

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

    // reset
    public static final String resetDefault = "ResetDefault?/";
    public static final String resetDevice = "ResetDevice?/";
    // relay
    public static final String openCabinet1 = "SetRelay?/Relay=01/State=ON";
    public static final String openCabinet2 = "SetRelay?/Relay=02/State=ON";
    public static final String openCabinet3 = "SetRelay?/Relay=03/State=ON";
    public static final String openCabinet4 = "SetRelay?/Relay=04/State=ON";
    public static final String openCabinet5 = "SetRelay?/Relay=05/State=ON";
    public static final String openCabinet6 = "SetRelay?/Relay=06/State=ON";
    public static final String openCabinet7 = "SetRelay?/Relay=07/State=ON";
//    public static final String openCabinetAll = "SetRelay?/Relay=08/State=ON";
}
