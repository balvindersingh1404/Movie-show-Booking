package com.example.balvinder.bookYourShow.model;

import java.util.List;

public class MFYResponse {

private Integer code;
private String status;
private String message;
private List<List<Datum>> data = null;
private String version;
private String minversion;



/**
*
* @return
* The code
*/
public Integer getCode() {
return code;
}

/**
*
* @param code
* The code
*/
public void setCode(Integer code) {
this.code = code;
}

/**
*
* @return
* The status
*/
public String getStatus() {
return status;
}

/**
*
* @param status
* The status
*/
public void setStatus(String status) {
this.status = status;
}

/**
*
* @return
* The message
*/
public String getMessage() {
return message;
}

/**
*
* @param message
* The message
*/
public void setMessage(String message) {
this.message = message;
}

/**
*
* @return
* The data
*/
public List<List<Datum>> getData() {
return data;
}

/**
*
* @param data
* The data
*/
public void setData(List<List<Datum>> data) {
this.data = data;
}

/**
*
* @return
* The version
*/
public String getVersion() {
return version;
}

/**
*
* @param version
* The version
*/
public void setVersion(String version) {
this.version = version;
}

/**
*
* @return
* The minversion
*/
public String getMinversion() {
return minversion;
}

/**
*
* @param minversion
* The minversion
*/
public void setMinversion(String minversion) {
this.minversion = minversion;
}


}