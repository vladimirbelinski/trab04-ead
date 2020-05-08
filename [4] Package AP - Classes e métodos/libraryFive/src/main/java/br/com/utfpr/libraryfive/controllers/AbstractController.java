package br.com.utfpr.libraryfive.controllers;

public abstract class AbstractController {

    protected static final String ROOT = "/";
    protected static final String REDIRECT_PREFIX = "redirect:";
    protected static final String FORWARD_PREFIX = "forward:";
    protected static final String REDIRECT_TO_SIGNUP = REDIRECT_PREFIX + "/signup";
    protected static final String REDIRECT_TO_HOMEPAGE = REDIRECT_PREFIX + "/home";
    protected static final String REDIRECT_TO_LOGIN = REDIRECT_PREFIX + "/login";
    protected static final String REDIRECT_TO_MY_LOANS = REDIRECT_PREFIX + "/user/myloans";
    protected static final String REDIRECT_TO_COLLECTION_LIST = REDIRECT_PREFIX + "/login";
    protected static final String REDIRECT_TO_ADMIN_VIEW_USERS = REDIRECT_PREFIX + "/admin/manage/users";
}
