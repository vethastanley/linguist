package com.softwareag.linguist.web.rest.errors;

/**
 * Created by VST on 11/10/2018.
 */
public class ProjectAlreadyExistException extends BadRequestAlertException {

    public ProjectAlreadyExistException() {
        super(ErrorConstants.DEFAULT_TYPE, "Project name already used!", "projectManagement", "projectexists");
    }
}
